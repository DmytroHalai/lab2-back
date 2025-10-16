package org.example.lab2back.repository;

import jakarta.annotation.PostConstruct;
import org.example.lab2back.entity.CategoryEntity;
import org.example.lab2back.utils.AbstractEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.example.lab2back.bd.Initializer.setTestCategories;

@Repository
public class CategoryRepository {
    public void save(AbstractEntity entity) {
        categoryEntityList.add((CategoryEntity) entity);
    }

    @PostConstruct
    public void init() {
        categoryEntityList.addAll(setTestCategories());
    }

    public List<CategoryEntity> categoryEntityList;

    public CategoryRepository(List<CategoryEntity> categoryEntityList) {
        this.categoryEntityList = categoryEntityList;
    }

    public List<CategoryEntity> findAll() {
        return categoryEntityList;
    }

    public CategoryEntity findById(Object id) {
        return categoryEntityList.stream().filter(category -> category.getId().equals(id)).findFirst().orElse(null);
    }

    public void deleteById(Object id) {
        categoryEntityList.removeIf(category -> category.getId().equals(id));
    }
}
