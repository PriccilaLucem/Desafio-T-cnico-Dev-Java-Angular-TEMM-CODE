package com.example.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.store.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}