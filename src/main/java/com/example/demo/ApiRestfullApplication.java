package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.example.demo",
		"com.example.juego"
})
public class ApiRestfullApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestfullApplication.class, args);
	}

}
