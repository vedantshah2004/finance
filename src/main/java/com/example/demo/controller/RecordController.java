package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.FinancialRecord;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RecordService;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    private final RecordService service;

    public RecordController(RecordService service) {
        this.service = service;
    }
    
    @Autowired
    public UserRepository userrepo;

    
    
    
    @PostMapping
    public FinancialRecord create(@RequestBody FinancialRecord r, @RequestParam String username) {

        User user = userrepo.findByUsername(username)
    				.orElseThrow(() -> new RuntimeException("User not found"));

        r.setUser(user); 

        return service.save(r);
    } 
    


    
    @GetMapping
    public List<FinancialRecord> getAll(@RequestParam String username) {
        return service.getUserRecords(username);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}