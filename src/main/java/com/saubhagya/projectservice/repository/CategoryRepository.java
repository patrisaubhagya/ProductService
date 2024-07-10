package com.saubhagya.projectservice.repository;

import com.saubhagya.projectservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);

    List<Category> findAll();

    Optional<Category> getCategoryByName(String categoryName);
}
