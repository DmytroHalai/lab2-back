package org.example.lab2back.service;

import org.example.lab2back.entity.RecordEntity;
import org.example.lab2back.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecordService {
    RecordRepository repository;

    public RecordService(RecordRepository repository) {
        this.repository = repository;
    }


    public List<RecordEntity> getRecordsByUserIdAndCategoryId(UUID userId, UUID categoryId) {
        if (userId != null && categoryId != null) {
            return repository.getRecordsByUserIdAndCategoryId(userId, categoryId);
        } else if (userId != null) {
            return repository.getRecordsByUserId(userId);
        } else if (categoryId != null) {
            return repository.getRecordsByCategoryId(categoryId);
        } else {
            return repository.getAllRecords();
        }
    }

    public RecordEntity getRecordById(UUID id) {
        return repository.getAllRecords().stream()
                .filter(oneRecord -> oneRecord.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteRecordById(UUID id) {
        repository.getAllRecords().stream()
                .filter(oneRecord -> oneRecord.getId().equals(id))
                .findFirst()
                .ifPresent(repository::deleteRecord);
    }

    public RecordEntity createRecord(RecordEntity oneRecord) {
        repository.addRecord(oneRecord);
        return oneRecord;
    }
}
