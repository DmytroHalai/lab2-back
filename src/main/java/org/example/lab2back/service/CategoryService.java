package org.example.lab2back.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.lab2back.entity.CategoryEntity;
import org.example.lab2back.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryEntity createCategory(CategoryEntity category) {
        CategoryEntity newCategory = new CategoryEntity(category.getName());
        categoryRepository.save(newCategory);
        return newCategory;
    }


    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryEntity getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category by id: " + id + " not found"));
    }
}
