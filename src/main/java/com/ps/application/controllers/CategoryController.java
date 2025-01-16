package com.ps.application.controllers;

import com.ps.application.data.CategoryDao;
import com.ps.application.data.UserDao;
import com.ps.application.models.AppUser;
import com.ps.application.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@PreAuthorize("hasRole('ROLE_Admin')or hasRole('ROLE_Member')")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final UserDao userDao;
    private CategoryDao categoryDao;

    @Autowired
    public CategoryController (CategoryDao categoryDao, UserDao userDao){
        this.categoryDao = categoryDao;
        this.userDao = userDao;
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<?> getCategoryBYId (Principal principal, @PathVariable int categoryId) {
//        return categoryDao.getCategoryById(categoryId);
//        String username = principal.getName();
//        AppUser user = userDao.getByUsername(username);
//        int userId = user.getUserId();
        Category category = categoryDao.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }
}
