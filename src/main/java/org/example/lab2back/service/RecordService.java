package org.example.lab2back.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.lab2back.entity.CategoryEntity;
import org.example.lab2back.entity.RecordEntity;
import org.example.lab2back.entity.UserEntity;
import org.example.lab2back.repository.CategoryRepository;
import org.example.lab2back.repository.RecordRepository;
import org.example.lab2back.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    RecordRepository repository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    public RecordService(RecordRepository repository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<RecordEntity> getRecordsByUserIdAndCategoryId(Long userId, Long categoryId) {
        if (userId == null && categoryId == null)
            throw new IllegalArgumentException("At least one parameter must be provided");
        if (userId != null && !userRepository.existsById(userId)) throw new EntityNotFoundException("User not found");
        if (categoryId != null && !categoryRepository.existsById(categoryId))
            throw new EntityNotFoundException("Category not found");
        if (userId != null && categoryId != null) return repository.findByUserIdAndCategoryId(userId, categoryId);
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

    public RecordEntity createRecord(RecordEntity oneRecord, Long userId, Long categoryId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        if (!category.getUser().getId().equals(userId))
            throw new IllegalArgumentException("Category doesn't belong to user");

        RecordEntity newRecord = new RecordEntity(oneRecord.getAmount());
        newRecord.setUser(user);
        newRecord.setCategory(category);
        return repository.save(newRecord);
    }

}
