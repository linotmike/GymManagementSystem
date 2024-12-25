package com.ps.application.data.mysql;

import com.ps.application.data.OrderDao;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MySqlOrderDao extends MySqlBaseDao implements OrderDao {
    public MySqlOrderDao(DataSource dataSource) {
        super(dataSource);
    }
}
