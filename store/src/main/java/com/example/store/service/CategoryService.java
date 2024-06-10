package com.example.store.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.store.entities.CategoryEntity;
import com.example.store.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity createCategory(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    public CategoryEntity getCategoryByName(String name) {
        return categoryRepository.findByNome(name)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryEntity updateCategory(CategoryEntity category) throws BadRequestException {
        if (category.getId() == null) {
            throw new BadRequestException("Category ID cannot be null");
        }
        return categoryRepository.save(category);
    }

    public List<CategoryEntity> getCategoriesByName(String name) {
        return categoryRepository.filterCategoryByName(name);
    }
}
