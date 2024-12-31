package com.ps.application.controllers;

import com.ps.application.data.UserDao;
import com.ps.application.models.AppUser;
import com.ps.application.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    public AuthenticationController (UserDao userDao){
        this.userDao = userDao;
    }



    @PostMapping(path = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login (@RequestBody AppUser appUser){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(),appUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("login failed: " + e.getMessage());
        }
    }



   public static class AuthResponse{
        private String token;

        public AuthResponse (String token){
            this.token = token;
        }
        public String getToken(){
            return token;
        }
        public void setToken(String token){
            this.token = token;
        }
    }

}


