package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.FinancialRecord;
import com.example.demo.repository.RecordRepository;

@Service
public class RecordService {

    private final RecordRepository repo;

    public RecordService(RecordRepository repo) {
        this.repo = repo;
    }

    public FinancialRecord save(FinancialRecord r) {
        return repo.save(r);
    }

    public List<FinancialRecord> getAll() {
        return repo.findAll();
    }
    
    
    
    public List<FinancialRecord> getUserRecords(String username) {
        return repo.findByUserUsername(username);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<FinancialRecord> filter(String category, String type) {
        return repo.findByCategory(category);
    }
}