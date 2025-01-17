package com.example.urlshortener;

import java.util.UUID;

public class User {
    private String id; // Уникальный идентификатор пользователя
    private String name; // Имя пользователя

    public User(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString(); // Генерация уникального ID
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}