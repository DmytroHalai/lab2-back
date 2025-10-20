package org.example.lab2back.controller;

import jakarta.validation.Valid;
import org.example.lab2back.docs.CategoryControllerDocs;
import org.example.lab2back.entity.CategoryEntity;
import org.example.lab2back.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryControllerDocs {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    @GetMapping("")
    public ResponseEntity<List<CategoryEntity>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> getCategoryById(@Valid @PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    @Override
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryEntity> createCategory(@Valid @RequestBody CategoryEntity category) {
        CategoryEntity newCategory = categoryService.createCategory(category);
        return ResponseEntity
                 .created(URI.create("/category/" + newCategory.getId()))
                 .body(newCategory);
    }
}
