package org.example.lab2back.repository;

import org.example.lab2back.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
    List<RecordEntity> getRecordsByUserIdAndCategoryId(Long userId, Long categoryId);

    List<RecordEntity> getRecordsByUserId(Long userId);

    List<RecordEntity> getRecordsByCategoryId(Long categoryId);

    Optional<RecordEntity> getRecordById(Long id);

    void deleteRecordById(Long id);
}
