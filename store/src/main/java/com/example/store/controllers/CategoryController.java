package com.example.store.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.entities.CategoryEntity;
import com.example.store.service.CategoryService;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryEntity>> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.filterAllCategoriesService());
    }

    @GetMapping("/search")
    public ResponseEntity<CategoryEntity> getCategoryByName(@RequestParam String nome) {
        return ResponseEntity.ok().body(categoryService.filterByCategoryNameService(nome));
    }
    
    @PostMapping()
    public ResponseEntity<CategoryEntity> postCategory(@RequestBody CategoryEntity entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.postCategoryService(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryEntity> putCategoryEntity(@PathVariable Long id, @RequestBody CategoryEntity entity) throws BadRequestException {
        entity.setId(id);
        return ResponseEntity.accepted().body(categoryService.putCategoryService(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategoryController(@PathVariable Long id){
        categoryService.deleteCategoryService(id);
        return ResponseEntity.noContent().build();
    }
    
}
