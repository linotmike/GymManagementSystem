package com.ps.application.controllers;

import com.ps.application.data.ProfileDao;
import com.ps.application.data.UserDao;
import com.ps.application.models.AppUser;
import com.ps.application.models.Authentication.SignupRequest;
import com.ps.application.models.Profile;
import com.ps.application.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private UserDao userDao;
    private ProfileDao profileDao;

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private PasswordEncoder passwordEncoder;


    @Autowired
    public AuthenticationController(UserDao userDao, ProfileDao profileDao, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.profileDao = profileDao;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody AppUser appUser) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            if(userDetails instanceof AppUser){
//                ((AppUser) userDetails).setPassword(null);
//            }
            String token = jwtTokenUtil.generateToken(userDetails);

//            AuthResponse authResponse = new AuthResponse(userDetails.getUsername(), token)
//            assert userDetails instanceof AppUser;
//            AppUser loggedInUser = (AppUser) authentication.getPrincipal();
//            loggedInUser.setPassword(null);
//            String token = jwtTokenUtil.generateToken(loggedInUser);
            return ResponseEntity.ok(new AuthResponse(userDetails.getUsername(), token, userDetails.getAuthorities()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("login failed: " + e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
        System.out.println("Received signup request: " + signupRequest);
        if (signupRequest.getUser() == null || signupRequest.getProfile() == null) {
            return ResponseEntity.badRequest().body("Request structure is incorrect");
        }

        AppUser appUser = signupRequest.getUser();
        Profile profile = signupRequest.getProfile();

        if (appUser.getUsername() == null || appUser.getEmail() == null || appUser.getPassword() == null || appUser.getRoles() == null) {
            System.out.println("Missing info");
            return ResponseEntity.badRequest().body("Missing information");

        }
        try {
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            AppUser newUser = userDao.createUser(appUser);
            if (newUser == null) {
                return ResponseEntity.badRequest().body("User could not be created");
            }
            profile.setUserId(newUser.getUserId());
            Profile createdProfile = profileDao.createProfile(profile);
            if (createdProfile == null) {
                return ResponseEntity.badRequest().body("Profile could not be created");
            }
            final String token = jwtTokenUtil.generateToken(newUser);
            return ResponseEntity.ok(new AuthResponse(newUser.getUsername(), token,newUser.getAuthorities()));
//            return null;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured while signing up " + e.getMessage());
        }


    }


    public static class AuthResponse {
        //        private AppUser appUser;
        private String token;
        //        private int userId;
        private String username;
        //        private String role;
        private Collection<? extends GrantedAuthority> authorities;

        public AuthResponse(String username, String token, Collection<? extends GrantedAuthority> authorities) {
//            this.appUser = appUser;
            this.username = username;
            this.token = token;
//            this.userId = user.getUserId();
//            this.role = user.getRoles().toString();
            this.authorities = authorities;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = authorities;
        }
//        public int getUserId() {
//            return userId;
//        }
//
//        public void setUserId(int userId) {
//            this.userId = userId;
//        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

//        public String getRole() {
//            return role;
//        }
//
//        public void setRole(String role) {
//            this.role = role;
//        }
    }

}



