package org.example.lab2back.controller;

import jakarta.validation.Valid;
import org.example.lab2back.dto.UserCreateDto;
import org.example.lab2back.entity.UserEntity;
import org.example.lab2back.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("")
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserCreateDto dto) {
        UserEntity newUser = userService.createUser(
                dto.getUsername(),
                dto.getPassword(),   // This was missing!
                dto.getCurrency()
        );
        return ResponseEntity
                .created(URI.create("/users/" + newUser.getId()))
                .body(newUser);
    }

    @PatchMapping("/{id}/currency")
    public ResponseEntity<UserEntity> updateUserCurrency(
            @PathVariable Long id,
            @RequestParam String currencyName) {
        UserEntity updated = userService.setCurrency(id, currencyName);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/ttt")
    public ResponseEntity<Void> deleteCurrencies() {
        userService.deleteAllCurrencies();
        return ResponseEntity.noContent().build();
    }
}
