package org.example.lab2back.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.lab2back.utils.AbstractEntity;

import java.util.UUID;

public class UserEntity extends AbstractEntity {
    @NotBlank(message = "Username cannot be null")
    private final String username;

    public UserEntity(UUID id, String username) {
        super.setId(id);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
