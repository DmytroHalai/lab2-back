package org.example.lab2back.dto;

import jakarta.validation.constraints.Positive;

public class RecordCreateDto {
    @Positive
    private double amount;

    public double getAmount() {
        return amount;
    }
}
