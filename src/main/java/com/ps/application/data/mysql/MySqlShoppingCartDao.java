package com.ps.application.data.mysql;

import com.ps.application.data.ShoppingCartDao;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MySqlShoppingCartDao extends MySqlBaseDao implements ShoppingCartDao {
    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }
}
