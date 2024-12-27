package com.ps.application.data;

import com.ps.application.models.AppUser;

public interface UserDao {
    AppUser getByUsername (String username);
}
