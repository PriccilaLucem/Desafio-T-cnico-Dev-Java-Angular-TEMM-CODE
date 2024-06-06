package com.example.store.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.store.repository.ProductRepository;
import com.example.store.util.Mapper;

import jakarta.persistence.EntityNotFoundException;

import com.example.store.dto.ProductCategoryDTO;
import com.example.store.entities.ProductEntity;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired 
    private CategoryService categoryService;

    public ProductEntity postProductService(ProductCategoryDTO productDTO){
        ProductEntity product = Mapper.parseObject(productDTO, ProductEntity.class);
        product.setCategory(categoryService.filterByCategoryNameService(productDTO.getCategoryNome()));
        return productRepository.save(product);
    }

    public ProductEntity filterByProductIdService(Long id){
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public List<ProductEntity> filterAllProductsService(){
        return productRepository.findAll();
    }
    
    public void deleteProductsService(Long id){
        productRepository.delete(productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found")));
    }

    public ProductEntity putProductService(ProductEntity product) throws BadRequestException{
        if(product.getId() == null){
            throw new BadRequestException("Product does not exist");
        }
        return productRepository.save(product);
    }


}
