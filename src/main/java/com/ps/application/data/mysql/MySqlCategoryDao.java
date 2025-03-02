package com.ps.application.data.mysql;

import com.ps.application.data.CategoryDao;
import com.ps.application.models.Category;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlBaseDao implements CategoryDao {
    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        String query = "SELECT * FROM categories WHERE category_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, categoryId);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                if (resultSet.next()) {
                    return mapCategories(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        String query = "SELECT * FROM categories";
        List<Category> categories = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Category category = mapCategories(resultSet);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category createCategories(Category category) {
        String query = "INSERT INTO categories (name,description) VALUES (?,?) ";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)

        ) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());

            int rowsCreated = preparedStatement.executeUpdate();
            if (rowsCreated > 0) {
                System.out.println("Rows created " + 1);
                try (
                        ResultSet resultSet = preparedStatement.getGeneratedKeys()
                ) {
                    if (resultSet.next()) {
                        category.setCategoryId(resultSet.getInt(1));
                        return category;
                    }
                }
            } else {
                System.out.println("No rows created");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category updateCategory(int categoryId, Category category) {
        String query = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setInt(3, categoryId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Rows updated" + rowsUpdated);
            } else {
                System.out.println("No rows updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public Category deleteCategory(int categoryId) {
        String query = "DELETE FROM categories WHERE category_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ) {
            preparedStatement.setInt(1,categoryId);
            int rowsDeleted = preparedStatement.executeUpdate();
            if(rowsDeleted > 0){
                System.out.println("Rows deleted " + rowsDeleted);
            } else{
                System.out.println("No rows deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Category mapCategories(ResultSet resultSet) throws SQLException {
        int categoryId = resultSet.getInt("category_id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");

        return new Category(categoryId, name, description);


    }
}
