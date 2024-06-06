package com.example.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.store.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    
}
