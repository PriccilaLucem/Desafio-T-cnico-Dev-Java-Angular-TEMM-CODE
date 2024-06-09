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


    public CategoryEntity postCategoryService(CategoryEntity category){
        return categoryRepository.save(category);
    }

    public CategoryEntity filterByCategoryNameService(String nome){
        return categoryRepository.findByNome(nome).orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }

    public List<CategoryEntity> filterAllCategoriesService(){
        return categoryRepository.findAll();
    }
    
    public void deleteCategoryService(Long id){
        categoryRepository.delete(categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found")));
    }

    public CategoryEntity putCategoryService(CategoryEntity category) throws BadRequestException{
        if(category.getId() == null){
            throw new BadRequestException("Category does not exist");
        }
        return categoryRepository.save(category);
    }
    public List<CategoryEntity> filterByNome(String nome){
        return categoryRepository.filterCategoryByName(nome);
    }
}
