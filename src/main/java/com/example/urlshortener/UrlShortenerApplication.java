package com.example.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;

@SpringBootApplication
public class UrlShortenerApplication {

	@Value("${link.expiration.hours:24}")
	private int defaultExpirationHours;

	@Value("${link.click.limit:10}")
	private int defaultClickLimit;

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

			while (true) {
				System.out.print("Введите длинную ссылку: ");
				String longUrl = scanner.nextLine();

				System.out.print("Введите лимит переходов (по умолчанию " + defaultClickLimit + "): ");
				String clickLimitInput = scanner.nextLine();
				int clickLimit = clickLimitInput.isEmpty() ? defaultClickLimit : Integer.parseInt(clickLimitInput);

				System.out.print("Введите время жизни ссылки в часах (по умолчанию " + defaultExpirationHours + "): ");
				String expirationInput = scanner.nextLine();
				int expirationHours = expirationInput.isEmpty() ? defaultExpirationHours : Integer.parseInt(expirationInput);

				String shortUrl = linkService.shortenUrl(longUrl, clickLimit, expirationHours, user);
				System.out.println("Короткая ссылка: " + shortUrl);

				System.out.print("Хотите создать еще одну короткую ссылку? (да/нет): ");
				String response = scanner.nextLine();

				// Условие выхода из программы
				if (response.equalsIgnoreCase("нет")) {
					System.out.println("Выход из программы.");
					break; // Выходить из цикла, когда пользователь отвечает 'нет'
				}
			}

			scanner.close(); // Закрытие сканера для освобождения ресурсов

			// Если хотите завершить приложение
			System.exit(0); // Явное завершение приложения
		};
	}
}