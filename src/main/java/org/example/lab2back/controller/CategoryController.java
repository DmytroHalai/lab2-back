package org.example.lab2back.controller;

import jakarta.validation.Valid;
import org.example.lab2back.entity.CategoryEntity;
import org.example.lab2back.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryEntity>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> getCategoryById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryEntity> createCategory(@Valid @RequestBody CategoryEntity category) {
        CategoryEntity newCategory = categoryService.createCategory(category);
        return ResponseEntity
                 .created(URI.create("/category/" + newCategory.getId()))
                 .body(newCategory);
    }
}
