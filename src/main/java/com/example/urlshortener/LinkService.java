package com.example.urlshortener;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class LinkService {
    private Map<String, Link> links = new HashMap<>(); // Храним ссылки в памяти
    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int SHORT_URL_LENGTH = 6; // Длина короткой ссылки
    private SecureRandom random = new SecureRandom(); // Генератор случайных чисел

    public String shortenUrl(String longUrl, int clickLimit, int expirationHours) {
        String shortUrl;
        do {
            shortUrl = generateShortUrl(); // Генерация короткой ссылки
        } while (links.containsKey(shortUrl)); // Убедимся, что короткая ссылка уникальна

        Link link = new Link(shortUrl, longUrl, clickLimit, expirationHours); // Изменение времени жизни на часы
        links.put(shortUrl, link);
        return shortUrl;
    }

    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            shortUrl.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return shortUrl.toString();
    }

    public Link getLink(String shortUrl) {
        return links.get(shortUrl);
    }
}