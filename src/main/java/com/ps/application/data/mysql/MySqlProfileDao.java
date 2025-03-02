package com.ps.application.data.mysql;

import com.ps.application.data.ProfileDao;
import com.ps.application.models.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class MySqlProfileDao extends MySqlBaseDao implements ProfileDao {
    public MySqlProfileDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Profile createProfile(Profile profile) {
        String query = "INSERT INTO profiles (user_id,bio,image_url,phone,email,address,city,state,zip,date_of_birth,fitness_goals,health_condition) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, profile.getUserId());
            preparedStatement.setString(2, profile.getBio());
            preparedStatement.setString(3, profile.getImageUrl());
            preparedStatement.setString(4, profile.getPhone());
            preparedStatement.setString(5, profile.getEmail());
            preparedStatement.setString(6, profile.getAddress());
            preparedStatement.setString(7, profile.getCity());
            preparedStatement.setString(8, profile.getState());
            preparedStatement.setString(9, profile.getZip());
//            LocalDate dateOfBirth = preparedStatement.setInt(10,profile.getDateOfBirth()).toLocalDate();
            LocalDate dateOfBirth = profile.getDateOfBirth();
            java.sql.Date sqlDateOfBirth = java.sql.Date.valueOf(dateOfBirth);
            preparedStatement.setDate(10, sqlDateOfBirth);
            preparedStatement.setString(11, profile.getFitnessGoals());
            preparedStatement.setString(12, profile.getHealthCondition());
//            int rowsCreated = re.executeQuery();
            int rowsCreated = preparedStatement.executeUpdate();

            if (rowsCreated > 0) {
                System.out.println("Rows created " + rowsCreated);
            } else {
                System.out.println("No rows created");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }

    @Override
    public Profile getUserById(int id) {
        String query = "SELECT * FROM profiles WHERE user_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                if (resultSet.next()) {
                    int profileId = resultSet.getInt("profile_Id");
//                    int userId = resultSet.getInt("user_id");
                    String bio = resultSet.getString("bio");
                    String imageUrl = resultSet.getString("image_url");
                    String phone = resultSet.getString("phone");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("state");
                    String zip = resultSet.getString("zip");
                    LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate();
                    String fitnessGoals = resultSet.getString("fitness_goals");
                    String healthCondition = resultSet.getString("health_condition");

                    return new Profile(profileId, id, bio, imageUrl, phone, email, address, city, state, zip, dateOfBirth, fitnessGoals, healthCondition);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting profile " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Profile updateProfile(int profileId, Profile profile) {
        String query = "UPDATE profiles SET bio = ?, image_url = ?, phone = ?, email = ?, address = ?, city = ?, state = ?, zip = ?, date_of_birth = ?, fitness_goals = ?, health_condition = ? WHERE profile_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ) {
            preparedStatement.setString(1,profile.getBio());
            preparedStatement.setString(2,profile.getImageUrl());
            preparedStatement.setString(3,profile.getPhone());
            preparedStatement.setString(4, profile.getEmail());
            preparedStatement.setString(5, profile.getAddress());
            preparedStatement.setString(6, profile.getCity());
            preparedStatement.setString(7, profile.getState());
            preparedStatement.setString(8, profile.getZip());
            preparedStatement.setDate(9, java.sql.Date.valueOf(profile.getDateOfBirth()));
            preparedStatement.setString(10, profile.getFitnessGoals());
            preparedStatement.setString(11, profile.getHealthCondition());
            preparedStatement.setInt(12, profileId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("profile Updated " + rowsUpdated);
            } else {
                System.out.println("No profile updated");
            }
        } catch (SQLException e) {
            System.out.println("Error updating profile " + e.getMessage());
            e.printStackTrace();
        }
        return profile;
    }
}
