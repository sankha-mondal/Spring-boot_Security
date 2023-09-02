package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRoleBaseSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRoleBaseSecurityApplication.class, args);
		System.out.println("Spring Boot Role Based Secutity running on port No 8080...");
	}

}
