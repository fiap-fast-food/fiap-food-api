package com.fiap.food.core.repository;

import com.fiap.food.core.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
     Optional<CategoryEntity> findByName(String name);
}
