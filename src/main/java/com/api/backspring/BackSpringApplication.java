package com.api.backspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BackSpringApplication {


	public static void main(String[] args) {
		SpringApplication.run(BackSpringApplication.class, args);
	}

	@GetMapping
	public String hello() {
		return "Hello World";
	}


}