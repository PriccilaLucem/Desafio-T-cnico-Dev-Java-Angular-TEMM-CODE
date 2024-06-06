package com.example.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.store.entities.CategoryEntity;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByNome(String nome);
}
