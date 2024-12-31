package com.ps.application.data.mysql;

import com.ps.application.data.UserDao;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import com.ps.application.models.AppUser;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlUserDao extends MySqlBaseDao implements UserDao {
    public MySqlUserDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public AppUser getByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, username);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                if (resultSet.next()) {
                    return mapUser(resultSet);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AppUser> getAllUsers() {
        String query = "SELECT * FROM users";
        List<AppUser> users = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                ) {
            while (resultSet.next()){
                AppUser appUser = mapUser(resultSet);
                users.add(appUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public AppUser mapUser(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("user_id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        AppUser.Roles roles = AppUser.Roles.getRole(resultSet.getString("role").toUpperCase());

        return new AppUser(userId, username, email, password, roles);

    }
}
