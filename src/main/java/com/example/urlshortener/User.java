package com.example.urlshortener;

import java.util.UUID;

public class User {
    private String id;
    private String name;

    public User(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}