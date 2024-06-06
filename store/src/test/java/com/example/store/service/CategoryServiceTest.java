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

import com.example.store.entities.CategoryEntity;
import com.example.store.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;


public class CategoryServiceTest {

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPostCategoryService() {
        CategoryEntity category = new CategoryEntity();
        category.setNome("Electronics");
        category.setDescricao("All kinds of electronic items");

        when(categoryRepository.save(category)).thenReturn(category);

        CategoryEntity savedCategory = categoryService.postCategoryService(category);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getNome()).isEqualTo("Electronics");
        assertThat(savedCategory.getDescricao()).isEqualTo("All kinds of electronic items");

        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void testFilterByCategoryNameService() {
        Long categoryId = 1L;
        CategoryEntity category = new CategoryEntity();
        category.setId(categoryId);
        category.setNome("Electronics");
        category.setDescricao("All kinds of electronic items");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        CategoryEntity foundCategory = categoryService.filterByCategoryNameService(categoryId);

        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getId()).isEqualTo(categoryId);
        assertThat(foundCategory.getNome()).isEqualTo("Electronics");
        assertThat(foundCategory.getDescricao()).isEqualTo("All kinds of electronic items");

        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    public void testFilterAllCategoriesService() {
        List<CategoryEntity> categories = new ArrayList<>();
        categories.add(new CategoryEntity());
        categories.add(new CategoryEntity());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryEntity> foundCategories = categoryService.filterAllCategoriesService();

        assertThat(foundCategories).isNotNull();
        assertThat(foundCategories.size()).isEqualTo(2);

        verify(categoryRepository, times(1)).findAll();
    }

@Test
public void testDeleteCategoryService() throws Exception{
    final Long id = 223L;
    
    assertThrows(EntityNotFoundException.class, () -> categoryService.deleteCategoryService(id));
    verify(categoryRepository, never()).deleteById(idCaptor.capture());


}



    @Test
    public void testPutCategoryService() throws BadRequestException {
        CategoryEntity category = new CategoryEntity();
        category.setId(1L);

        when(categoryRepository.save(category)).thenReturn(category);

        CategoryEntity updatedCategory = categoryService.putCategoryService(category);

        assertThat(updatedCategory).isNotNull();
        assertThat(updatedCategory.getId()).isEqualTo(1L);

        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void testPutCategoryService_InvalidCategory() {
        CategoryEntity category = new CategoryEntity();

        assertThatThrownBy(() -> categoryService.putCategoryService(category))
            .isInstanceOf(BadRequestException.class)
            .hasMessage("Category does not exist");
    }
}
