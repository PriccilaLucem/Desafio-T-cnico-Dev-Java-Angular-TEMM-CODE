package com.example.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.store.entities.CategoryEntity;
import java.util.Optional;
import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query("SELECT c FROM CategoryEntity c WHERE c.nome LIKE %:nome%")
    List<CategoryEntity> filterCategoryByName(String nome);
    
    Optional<CategoryEntity> findByNome(String nome);
}
