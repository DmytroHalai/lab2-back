package org.example.lab2back.controller;

import jakarta.validation.Valid;
import org.example.lab2back.dto.CategoryCreateDto;
import org.example.lab2back.entity.CategoryEntity;
import org.example.lab2back.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryEntity>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryEntity> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping("/users/{userId}/categories")
    public ResponseEntity<List<CategoryEntity>> getCategoriesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(categoryService.getCategoriesByUserId(userId));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    @PostMapping("/users/{userId}/categories")
    public ResponseEntity<?> createCategory(
            @PathVariable Long userId,
            @Valid @RequestBody CategoryCreateDto category) {
        CategoryEntity newCategory = categoryService.createCategory(category.getName(), userId);
        return ResponseEntity
                .created(URI.create("/categories/" + newCategory.getId()))
                .body(newCategory);
    }
}
