package com.ps.application.data.mysql;

import com.ps.application.data.MemberDao;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MySqlMemberDao extends MySqlBaseDao implements MemberDao {
    public MySqlMemberDao(DataSource dataSource) {
        super(dataSource);
    }
}
