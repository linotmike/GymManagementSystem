package com.ps.application.data;

import com.ps.application.models.AppUser;

import java.util.List;

public interface UserDao {
    AppUser getByUsername (String username);
    List<AppUser> getAllUsers();
    AppUser createUser (AppUser user);
}
