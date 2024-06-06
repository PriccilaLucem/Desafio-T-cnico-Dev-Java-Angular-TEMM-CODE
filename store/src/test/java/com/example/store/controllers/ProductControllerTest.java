package com.example.store.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.store.entities.ProductEntity;
import com.example.store.service.ProductService;
import com.example.store.dto.ProductCategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostProductController() throws Exception {
        ProductCategoryDTO productDTO = new ProductCategoryDTO();
        productDTO.setNome("Product");
        productDTO.setDescricao("New Product");
        productDTO.setCategoryNome("categoria");
        productDTO.setPreco(22.2);
        productDTO.setQuantity(2);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setNome(productDTO.getNome());
        productEntity.setDescricao(productDTO.getDescricao());
        productEntity.setPreco(productDTO.getPreco());
        productEntity.setQuantity(productDTO.getQuantity());

        when(productService.postProductService(any(ProductCategoryDTO.class))).thenReturn(productEntity);

        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Product"))
                .andExpect(jsonPath("$.descricao").value("New Product"));
    }

    @Test
    void testGetAllProductsController() throws Exception {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setNome("Product");
        productEntity.setDescricao("New Product");
        productEntity.setPreco(22.2);
        productEntity.setQuantity(2);

        List<ProductEntity> products = Arrays.asList(productEntity);

        when(productService.filterAllProductsService()).thenReturn(products);

        mockMvc.perform(get("/api/product")
                .param("param", "someValue")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Product"));
    }

    @Test
    void testGetOneProductController() throws Exception {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setNome("Product");
        productEntity.setDescricao("New Product");
        productEntity.setPreco(22.2);
        productEntity.setQuantity(2);

        when(productService.filterByProductIdService(1L)).thenReturn(productEntity);

        mockMvc.perform(get("/api/product/{product_id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Product"));
    }

    @Test
    void testPutProductController() throws Exception {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setNome("Updated Product");
        productEntity.setDescricao("Updated Description");
        productEntity.setPreco(25.5);
        productEntity.setQuantity(3);

        when(productService.putProductService(any(ProductEntity.class))).thenReturn(productEntity);

        mockMvc.perform(put("/api/product/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productEntity)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.nome").value("Updated Product"));
    }

    @Test
    void testDeleteProductController() throws Exception {
        doNothing().when(productService).deleteProductsService(1L);

        mockMvc.perform(delete("/api/product/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testHandleEntityNotFoundException() throws Exception {
        when(productService.filterByProductIdService(anyLong())).thenThrow(new EntityNotFoundException("Product not found"));

        mockMvc.perform(get("/api/product/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Product not found"));
    }

    @Test
    void testHandleUniqueViolationException() throws Exception {
        ProductCategoryDTO productDTO = new ProductCategoryDTO();
        productDTO.setNome("Product");
        productDTO.setDescricao("New Product");
        productDTO.setCategoryNome("categoria");
        productDTO.setPreco(22.2);
        productDTO.setQuantity(2);

        when(productService.postProductService(any(ProductCategoryDTO.class)))
                .thenThrow(new DataIntegrityViolationException("Unique constraint violation"));

        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(productDTO)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Unique constraint violation"));
    }

    @Test
    void testHandleBadRequestException() throws Exception {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setNome("Updated Product");
        productEntity.setDescricao("Updated Description");
        productEntity.setPreco(25.5);
        productEntity.setQuantity(3);

        doThrow(new BadRequestException("Bad request")).when(productService).putProductService(any(ProductEntity.class));

        mockMvc.perform(put("/api/product/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(productEntity)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Bad request"));
    }
}
