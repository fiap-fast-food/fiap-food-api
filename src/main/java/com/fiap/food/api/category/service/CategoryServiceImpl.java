package com.fiap.food.api.category.service;

import com.fiap.food.api.assembler.CategoryMapper;
import com.fiap.food.api.category.dto.CategoryRequest;
import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.CategoryEntity;
import com.fiap.food.core.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public CategoryEntity insert(CategoryRequest category) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(category);
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public void update(CategoryRequest category) throws NotFoundException {
        var categoryEntity = categoryMapper.toEntity(category);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity find(String categoryName) throws NotFoundException {
        var category = categoryRepository.findByName(categoryName).orElseThrow(() -> new NotFoundException("Category not Found"));
        return category;
    }

    @Override
    public CategoryEntity find(Long id) throws NotFoundException {
        CategoryEntity category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not Found"));
        return category;
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        categoryRepository.deleteById(id);
    }
}
