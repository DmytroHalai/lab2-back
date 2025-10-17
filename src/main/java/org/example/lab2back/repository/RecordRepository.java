package org.example.lab2back.repository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.example.lab2back.entity.RecordEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.example.lab2back.bd.Initializer.setTestRecords;

@Repository
public class RecordRepository {
    private final List<RecordEntity> records;

    public RecordRepository(List<RecordEntity> records) {
        this.records = records;
    }

    @PostConstruct
    public void init() {
        records.addAll(setTestRecords());
    }

    public List<RecordEntity> getAllRecords() {
        System.out.println(records);
        return records;
    }

    public void addRecord(RecordEntity oneRecord) {
        records.add(oneRecord);
    }

    public List<RecordEntity> getRecordsByUserId(UUID userId) {
        return records.stream()
                .filter(oneRecord -> oneRecord.getUserId().equals(userId))
                .toList();
    }

    public List<RecordEntity> getRecordsByCategoryId(UUID categoryId) {
        return records.stream()
                .filter(oneRecord -> oneRecord.getCategoryId().equals(categoryId))
                .toList();
    }

    public List<RecordEntity> getRecordsByUserIdAndCategoryId(UUID userId, UUID categoryId) {
        return records.stream()
                .filter(oneRecord -> oneRecord.getUserId().equals(userId) && oneRecord.getCategoryId().equals(categoryId))
                .toList();
    }

    public void deleteRecordById(UUID id) {
        if (!records.removeIf(oneRecord -> oneRecord.getId().equals(id)))
            throw new EntityNotFoundException("Record by id: " + id + " not found");
    }

    public Optional<RecordEntity> getRecordById(UUID id) {
        return records.stream()
                .filter(oneRecord -> oneRecord.getId().equals(id))
                .findFirst();
    }
}
