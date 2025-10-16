package org.example.lab2back.bd;

import org.example.lab2back.entity.CategoryEntity;
import org.example.lab2back.entity.RecordEntity;
import org.example.lab2back.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Initializer {
    private static final UUID userId1 = UUID.randomUUID();
    private static final UUID userId2 = UUID.randomUUID();
    public static final UUID categoryId1 = UUID.randomUUID();
    public static final UUID categoryId2 = UUID.randomUUID();
    public static final UUID categoryId3 = UUID.randomUUID();

    public static List<CategoryEntity> setTestCategories() {
        return List.of(
                new CategoryEntity(categoryId1, "Category 1"),
                new CategoryEntity(categoryId2, "Category 2"),
                new CategoryEntity(categoryId3, "Category 3")
        );
    }

    public static List<RecordEntity> setTestRecords() {
        return List.of(
                new RecordEntity(UUID.randomUUID(), categoryId1, userId1, 100.0),
                new RecordEntity(UUID.randomUUID(), categoryId2, userId1, 200.0),
                new RecordEntity(UUID.randomUUID(), categoryId3, userId1, 300.0),
                new RecordEntity(UUID.randomUUID(), categoryId1, userId2, 400.0),
                new RecordEntity(UUID.randomUUID(), categoryId2, userId2, 500.0),
                new RecordEntity(UUID.randomUUID(), categoryId3, userId2, 600.0)
        );
    }

    public static List<UserEntity> setTestUsers() {
        List<UserEntity> users = new ArrayList<>();
        users.add(new UserEntity(userId1, "user1"));
        users.add(new UserEntity(userId2, "user2"));
        return users;
    }
}
