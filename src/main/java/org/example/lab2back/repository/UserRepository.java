package org.example.lab2back.repository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.example.lab2back.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.example.lab2back.bd.Initializer.setTestUsers;

@Repository
public class UserRepository {
    private final List<UserEntity> users;

    public UserRepository(List<UserEntity> users) {
        this.users = users;
    }

    @PostConstruct
    public void init() {
        users.addAll(setTestUsers());
    }

    public void save(UserEntity entity) {
        users.add(entity);
    }

    public Optional<UserEntity> findById(UUID id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public void deleteById(Object o) {
        if (!users.removeIf(user -> user.getId().equals(o)))
            throw new EntityNotFoundException("User by id: " + o + " not found");
    }

    public List<UserEntity> findAll() {
        return users;
    }
}
