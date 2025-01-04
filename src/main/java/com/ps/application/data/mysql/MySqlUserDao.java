package com.ps.application.data.mysql;

import com.ps.application.data.UserDao;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import com.ps.application.models.AppUser;

import javax.sql.DataSource;
import java.sql.*;
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
            while (resultSet.next()) {
                AppUser appUser = mapUser(resultSet);
                users.add(appUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public AppUser createUser(AppUser user) {
        String query = "INSERT INTO users (username,email,password,role) VALUES(?,?,?,?)";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
//            if(user.getRoles() != null){
            preparedStatement.setString(4, user.getRoles().toString());
//            } else {
//                preparedStatement.setString(4,AppUser.Roles.Guest.toString());
//                System.out.println("User role is not set");
//            }
//            AppUser.Roles roles = AppUser.Roles.valueOf(preparedStatement.setString(4,user.getRoles().toString()));
            int rowsAdded = preparedStatement.executeUpdate();
            if (rowsAdded > 0) {
                System.out.println("Rows added " + rowsAdded);
            } else {
                System.out.println("No rows added");
            }

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    user.setUserId(resultSet.getInt(1));
                } else {
                    throw new SQLException("Creating user failed");
                }
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Error creating user " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public AppUser mapUser(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("user_id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String roleStr = resultSet.getString("role");
        AppUser.Roles roles;
//        AppUser.Roles roles = AppUser.Roles.getRole(resultSet.getString("role").toUpperCase());
        if(roleStr !=null && !roleStr.isEmpty()){
            try{
                roles = AppUser.Roles.getRole(roleStr.toUpperCase());
            }catch(IllegalArgumentException e){
                System.out.println("Invalid role" + roleStr);
                roles = AppUser.Roles.Guest;
            }
        }else {
            roles = AppUser.Roles.Guest;
            System.out.println("Role not specified for " + username + "defaulted to guest");
        }

        return new AppUser(userId, username, email, password, roles);

    }
}
