package com.ps.application.data.mysql;

import com.ps.application.data.CategoryDao;
import com.ps.application.models.Category;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlBaseDao implements CategoryDao {
    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public Category createCategories(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(int categoryId, Category category) {
        return null;
    }

    @Override
    public Category deleteCategory(int categoryId) {
        return null;
    }
    public Category mapCategories (ResultSet resultSet) throws SQLException{
        int categoryId = resultSet.getInt("category_id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");

        return new Category(categoryId,name,description);


    }
}
