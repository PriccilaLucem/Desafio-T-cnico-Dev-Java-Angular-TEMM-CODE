package com.example.store.presenter;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.store.dto.ProductCategoryDTO;
import com.example.store.entities.ProductEntity;
import com.example.store.service.ProductService;

@Service
public class ProductPresenter {

    @Autowired
    private ProductService productService;

    @Transactional
    public ProductEntity createProduct(ProductCategoryDTO productDTO) {
        return productService.postProductService(productDTO);
    }

    public ProductEntity getProductById(Long id) {
        return productService.filterByProductIdService(id);
    }

    public List<ProductEntity> getAllProducts() {
        return productService.filterAllProductsService();
    }

    public void deleteProduct(Long id) {
        productService.deleteProductsService(id);
    }

    @Transactional
    public ProductEntity updateProduct(ProductCategoryDTO product, Long id) throws BadRequestException {
        return productService.putProductService(product, id);
    }

    public List<ProductEntity> getProductsByName(String name) {
        return productService.filterByNomeProductService(name);
    }
}
