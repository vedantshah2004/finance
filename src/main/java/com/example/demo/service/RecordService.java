package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.FinancialRecord;
import com.example.demo.model.User;
import com.example.demo.repository.RecordRepository;
import com.example.demo.repository.UserRepository;

@Service
public class RecordService {

    private final RecordRepository repo;
    private final UserRepository userRepo;

    @Autowired
    public RecordService(RecordRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public FinancialRecord save(FinancialRecord record, String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        record.setUser(user);
        return repo.save(record);
    }

    public List<FinancialRecord> getUserRecords(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        return repo.findByUserUsername(username);
    }
    
//    public void deleteByIdAndUsername(Long id, String username) {
//        FinancialRecord record = repo.findById(id)
//            .orElseThrow(() -> new RuntimeException("Record not found"));
//        if (!record.getUser().getUsername().equals(username)) {
//            throw new RuntimeException("Forbidden: You cannot delete this record");
//        }
//        repo.delete(record);
//    }
    
    public boolean deleteByIdAndUsername(Long id, String username) {
        Optional<FinancialRecord> recordOpt = repo.findById(id);
        if (recordOpt.isPresent() && recordOpt.get().getUser().getUsername().equals(username)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<FinancialRecord> getAll() {
        return repo.findAll();
    }

    public List<FinancialRecord> filter(String category, String type) {
        return repo.findByCategory(category);
    }
}