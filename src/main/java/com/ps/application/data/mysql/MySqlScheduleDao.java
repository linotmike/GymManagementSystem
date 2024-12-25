package com.ps.application.data.mysql;

import com.ps.application.data.ScheduleDao;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MySqlScheduleDao extends MySqlBaseDao implements ScheduleDao {
    public MySqlScheduleDao(DataSource dataSource) {
        super(dataSource);
    }
}
