package com.example.store.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.dto.ProductCategoryDTO;
import com.example.store.entities.ProductEntity;
import com.example.store.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductEntity> postProductController(@RequestBody ProductCategoryDTO entity) {        
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.postProductService(entity));
    }
    
    @GetMapping()
    public  ResponseEntity<List<ProductEntity>>  getAllProductsController(@RequestParam String param) {
        return ResponseEntity.ok().body(productService.filterAllProductsService());
    }
    
    @GetMapping("/{product_id}")
    public ResponseEntity<ProductEntity> getOneProductController(@PathVariable(name = "product_id") Long id) {
        return ResponseEntity.ok().body(productService.filterByProductIdService(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> putProductController(@PathVariable Long id, @RequestBody ProductEntity entity) throws BadRequestException {
        entity.setId(id);
        return ResponseEntity.accepted().body(productService.putProductService(entity));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductController(@PathVariable Long id){
        productService.deleteProductsService(id);
        return ResponseEntity.noContent().build();
    }
}
