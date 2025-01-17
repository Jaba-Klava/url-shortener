package com.example.urlshortener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class UrlShortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(LinkService linkService) {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Добро пожаловать в наш сервис!");
			System.out.print("Введите имя пользователя: ");
			String name = scanner.nextLine();
			User user = new User(name);
			System.out.println("Добро пожаловать, " + user.getName() + "! Ваш уникальный ID: " + user.getId());
			System.out.println("Ссылка для удобства проверки: https://san-epidem.ru/blog/tpost/hl3y9zvxb1-klop-vonyuchka-vneshnii-vid-i-osobennost");

			while (true) {
				System.out.print("Введите длинную ссылку: ");
				String longUrl = scanner.nextLine();

				System.out.print("Введите лимит переходов: ");
				int clickLimit = scanner.nextInt();

				System.out.print("Введите время жизни ссылки в часах: ");
				int expirationHours = scanner.nextInt();
				scanner.nextLine(); // Очищаем буфер после nextInt()

				String shortUrl = linkService.shortenUrl(longUrl, clickLimit, expirationHours);
				System.out.println("Короткая ссылка: " + shortUrl);

				System.out.print("Хотите создать еще одну короткую ссылку? (да/нет): ");
				String response = scanner.nextLine();
				if (response.equalsIgnoreCase("нет")) {
					System.out.println("Выход из программы.");
					break; // Выход из цикла и завершение программы
				}
			}
		};
	}
}