package com.example.store.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.store.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

import com.example.store.entities.ProductEntity;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

      public ProductEntity postProductService(ProductEntity product){
        return productRepository.save(product);
    }

    public ProductEntity filterByProductNameService(Long id){
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public List<ProductEntity> filterAllProductsService(){
        return productRepository.findAll();
    }
    
    public void deleteProductsService(Long id){
        productRepository.delete(productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found")));
    }

    public ProductEntity putCategoryService(ProductEntity product) throws BadRequestException{
        if(product.getId() == null){
            throw new BadRequestException("Category does not exist");
        }
        return productRepository.save(product);
    }


}
