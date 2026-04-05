package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FinancialRecord;

public interface RecordRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByCategory(String category);
    List<FinancialRecord> findByType(String type);
    List<FinancialRecord> findByUserUsername(String username);

}