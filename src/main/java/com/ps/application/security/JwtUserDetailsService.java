package com.ps.application.security;

import com.ps.application.data.UserDao;
import com.ps.application.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private UserDao userDao;

    @Autowired
    public JwtUserDetailsService (UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userDao.getByUsername(username);
        if(appUser == null){
            throw new UsernameNotFoundException("user not found with the username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(),appUser.getPassword(),appUser.getAuthorities());
    }
}
