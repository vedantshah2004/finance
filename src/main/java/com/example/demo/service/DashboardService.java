package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.FinancialRecord;
import com.example.demo.repository.RecordRepository;
@Service
public class DashboardService {

    private final RecordRepository repo;

    public DashboardService(RecordRepository repo) {
        this.repo = repo;
    }

    public double totalIncome() {
        return repo.findAll().stream()
                .filter(r -> r.getType().equalsIgnoreCase("INCOME"))
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    public double totalExpense() {
        return repo.findAll().stream()
                .filter(r -> r.getType().equalsIgnoreCase("EXPENSE"))
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    public double balance() {
        return totalIncome() - totalExpense();
    }
}