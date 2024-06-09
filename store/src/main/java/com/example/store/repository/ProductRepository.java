package com.example.store.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.store.entities.ProductEntity;
import java.util.List;


public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT p FROM ProductEntity p WHERE p.nome LIKE %:nome% OR p.category.nome LIKE %:categoria%")
    List<ProductEntity> findByNomeOrCategoria(@Param("nome") String nome, @Param("categoria") String categoria);

}