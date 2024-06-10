package com.example.store.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.store.entities.CategoryEntity;
import com.example.store.presenter.CategoryPresenter;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryPresenter categoryPresenter;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostCategoryController() throws Exception {
        CategoryEntity category = new CategoryEntity();
        category.setDescricao("DESCRICAO");
        category.setNome("NOME");

        when(categoryPresenter.createCategory(any(CategoryEntity.class))).thenReturn(category);

        mockMvc.perform(post("/api/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(category.getNome()))
                .andExpect(jsonPath("$.descricao").value(category.getDescricao()));
    }

    @Test
    void testGetAllCategoriesController() throws Exception {
        CategoryEntity category = new CategoryEntity();
        category.setDescricao("DESCRICAO");
        category.setNome("NOME");

        List<CategoryEntity> categories = Arrays.asList(category);

        when(categoryPresenter.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/category")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value(category.getNome()));
    }

    @Test
    void testGetOneCategoryController() throws Exception {
        CategoryEntity category = new CategoryEntity();
        category.setId(1L);
        category.setDescricao("DESCRICAO");
        category.setNome("NOME");

        when(categoryPresenter.getCategoriesByName(category.getNome())).thenReturn(Collections.singletonList(category));

        mockMvc.perform(get("/api/category/search")
                .param("nome", category.getNome())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(category.getId()))
                .andExpect(jsonPath("$[0].nome").value(category.getNome()))
                .andExpect(jsonPath("$[0].descricao").value(category.getDescricao()));
    }

    @Test
    void testPutCategoryEntity() throws Exception {
        CategoryEntity category = new CategoryEntity();
        category.setId(1L);
        category.setDescricao("UPDATED_DESCRICAO");
        category.setNome("UPDATED_NOME");

        when(categoryPresenter.updateCategory(any(CategoryEntity.class))).thenReturn(category);

        mockMvc.perform(put("/api/category/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(category.getId()))
                .andExpect(jsonPath("$.nome").value(category.getNome()))
                .andExpect(jsonPath("$.descricao").value(category.getDescricao()));
    }

    @Test
    void testDeleteCategoryController() throws Exception {
        doNothing().when(categoryPresenter).deleteCategory(anyLong());

        mockMvc.perform(delete("/api/category/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
