package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWithBasicSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithBasicSecurityApplication.class, args);
		System.out.println("Spring Boot basic Security running on port 8080 running...");
	}

}


