package com.example.urlshortener;

import java.time.LocalDateTime;

public class Link {
    private String shortUrl;           // Короткая ссылка
    private String longUrl;            // Длинная ссылка
    private int clickLimit;            // Лимит переходов
    private LocalDateTime expirationDate; // Дата истечения срока действия ссылки
    private int clickCount;            // Счетчик переходов
    private String userId;             // Идентификатор создателя ссылки

    // Конструктор
    public Link(String shortUrl, String longUrl, int clickLimit, int expirationHours, String userId) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.clickLimit = clickLimit;
        this.clickCount = 0;
        this.expirationDate = LocalDateTime.now().plusHours(Math.min(expirationHours, 24));  // Ограничиваем время жизни ссылки до 24 часов
        this.userId = userId; // Указываем пользователя, создавшего ссылку
    }

    // Проверка, доступна ли ссылка
    public boolean isUsable() {
        return clickCount < clickLimit && LocalDateTime.now().isBefore(expirationDate);
    }

    // Инкремент счетчика переходов
    public void incrementClickCount() {
        clickCount++;
    }

    // Геттеры
    public String getShortUrl() {
        return shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public int getClickLimit() {
        return clickLimit;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public int getClickCount() {
        return clickCount;
    }

    public String getUserId() {
        return userId; // Возвращаем идентификатор пользователя
    }
}