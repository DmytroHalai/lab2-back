package org.example.lab2back.controller;

import jakarta.validation.Valid;
import org.example.lab2back.docs.RecordControllerDocs;
import org.example.lab2back.dto.RecordCreateDto;
import org.example.lab2back.entity.RecordEntity;
import org.example.lab2back.service.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("")
public class RecordController implements RecordControllerDocs {
    RecordService service;

    public RecordController(RecordService service) {
        this.service = service;
    }

    @GetMapping("/records")
    public ResponseEntity<List<RecordEntity>> getRecordsByUserIdAndCategoryId(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long categoryId
    ) {
        return ResponseEntity.ok(service.getRecordsByUserIdAndCategoryId(userId, categoryId));
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<RecordEntity> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRecordById(id));
    }

    @DeleteMapping("/records/{id}")
    public ResponseEntity<String> deleteRecordById(@PathVariable Long id) {
        service.deleteRecordById(id);
        return ResponseEntity.ok("Record deleted successfully");
    }

    @PostMapping("/users/{userId}/category/{categoryId}/records")
    public ResponseEntity<RecordEntity> createRecord(
            @Valid @RequestBody RecordCreateDto oneRecord,
            @PathVariable Long userId,
            @PathVariable Long categoryId) {
        RecordEntity entity = new RecordEntity(oneRecord.getAmount());
        RecordEntity newRecord = service.createRecord(entity, userId, categoryId);
        return ResponseEntity
                .created(URI.create("/record/" + newRecord.getId()))
                .body(newRecord);
    }
}
