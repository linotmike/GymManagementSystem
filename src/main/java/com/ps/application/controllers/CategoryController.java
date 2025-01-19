package com.ps.application.controllers;

import com.ps.application.data.CategoryDao;
import com.ps.application.data.UserDao;
import com.ps.application.models.AppUser;
import com.ps.application.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    public ResponseEntity<?> getCategoryBYId ( @PathVariable int categoryId) {
        Category category = categoryDao.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @GetMapping()
    public ResponseEntity<?>getAllCategories(){
//        return categoryDao.getAllCategories();
        List<Category> categories = categoryDao.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<?> createCategory (@RequestBody Category category){
        Category createdCategory = categoryDao.createCategories(category);
        return ResponseEntity.ok(createdCategory);
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<?> updateCategory (@PathVariable int categoryId, @RequestBody Category category){
        Category updatedCategory = categoryDao.updateCategory(categoryId,category);
        return ResponseEntity.ok(updatedCategory);

    }
}
