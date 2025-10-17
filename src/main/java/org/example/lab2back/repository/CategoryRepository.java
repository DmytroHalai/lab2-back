package org.example.lab2back.repository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.example.lab2back.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.example.lab2back.bd.Initializer.setTestCategories;

@Repository
public class CategoryRepository {
    public List<CategoryEntity> categories;

    public CategoryRepository(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public void save(CategoryEntity entity) {
        categories.add(entity);
    }

    @PostConstruct
    public void init() {
        categories.addAll(setTestCategories());
    }

    public List<CategoryEntity> findAll() {
        return categories;
    }

    public Optional<CategoryEntity> findById(Object id) {
        return categories.stream().filter(category -> category.getId().equals(id)).findFirst();
    }

    public void deleteById(Object id) {
        if (!categories.removeIf(category -> category.getId().equals(id)))
            throw new EntityNotFoundException("Category by id: " + id + " not found");
    }
}
