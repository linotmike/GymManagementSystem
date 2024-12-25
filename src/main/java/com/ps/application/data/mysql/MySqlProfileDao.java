package com.ps.application.data.mysql;

import com.ps.application.data.ProfileDao;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MySqlProfileDao extends MySqlBaseDao implements ProfileDao {
    public MySqlProfileDao(DataSource dataSource) {
        super(dataSource);
    }
}
