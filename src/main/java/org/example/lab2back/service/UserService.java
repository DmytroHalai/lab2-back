package org.example.lab2back.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.lab2back.entity.UserEntity;
import org.example.lab2back.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserByID(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User by id: " + id + " not found"));
    }

    public UserEntity createUser(UserEntity user) {
        UserEntity newUser = new UserEntity(user.getUsername());
        userRepository.save(newUser);
        return newUser;
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) throw new EntityNotFoundException("User not found");
        userRepository.deleteById(id);
    }
}
