package org.example.lab2back.entity;

import jakarta.validation.constraints.NotNull;
import org.example.lab2back.utils.AbstractEntity;

import java.util.UUID;

public class RecordEntity extends AbstractEntity {
    @NotNull(message = "Category ID is mandatory")
    private final UUID categoryId;
    @NotNull(message = "User ID is mandatory")
    private final UUID userId;
    @NotNull(message = "Amount is mandatory")
    private final double amount;

    public RecordEntity(UUID id, UUID categoryId, UUID userId, double amount) {
        super.setId(id);
        this.categoryId = categoryId;
        this.userId = userId;
        this.amount = amount;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public UUID getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }
}
