package org.example.lab2back.entity;

import org.example.lab2back.utils.AbstractEntity;

import java.util.UUID;


public class CategoryEntity extends AbstractEntity {
    private final String name;

    public CategoryEntity(UUID id, String name) {
        super.setId(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
