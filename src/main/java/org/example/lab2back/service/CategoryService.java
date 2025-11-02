package org.example.lab2back.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.lab2back.entity.CategoryEntity;
import org.example.lab2back.entity.UserEntity;
import org.example.lab2back.repository.CategoryRepository;
import org.example.lab2back.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;
    UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryEntity createCategory(String categoryName, Long userId) {
        if (categoryRepository.existsByUserIdAndName(userId, categoryName)) {
            throw new IllegalArgumentException("Category already exists");
        }
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        CategoryEntity newCategory = new CategoryEntity(categoryName);
        newCategory.setUser(user);
        return categoryRepository.save(newCategory);
    }

    public void deleteCategory(Long id) {
        if(!categoryRepository.existsById(id)) throw new EntityNotFoundException("Category not found");
        categoryRepository.deleteById(id);
    }

    public CategoryEntity getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category by id: " + id + " not found"));
    }

    public List<CategoryEntity> getCategoriesByUserId(Long userId) {
        if (!userRepository.existsById(userId)) throw new EntityNotFoundException("User not found");
        return categoryRepository.findAllByUserId(userId);
    }
}
