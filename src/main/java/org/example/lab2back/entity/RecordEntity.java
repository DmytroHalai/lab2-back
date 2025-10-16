package org.example.lab2back.entity;

import org.example.lab2back.utils.AbstractEntity;

import java.util.UUID;

public class RecordEntity extends AbstractEntity {
    private final UUID categoryId;
    private final UUID userId;
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
