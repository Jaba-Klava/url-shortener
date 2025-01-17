package com.example.urlshortener;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class LinkService {
    private Map<String, Link> links = new HashMap<>();
    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int SHORT_URL_LENGTH = 6;
    private SecureRandom random = new SecureRandom();

    public String shortenUrl(String longUrl, int clickLimit, int expirationHours, User user) {
        String shortUrl;
        do {
            shortUrl = generateShortUrl();
        } while (links.containsKey(shortUrl));

        Link link = new Link(shortUrl, longUrl, clickLimit, expirationHours, user.getId());
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

    @Scheduled(fixedRate = 3600000) // Проверка каждую час
    public void removeExpiredLinks() {
        links.values().removeIf(link -> !link.isUsable());
    }
}