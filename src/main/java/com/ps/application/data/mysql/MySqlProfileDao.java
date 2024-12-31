package com.ps.application.data.mysql;

import com.ps.application.data.ProfileDao;
import com.ps.application.models.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MySqlProfileDao extends MySqlBaseDao implements ProfileDao {
    public MySqlProfileDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Profile createProfile(Profile profile) {
        return null;
    }

    @Override
    public Profile getUserById(int profileId) {
        return null;
    }

    @Override
    public Profile updateProfile(int profileId, Profile profile) {
        return null;
    }
}
