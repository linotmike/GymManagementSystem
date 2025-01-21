package com.ps.application.data.mysql;

import com.ps.application.data.InstructorDao;
import com.ps.application.models.Instructors;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
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
        return null;
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
}
