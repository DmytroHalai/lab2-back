package org.example.lab2back.service;

import org.example.lab2back.entity.CategoryEntity;
import org.example.lab2back.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(CategoryEntity category) {
        categoryRepository.save(category);
    }


    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }

    public CategoryEntity getById(UUID id) {
        return categoryRepository.findById(id);
    }
}
