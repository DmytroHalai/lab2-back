package org.example.lab2back.repository;

import jakarta.annotation.PostConstruct;
import org.example.lab2back.entity.UserEntity;
import org.example.lab2back.utils.AbstractEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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

    public void save(AbstractEntity entity) {
        users.add((UserEntity) entity);
    }

    public UserEntity findById(UUID id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public void deleteById(Object o) {
        users.removeIf(user -> user.getId().equals(o));
    }

    public List<UserEntity> findAll() {
        return users;
    }
}
