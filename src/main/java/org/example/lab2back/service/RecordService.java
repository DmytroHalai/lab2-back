package org.example.lab2back.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.lab2back.entity.CategoryEntity;
import org.example.lab2back.entity.CurrencyEntity;
import org.example.lab2back.entity.RecordEntity;
import org.example.lab2back.entity.UserEntity;
import org.example.lab2back.repository.CategoryRepository;
import org.example.lab2back.repository.CurrencyRepository;
import org.example.lab2back.repository.RecordRepository;
import org.example.lab2back.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    RecordRepository repository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;
    CurrencyRepository currencyRepository;

    public RecordService(RecordRepository repository, UserRepository userRepository, CategoryRepository categoryRepository, CurrencyRepository currencyRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.currencyRepository = currencyRepository;
    }

    public List<RecordEntity> getRecordsByUserIdAndCategoryId(Long userId, Long categoryId) {
        if (userId == null && categoryId == null)
            throw new IllegalArgumentException("At least one parameter must be provided");
        if (userId != null && !userRepository.existsById(userId)) throw new EntityNotFoundException("User not found");
        if (categoryId != null && !categoryRepository.existsById(categoryId))
            throw new EntityNotFoundException("Category not found");
        if (userId != null && categoryId != null) {
            UserEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not fount"));
            CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category  not found"));
            if (!user.getCategories().contains(category)) throw new IllegalArgumentException("Category doesn't belong to user");
        }
        return userId != null
                ? repository.findByUserId(userId)
                : repository.findByCategoryId(categoryId);
    }

    public RecordEntity getRecordById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Record by id: " + id + " not found"));
    }

    public void deleteRecordById(Long id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Record not found");
        repository.deleteById(id);
    }

    public RecordEntity createRecord(double amount, String currency, Long userId, Long categoryId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        CurrencyEntity currencyEntity = currencyRepository.findByName(currency);
        if (currency == null || currency.trim().isEmpty()) currencyEntity = user.getCurrencyEntity();
        else if (currencyEntity == null) currencyEntity = new CurrencyEntity(currency);
        currencyRepository.save(currencyEntity);
        RecordEntity newRecord = new RecordEntity(amount, currencyEntity);
        newRecord.setUser(user);
        newRecord.setCategory(category);
        return repository.save(newRecord);
    }

    public RecordEntity updateCurrency(Long recordId, String currencyName) {
        if (currencyName == null) throw new IllegalArgumentException("Currency name must not be null");
        RecordEntity newRecord = repository.findById(recordId)
                .orElseThrow(() -> new EntityNotFoundException("Record not found"));
        newRecord.setCurrencyEntity(new CurrencyEntity(currencyName));
        return repository.save(newRecord);
    }
}
