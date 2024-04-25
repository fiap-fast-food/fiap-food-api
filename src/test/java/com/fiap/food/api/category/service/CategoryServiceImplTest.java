package com.fiap.food.api.category.service;

import com.fiap.food.api.AplicationConfigTest;
import com.fiap.food.api.assembler.CategoryMapper;
import com.fiap.food.api.category.dto.CategoryRequest;
import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.CategoryEntity;
import com.fiap.food.core.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@DisplayName("CategoryServiceImplTest")
class CategoryServiceImplTest extends AplicationConfigTest {


    @MockBean
    private CategoryMapper categoryMapper;
    @MockBean
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("mustSaveCategory")
    public void mustSaveCategory() {
        Mockito.when(categoryMapper.toEntity(any())).thenReturn(getCategory());
        categoryService.insert(getCategoryRequest());
        Mockito.verify(categoryRepository, Mockito.times(1)).save(any(CategoryEntity.class));
    }

    @Test
    @DisplayName("mustUpdateCategory")
    public void mustUpdateCategory() throws NotFoundException {
        Mockito.when(categoryMapper.toEntity(any())).thenReturn(getCategory());
        categoryService.update(getCategoryRequest());
        Mockito.verify(categoryRepository, Mockito.times(1)).save(any(CategoryEntity.class));
    }

    @Test
    @DisplayName("mustFindByNameCategory")
    public void mustFindByNameCategory() throws NotFoundException {
        Mockito.when(categoryRepository.findByName(any())).thenReturn(Optional.of(getCategory()));
        categoryService.find("teste");
        Mockito.verify(categoryRepository, Mockito.times(1)).findByName(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("mustFindByIdCategory")
    public void mustFindByIdCategory() throws NotFoundException {
        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.of(getCategory()));
        categoryService.find(1L);
        Mockito.verify(categoryRepository, Mockito.times(1)).findById(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("mustDeleteByIdCategory")
    public void mustDeleteByIdCategory() throws NotFoundException {
        categoryService.delete(1L);
        Mockito.verify(categoryRepository, Mockito.times(1)).deleteById(ArgumentMatchers.any());
    }

    private CategoryEntity getCategory() {

        var category = new CategoryEntity();
        category.setName("Teste");
        category.setId(1L);
        return category;
    }
    private CategoryRequest getCategoryRequest() {
        var category = new CategoryRequest();
        category.setName("Teste");
        return category;
    }
}