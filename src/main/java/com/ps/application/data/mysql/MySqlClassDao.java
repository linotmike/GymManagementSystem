package com.ps.application.data.mysql;

import com.ps.application.data.ClassDao;
import com.ps.application.models.Class;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlClassDao extends MySqlBaseDao implements ClassDao {
    public MySqlClassDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Class getClassById(int classId) {
        return null;
    }

    @Override
    public List<Class> getAllClasses() {
        return List.of();
    }

    @Override
    public Class createClass(Class clas) {
        return null;
    }

    @Override
    public Class updateClass(int classID, Class clas) {
        return null;
    }

    @Override
    public Class deleteClass(int classID) {
        return null;
    }
    public Class mapClass (ResultSet resultSet) throws SQLException {
        int classId = resultSet.getInt("classId");
        String name = resultSet.getString("name");
        Class.Type type = Class.Type.valueOf(resultSet.getString("type"));
        String description = resultSet.getString("description");
        Class.DifficultyLevel difficultyLevel = Class.DifficultyLevel.valueOf(resultSet.getString("difficulty_level"));
        int maxParticipants = resultSet.getInt("max_participants");

        return new Class(classId,name,type,description,difficultyLevel,maxParticipants);
    }
}
