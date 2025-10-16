package org.example.lab2back.controller;

import org.example.lab2back.entity.RecordEntity;
import org.example.lab2back.service.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/record")
public class RecordController {
    RecordService service;

    public RecordController(RecordService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<RecordEntity>> getRecordsByUserIdAndCategoryId(
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) UUID categoryId
    ) {
        return ResponseEntity.ok(service.getRecordsByUserIdAndCategoryId(userId, categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecordEntity> getRecordById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getRecordById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecordById(@PathVariable UUID id) {
        try {
            service.deleteRecordById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<RecordEntity> createRecord(@RequestBody RecordEntity oneRecord) {
        return ResponseEntity.ok(service.createRecord(oneRecord));
    }
}
