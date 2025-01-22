package com.ps.application.data.mysql;

import com.ps.application.data.InstructorDao;
import com.ps.application.models.Instructors;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class MySqlInstructorDao extends MySqlBaseDao implements InstructorDao {
    public MySqlInstructorDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Instructors> getAllInstructors() {
        return List.of();
    }

    @Override
    public Instructors getInstructorById(int instructorId) {
        String query = "SELECT * FROM instructors WHERE instructor_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1,instructorId);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                if(resultSet.next()){
                  return  mapInstructors(resultSet);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public Instructors createInstructors(Instructors instructors) {
        return null;
    }

    @Override
    public Instructors updateInstructors(int instructorId, Instructors instructors) {
        return null;
    }

    @Override
    public Instructors deleteInstructors(int instructorId) {
        return null;
    }

    public Instructors mapInstructors (ResultSet resultSet) throws SQLException {
        int instructorId = resultSet.getInt("instructor_id");
        int userId = resultSet.getInt("user_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String bio = resultSet.getString("bio");
        String specialty = resultSet.getString("specialty");

        return new Instructors(instructorId,userId,firstName,lastName,bio,specialty);
    }
}
