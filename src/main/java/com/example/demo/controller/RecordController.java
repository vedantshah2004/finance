package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.FinancialRecord;
import com.example.demo.service.RecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    private final RecordService service;

    public RecordController(RecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FinancialRecord> create(@Valid @RequestBody FinancialRecord record,
                                                  Principal principal) {
        if (principal == null || principal.getName() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        FinancialRecord savedRecord = service.save(record, principal.getName());
        return ResponseEntity.ok(savedRecord);
    }

    @GetMapping
    public ResponseEntity<List<FinancialRecord>> getAll(Principal principal) {
        if (principal == null || principal.getName() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<FinancialRecord> records = service.getUserRecords(principal.getName());
        return ResponseEntity.ok(records);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Principal principal) {
        if (principal == null || principal.getName() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boolean deleted = service.deleteByIdAndUsername(id, principal.getName());
        if (!deleted) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.noContent().build();
    }
}