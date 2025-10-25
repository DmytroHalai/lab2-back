package org.example.lab2back.repository;

import org.example.lab2back.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
    List<RecordEntity> findByUserIdAndCategoryId(Long userId, Long categoryId);

    List<RecordEntity> findByUserId(Long userId);

    List<RecordEntity> findByCategoryId(Long categoryId);
}
