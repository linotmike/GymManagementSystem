package com.ps.application.data.mysql;

import com.ps.application.data.ClassDao;
import com.ps.application.models.Class;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlClassDao extends MySqlBaseDao implements ClassDao {
    public MySqlClassDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Class getClassById(int classId) {
        String query = "SELECT * FROM classes WHERE class_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, classId);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                if (resultSet.next()) {
                    return mapClass(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Class> getAllClasses() {
        List<Class> clas = new ArrayList<>();
        String query = "SELECT * FROM classes ";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Class classes = mapClass(resultSet);
                clas.add(classes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clas;
    }

    @Override
    public Class createClass(Class clas) {
        String query = "INSERT INTO classes (name,type,description,difficulty_level,max_participants) VALUES (?,?,?,?,?)";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, clas.getName());
            preparedStatement.setString(2, clas.getType().toString());
            preparedStatement.setString(3, clas.getDescription());
            preparedStatement.setString(4, clas.getDifficultyLevel().toString());
            preparedStatement.setInt(5, clas.getMaxParticipants());

            int rowsCreated = preparedStatement.executeUpdate();
            if (rowsCreated > 0) {
                System.out.println("Rows created " + 1);
            } else {
                System.out.println("No rows updated");
            }
            try (
                    ResultSet resultSet = preparedStatement.getGeneratedKeys()
            ) {
                if (resultSet.next()) {
                    clas.setClassId(resultSet.getInt(1));
                } else {
                    throw new SQLException("Creating class failed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Class updateClass(int classID, Class clas) {
        String query = "UPDATE classes SET name = ?, type = ?, description = ?, difficulty_level = ?, max_participants = ? WHERE user_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, clas.getName());
            preparedStatement.setString(2, clas.getType().toString());
            preparedStatement.setString(3, clas.getDescription());
            preparedStatement.setString(4, clas.getDifficultyLevel().toString());
            preparedStatement.setInt(5, clas.getMaxParticipants());
            preparedStatement.setInt(6, classID);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Rows Updated " + 1);
            } else {
                System.out.println("No rows updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clas;
    }

    @Override
    public Class deleteClass(int classID) {
        String query = "DELETE FROM classes WHERE class_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, classID);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Rows deleted " + 1);
            } else {
                System.out.println("No rows updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

public Class mapClass(ResultSet resultSet) throws SQLException {
    int classId = resultSet.getInt("class_id");
    String name = resultSet.getString("name");
    String typeStr = resultSet.getString("type");
    Class.Type type = null;
    if (typeStr != null) {
        try {
            type = Class.Type.valueOf(typeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid type value: " + typeStr);
            e.printStackTrace();
        }
    }

    String description = resultSet.getString("description");
    String difficultyLevelStr = resultSet.getString("difficulty_level");
    Class.DifficultyLevel difficultyLevel = null;
    if (difficultyLevelStr != null) {
        try {
            difficultyLevel = Class.DifficultyLevel.valueOf(difficultyLevelStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid difficulty level: " + difficultyLevelStr);
            e.printStackTrace();
        }
    }

    int maxParticipants = resultSet.getInt("max_participants");

    return new Class(classId, name, type, description, difficultyLevel, maxParticipants);
}

}
