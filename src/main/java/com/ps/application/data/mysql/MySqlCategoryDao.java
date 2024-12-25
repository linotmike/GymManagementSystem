package com.ps.application.data.mysql;

import com.ps.application.data.CategoryDao;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MySqlCategoryDao extends MySqlBaseDao implements CategoryDao {
    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }
}
