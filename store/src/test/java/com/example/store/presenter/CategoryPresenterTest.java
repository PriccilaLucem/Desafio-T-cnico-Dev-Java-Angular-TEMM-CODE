package com.example.store.presenter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.store.entities.CategoryEntity;
import com.example.store.service.CategoryService;

import jakarta.persistence.EntityNotFoundException;

public class CategoryPresenterTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryPresenter categoryPresenter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCategory() {
        CategoryEntity category = new CategoryEntity();
        category.setNome("Electronics");
        category.setDescricao("All kinds of electronic items");

        when(categoryService.createCategory(any(CategoryEntity.class))).thenReturn(category);

        CategoryEntity createdCategory = categoryPresenter.createCategory(category);

        assertThat(createdCategory).isNotNull();
        assertThat(createdCategory.getNome()).isEqualTo("Electronics");
        assertThat(createdCategory.getDescricao()).isEqualTo("All kinds of electronic items");

        verify(categoryService, times(1)).createCategory(category);
    }

    @Test
    public void testGetCategoryByName() {
        String categoryName = "Electronics";
        CategoryEntity category = new CategoryEntity();
        category.setNome(categoryName);
        category.setDescricao("All kinds of electronic items");

        when(categoryService.getCategoryByName(categoryName)).thenReturn(category);

        CategoryEntity foundCategory = categoryPresenter.getCategoryByName(categoryName);

        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getNome()).isEqualTo(categoryName);
        assertThat(foundCategory.getDescricao()).isEqualTo("All kinds of electronic items");

        verify(categoryService, times(1)).getCategoryByName(categoryName);
    }

    @Test
    public void testGetCategoryByName_NotFound() {
        String categoryName = "NonExistingCategory";

        when(categoryService.getCategoryByName(categoryName)).thenThrow(new EntityNotFoundException("Category not found"));

        assertThatThrownBy(() -> categoryPresenter.getCategoryByName(categoryName))
            .isInstanceOf(EntityNotFoundException.class)
            .hasMessage("Category not found");

        verify(categoryService, times(1)).getCategoryByName(categoryName);
    }

    @Test
    public void testGetAllCategories() {
        List<CategoryEntity> categories = Arrays.asList(new CategoryEntity(), new CategoryEntity());

        when(categoryService.getAllCategories()).thenReturn(categories);

        List<CategoryEntity> foundCategories = categoryPresenter.getAllCategories();

        assertThat(foundCategories).isNotNull();
        assertThat(foundCategories.size()).isEqualTo(2);

        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    public void testDeleteCategory() {
        Long categoryId = 1L;
        doNothing().when(categoryService).deleteCategory(anyLong());

        categoryPresenter.deleteCategory(categoryId);

        verify(categoryService, times(1)).deleteCategory(categoryId);
    }

    @Test
    public void testUpdateCategory() throws BadRequestException {
        CategoryEntity category = new CategoryEntity();
        category.setId(1L);
        category.setNome("UpdatedName");
        category.setDescricao("UpdatedDescription");

        when(categoryService.updateCategory(any(CategoryEntity.class))).thenReturn(category);

        CategoryEntity updatedCategory = categoryPresenter.updateCategory(category);

        assertThat(updatedCategory).isNotNull();
        assertThat(updatedCategory.getId()).isEqualTo(1L);
        assertThat(updatedCategory.getNome()).isEqualTo("UpdatedName");
        assertThat(updatedCategory.getDescricao()).isEqualTo("UpdatedDescription");

        verify(categoryService, times(1)).updateCategory(category);
    }
    @Test
    public void testGetCategoriesByName() {
        String categoryName = "Electronics";
        List<CategoryEntity> categories = Arrays.asList(new CategoryEntity(), new CategoryEntity());

        when(categoryService.getCategoriesByName(categoryName)).thenReturn(categories);

        List<CategoryEntity> foundCategories = categoryPresenter.getCategoriesByName(categoryName);

        assertThat(foundCategories).isNotNull();
        assertThat(foundCategories.size()).isEqualTo(2);

        verify(categoryService, times(1)).getCategoriesByName(categoryName);
    }
}
