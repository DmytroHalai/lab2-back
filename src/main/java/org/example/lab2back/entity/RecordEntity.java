package org.example.lab2back.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference("category-record")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-record")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyEntity currencyEntity;
    @Column(nullable = false)
    @NotNull
    @Positive
    private double amount;

    public RecordEntity(double amount, CurrencyEntity currencyEntity) {
        this.amount = amount;
        this.currencyEntity = currencyEntity;
    }

    public RecordEntity() {

    }

    public CurrencyEntity getCurrencyEntity() {
        return currencyEntity;
    }

    public void setCurrencyEntity(CurrencyEntity currencyEntity) {
        this.currencyEntity = currencyEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
