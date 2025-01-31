package com.ps.application.data.mysql;

import com.ps.application.data.InstructorDao;
import com.ps.application.models.Instructors;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlInstructorDao extends MySqlBaseDao implements InstructorDao {
    public MySqlInstructorDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Instructors> getAllInstructors() {
        List<Instructors> instructors = new ArrayList<>();
        String query = "SELECT * FROM instructors";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Instructors instructor = mapInstructors(resultSet);
                instructors.add(instructor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructors;
    }

    @Override
    public Instructors getInstructorById(int instructorId) {
        String query = "SELECT * FROM instructors WHERE instructor_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, instructorId);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                if (resultSet.next()) {
                    return mapInstructors(resultSet);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Instructors createInstructors(Instructors instructors) {
        String query = "INSERT INTO instructors (user_id,first_name,last_name,bio,specialty) VALUES (?,?,?,?,?)";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setInt(1, instructors.getUserId());
            preparedStatement.setString(2, instructors.getFirstName());
            preparedStatement.setString(3, instructors.getLastName());
            preparedStatement.setString(4, instructors.getBio());
            preparedStatement.setString(5, instructors.getSpecialty());

            int rowsCreated = preparedStatement.executeUpdate();
            if (rowsCreated > 0) {
                System.out.println("Rows created " + rowsCreated);
                try (
                        ResultSet resultSet = preparedStatement.getGeneratedKeys()
                ) {
                    if (resultSet.next()) {
                        instructors.setInstructorId(resultSet.getInt(1));
                        return instructors;
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
    public Instructors updateInstructors(int instructorId, Instructors instructors) {
        String query = "UPDATE instructors SET first_name = ?, last_name = ?, bio = ?, specialty = ? WHERE instructor_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {

            preparedStatement.setString(1, instructors.getFirstName());
            preparedStatement.setString(2, instructors.getLastName());
            preparedStatement.setString(3, instructors.getBio());
            preparedStatement.setString(4, instructors.getSpecialty());
            preparedStatement.setInt(5, instructorId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Rows updated " + 0);
            } else {
                System.out.println("No rows updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructors;
    }

    @Override
    public Instructors deleteInstructors(int instructorId) {
        return null;
    }

    public Instructors mapInstructors(ResultSet resultSet) throws SQLException {
        int instructorId = resultSet.getInt("instructor_id");
        int userId = resultSet.getInt("user_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String bio = resultSet.getString("bio");
        String specialty = resultSet.getString("specialty");

        return new Instructors(instructorId, userId, firstName, lastName, bio, specialty);
    }
}
