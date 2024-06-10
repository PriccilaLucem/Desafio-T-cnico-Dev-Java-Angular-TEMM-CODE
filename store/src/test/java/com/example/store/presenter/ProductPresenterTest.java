package com.example.store.presenter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.store.dto.ProductCategoryDTO;
import com.example.store.entities.ProductEntity;
import com.example.store.service.ProductService;


public class ProductPresenterTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductPresenter productPresenter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        ProductCategoryDTO productDTO = new ProductCategoryDTO();
        ProductEntity product = new ProductEntity();
        when(productService.postProductService(any(ProductCategoryDTO.class))).thenReturn(product);

        ProductEntity createdProduct = productPresenter.createProduct(productDTO);

        assertThat(createdProduct).isNotNull();
        verify(productService, times(1)).postProductService(productDTO);
    }

    @Test
    public void testGetProductById() {
        Long productId = 1L;
        ProductEntity product = new ProductEntity();
        when(productService.filterByProductIdService(anyLong())).thenReturn(product);

        ProductEntity foundProduct = productPresenter.getProductById(productId);

        assertThat(foundProduct).isNotNull();
        verify(productService, times(1)).filterByProductIdService(productId);
    }

    @Test
    public void testGetAllProducts() {
        List<ProductEntity> products = Arrays.asList(new ProductEntity(), new ProductEntity());
        when(productService.filterAllProductsService()).thenReturn(products);

        List<ProductEntity> allProducts = productPresenter.getAllProducts();

        assertThat(allProducts).isNotNull();
        assertThat(allProducts.size()).isEqualTo(2);
        verify(productService, times(1)).filterAllProductsService();
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;
        doNothing().when(productService).deleteProductsService(anyLong());

        productPresenter.deleteProduct(productId);

        verify(productService, times(1)).deleteProductsService(productId);
    }

    @Test
    public void testUpdateProduct() throws BadRequestException {
        ProductCategoryDTO productDTO = new ProductCategoryDTO();
        Long productId = 1L;
        ProductEntity product = new ProductEntity();
        when(productService.putProductService(any(ProductCategoryDTO.class), anyLong())).thenReturn(product);

        ProductEntity updatedProduct = productPresenter.updateProduct(productDTO, productId);

        assertThat(updatedProduct).isNotNull();
        verify(productService, times(1)).putProductService(productDTO, productId);
    }

    @Test
    public void testGetProductsByName() {
        String productName = "TestProduct";
        List<ProductEntity> products = Arrays.asList(new ProductEntity(), new ProductEntity());
        when(productService.filterByNomeProductService(anyString())).thenReturn(products);

        List<ProductEntity> foundProducts = productPresenter.getProductsByName(productName);

        assertThat(foundProducts).isNotNull();
        assertThat(foundProducts.size()).isEqualTo(2);
        verify(productService, times(1)).filterByNomeProductService(productName);
    }
}
