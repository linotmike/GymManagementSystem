package com.ps.application.data.mysql;

import com.ps.application.data.ProductDao;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MySqlProductDao extends MySqlBaseDao implements ProductDao {
    public MySqlProductDao(DataSource dataSource) {
        super(dataSource);
    }
}
