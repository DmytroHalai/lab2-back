package org.example.lab2back.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.lab2back.entity.UserEntity;
import org.example.lab2back.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserByID(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User by id: " + id +" not found"));
    }

    public UserEntity createUser(UserEntity user) {
        UserEntity newUser = new UserEntity(UUID.randomUUID(), user.getUsername());
        userRepository.save(newUser);
        return newUser;
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
