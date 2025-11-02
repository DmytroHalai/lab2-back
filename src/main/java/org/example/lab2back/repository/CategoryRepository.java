package org.example.lab2back.repository;

import org.example.lab2back.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByUserId(Long userId);

    boolean existsByUserIdAndName(Long userId, String categoryName);
}
