package com.ps.application.data;

import com.ps.application.models.Category;

import java.util.List;

public interface CategoryDao {
    Category getCategoryById(int categoryId);
    List<Category> getAllCategories ();
    Category createCategories( Category category);
    Category updateCategory (int categoryId, Category category);
    Category deleteCategory(int categoryId);

    }

