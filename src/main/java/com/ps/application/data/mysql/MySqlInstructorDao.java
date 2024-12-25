package com.ps.application.data.mysql;

import com.ps.application.data.InstructorDao;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
@Component
public class MySqlInstructorDao extends MySqlBaseDao implements InstructorDao {
    public MySqlInstructorDao(DataSource dataSource) {
        super(dataSource);
    }
}
