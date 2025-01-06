package com.ps.application.controllers;

import com.ps.application.data.ProfileDao;
import com.ps.application.data.UserDao;
import com.ps.application.models.AppUser;
import com.ps.application.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/profile")
@PreAuthorize("hasRole('ROLE_Admin')or hasRole('ROLE_Member')")
public class ProfileController {
    private ProfileDao profileDao;
    private UserDao userDao;

    @Autowired
    public ProfileController(ProfileDao profileDao, UserDao userDao){
        this.profileDao = profileDao;
        this.userDao = userDao;
    }


    @GetMapping()
    public ResponseEntity<?> getProfileById(Principal principal){
        String username = principal.getName();
        AppUser user =  userDao.getByUsername(username);
        int userId = user.getUserId();
        Profile profile = profileDao.getUserById(userId);
        return ResponseEntity.ok(profile);

    }

    @PutMapping("{profileId}")
    public ResponseEntity<?> updateProfile (Principal principal, @RequestBody Profile profile){
        String username = principal.getName();
        AppUser user = userDao.getByUsername(username);
        int userId = user.getUserId();
        Profile newProfile = profileDao.updateProfile(userId,profile);
        return ResponseEntity.ok(newProfile);
    }


}
