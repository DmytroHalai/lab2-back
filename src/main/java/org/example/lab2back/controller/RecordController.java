package org.example.lab2back.controller;

import jakarta.validation.Valid;
import org.example.lab2back.entity.RecordEntity;
import org.example.lab2back.service.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {
    RecordService service;

    public RecordController(RecordService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<RecordEntity>> getRecordsByUserIdAndCategoryId(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long categoryId
    ) {
        return ResponseEntity.ok(service.getRecordsByUserIdAndCategoryId(userId, categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecordEntity> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRecordById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecordById(@PathVariable Long id) {
        try {
            service.deleteRecordById(id);
            return ResponseEntity.ok("Record deleted successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<RecordEntity> createRecord(@Valid @RequestBody RecordEntity oneRecord) {
        return ResponseEntity.ok(service.createRecord(oneRecord));
    }
}
