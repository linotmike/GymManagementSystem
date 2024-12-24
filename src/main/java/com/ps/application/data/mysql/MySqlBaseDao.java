package com.ps.application.data.mysql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class MySqlBaseDao {
    private DataSource dataSource;

    public MySqlBaseDao (DataSource dataSource){
        this.dataSource = dataSource;
    }
    protected Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
}
