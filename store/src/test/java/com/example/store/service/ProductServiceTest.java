package com.example.store.service;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.store.entities.ProductEntity;
import com.example.store.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

public class ProductServiceTest {
    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test 
    public void testPostProductService(){
        ProductEntity product = new ProductEntity();
        product.setNome("Product");
        product.setDescricao("New Product");
        product.setPreco(22.2);
        product.setQuantity(2);
        

        when(productRepository.save(product)).thenReturn(product);

        ProductEntity savedProduct = productService.postProductService(product);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getNome()).isEqualTo("Product");
        assertThat(savedProduct.getDescricao()).isEqualTo("New Product");
        assertThat(savedProduct.getPreco()).isEqualTo(22.2);
        assertThat(savedProduct.getQuantity()).isEqualTo(2);

        verify(productRepository, times(1)).save(product);
    }

    @Test 
    public void testFilterByProductId(){
        Long productId = 1L;
        ProductEntity product = new ProductEntity();
        product.setId(productId);
        product.setNome("Electronics");
        product.setDescricao("All kinds of electronic items");
        product.setPreco(22.2);
        product.setQuantity(2);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        productService.filterByProductNameService(productId);
        
        verify(productRepository, times(1)).findById(productId);
    }
    @Test
    public void testFilterAllCategoriesService() {
        List<ProductEntity> products = new ArrayList<>();
        
        products.add(new ProductEntity());
        products.add(new ProductEntity());
        products.add(new ProductEntity());

        when(productRepository.findAll()).thenReturn(products);

        List<ProductEntity> foundProducts = productService.filterAllProductsService();

        assertThat(foundProducts).isNotNull();
        assertThat(foundProducts.size()).isEqualTo(3);

        verify(productRepository, times(1)).findAll();
    }
    @Test
    public void testDeleteProductService() throws Exception{
        final Long ID = 223L;
        assertThrows(EntityNotFoundException.class,() -> productService.deleteProductsService(ID));
        verify(productRepository, never()).deleteById(idCaptor.capture());
    }

    @Test
    public void testPutProductService() throws BadRequestException{
        ProductEntity product = new ProductEntity();
        product.setId(1L);

        when(productRepository.save(product)).thenReturn(product);

        ProductEntity updatedProduct = productService.putCategoryService(product);

        assertThat(updatedProduct).isNotNull();
        assertThat(updatedProduct.getId()).isEqualTo(1L);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testPutProductService_InvalidProduct(){
        ProductEntity product = new ProductEntity();

        assertThatThrownBy(() -> productService.putCategoryService(product))
        .isInstanceOf(BadRequestException.class)
        .hasMessage("Product does not exist");
    }
}
