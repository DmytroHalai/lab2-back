package org.example.lab2back.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.lab2back.utils.AbstractEntity;

import java.util.UUID;


public class CategoryEntity extends AbstractEntity {
    @NotBlank(message = "Category name cannot be null")
    private final String name;

    public CategoryEntity(UUID id, String name) {
        super.setId(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
