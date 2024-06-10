package com.example.store.presenter;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.store.entities.CategoryEntity;
import com.example.store.service.CategoryService;

import java.util.List;

@Service
public class CategoryPresenter {

    @Autowired
    private CategoryService categoryService;

    public CategoryEntity createCategory(CategoryEntity category) {
        return categoryService.createCategory(category);
    }

    public CategoryEntity getCategoryByName(String name) {
        return categoryService.getCategoryByName(name);
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryService.getAllCategories();
    }

    public void deleteCategory(Long id) {
        categoryService.deleteCategory(id);
    }

    public CategoryEntity updateCategory(CategoryEntity category) throws BadRequestException {
        return categoryService.updateCategory(category);
    }

    public List<CategoryEntity> getCategoriesByName(String name) {
        return categoryService.getCategoriesByName(name);
    }
}

