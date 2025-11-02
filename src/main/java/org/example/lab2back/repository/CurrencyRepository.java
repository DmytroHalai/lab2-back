package org.example.lab2back.repository;

import org.example.lab2back.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {
    CurrencyEntity findByName(String usd);

    boolean existsByName(String currencyName);
}
