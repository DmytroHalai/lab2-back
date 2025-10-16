package org.example.lab2back.entity;

import org.example.lab2back.utils.AbstractEntity;

import java.util.UUID;

public class UserEntity extends AbstractEntity {
    private final String username;

    public UserEntity(UUID id, String username) {
        super.setId(id);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
