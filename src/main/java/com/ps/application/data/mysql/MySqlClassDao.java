package com.ps.application.data.mysql;

import com.ps.application.data.ClassDao;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MySqlClassDao extends MySqlBaseDao implements ClassDao {
    public MySqlClassDao(DataSource dataSource) {
        super(dataSource);
    }
}
