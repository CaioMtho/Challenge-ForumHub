package com.alura.forum.api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		loadEnv();
		SpringApplication.run(ApiApplication.class, args);
	}

	static private void loadEnv(){
		Dotenv dotenv = Dotenv.load();
		try {
			System.setProperty("spring.datasource.url", Objects.requireNonNull(dotenv.get("DB_URL"), "DB_URL faltando em .env"));
			System.setProperty("spring.datasource.username", Objects.requireNonNull(dotenv.get("DB_USERNAME"), "DB_USERNAME faltando em .env"));
			System.setProperty("spring.datasource.password", Objects.requireNonNull(dotenv.get("DB_PASSWORD"), "DB_PASSWORD faltando em .env"));
			var secret = dotenv.get("API_SECRET");
			if (secret != null) {
				System.setProperty("api.security.token.secret", secret);
			}
		} catch (NullPointerException e) {
			throw new IllegalStateException("Falha ao carregar variaveis do arquivo env: ", e);
		}
	}

}
