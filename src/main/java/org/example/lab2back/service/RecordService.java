package org.example.lab2back.service;

import org.example.lab2back.entity.RecordEntity;
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
        if (userId != null && categoryId != null) return repository.getRecordsByUserIdAndCategoryId(userId, categoryId);
        else if (userId != null) return repository.getRecordsByUserId(userId);
        else if (categoryId != null) return repository.getRecordsByCategoryId(categoryId);
        else throw new IllegalArgumentException("At least one parameter must be provided");
    }

    public RecordEntity getRecordById(Long id) {
        return repository
                .getRecordById(id)
                .orElseThrow(() -> new IllegalArgumentException("Record by id: " + id + " not found"));
    }

    public void deleteRecordById(Long id) {
        repository.deleteRecordById(id);
    }

    public RecordEntity createRecord(RecordEntity oneRecord) {
        if (checkIsValid(oneRecord)) throw new IllegalArgumentException("Input ids are not valid");
        RecordEntity newRecord = new RecordEntity(oneRecord.getCategoryId(), oneRecord.getUserId(), oneRecord.getAmount());
        repository.save(newRecord);
        return newRecord;
    }

    private boolean checkIsValid(RecordEntity oneRecord) {
        return userRepository.findById(oneRecord.getUserId()).isEmpty() ||
                categoryRepository.findById(oneRecord.getCategoryId()).isEmpty();
    }
}
