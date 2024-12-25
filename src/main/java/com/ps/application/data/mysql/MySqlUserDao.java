package com.ps.application.data.mysql;

import com.ps.application.data.UserDao;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class  MySqlUserDao extends MySqlBaseDao implements UserDao  {
    public MySqlUserDao(DataSource dataSource) {
        super(dataSource);
    }
}
