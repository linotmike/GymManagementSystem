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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            if(userDetails instanceof AppUser){
//                ((AppUser) userDetails).setPassword(null);
//            }
//            String token = jwtTokenUtil.generateToken(userDetails);
//            assert userDetails instanceof AppUser;
            AppUser loggedInUser = (AppUser) authentication.getPrincipal();
            loggedInUser.setPassword(null);
            String token = jwtTokenUtil.generateToken(loggedInUser);
            return ResponseEntity.ok(new AuthResponse(loggedInUser, token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("login failed: " + e.getMessage());
        }
    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
//        AppUser appUser = signupRequest.getUser();
//        Profile profile = signupRequest.getProfile();
//
//        if (appUser.getUsername() == null || appUser.getEmail() == null || appUser.getPassword() == null) {
//            return ResponseEntity.badRequest().body("Missing information");
//        }
//        try {
//            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
//            AppUser newUser = userDao.createUser(appUser);
//            if (newUser == null) {
//                return ResponseEntity.badRequest().body("User could not be created");
//            }
//            profile.setUserId(newUser.getUserId());
//            Profile createdProfile = profileDao.createProfile(profile);
//            if (createdProfile == null) {
//                return ResponseEntity.badRequest().body("Profile could not be created");
//            }
//            final String token = jwtTokenUtil.generateToken(newUser);
//            return ResponseEntity.ok(new AuthResponse(newUser, token));
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured while signing up " + e.getMessage());
//        }
//
////        try{
////
////            Profile profile = new Profile();
////             profile.setUserId(newUser.getUserId());
////             profile.setBio(newUser.getBio());
////
////            final String token = jwtTokenUtil.generateToken(newUser);
//////            return ResponseEntity.ok(new AuthResponse(newUser,token));
////
////        }catch (Exception e){
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during signing up " + e.getMessage());
////        }
//    }
@PostMapping("/signup")
public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
    if (signupRequest == null) {
        return ResponseEntity.badRequest().body("No signup request data provided");
    }

    AppUser appUser = signupRequest.getUser();
    Profile profile = signupRequest.getProfile();

    // Check if the AppUser object is not null
    if (appUser == null) {
        return ResponseEntity.badRequest().body("Signup details are incomplete.");
    }

    // Check required user fields
    if (appUser.getUsername() == null || appUser.getEmail() == null || appUser.getPassword() == null) {
        return ResponseEntity.badRequest().body("Missing information");
    }

    // Encrypt the user's password
    appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

    // Create the user
    AppUser newUser = userDao.createUser(appUser);
    if (newUser == null) {
        return ResponseEntity.badRequest().body("User could not be created");
    }

    // Check if profile data is included and create profile
    if (profile != null) {
        profile.setUserId(newUser.getUserId());  // Associate the profile with the newly created user
        Profile createdProfile = profileDao.createProfile(profile);
        if (createdProfile == null) {
            return ResponseEntity.badRequest().body("Profile could not be created");
        }
    }

    // Generate JWT token
    final String token = jwtTokenUtil.generateToken(newUser);

    // Return success response
    return ResponseEntity.ok(new AuthResponse(newUser, token));
}



    public static class AuthResponse {
        private AppUser appUser;
        private String token;

        public AuthResponse(AppUser user, String token) {
            this.appUser = appUser;
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

}


