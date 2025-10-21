package org.example.lab2back.repository;

import jakarta.persistence.EntityNotFoundException;
import org.example.lab2back.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {}
