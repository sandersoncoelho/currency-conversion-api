package org.example.currencyconversionapi.repository;

import org.example.currencyconversionapi.model.CurrencyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyTransactionRepository extends
        JpaRepository<CurrencyTransaction, Long> {

    List<CurrencyTransaction> findByUserId(Long userId);
}
