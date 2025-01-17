package com.example.urlshortener;

import java.time.LocalDateTime;

public class Link {
    private String shortUrl;
    private String longUrl;
    private int clickLimit;
    private LocalDateTime expirationDate;
    private int clickCount;

    public Link(String shortUrl, String longUrl, int clickLimit, int expirationHours) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.clickLimit = clickLimit;
        this.clickCount = 0;
        this.expirationDate = LocalDateTime.now().plusHours(expirationHours); // Устанавливаем дату истечения
    }

    public boolean isUsable() {
        return clickCount < clickLimit && LocalDateTime.now().isBefore(expirationDate);
    }

    public void incrementClickCount() {
        clickCount++;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }
}