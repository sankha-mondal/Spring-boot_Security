package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySimpleController {
	
	//  http://localhost:8080/hello_user
	@GetMapping("/hello_user")
	public String getUser() {
		return "Hello User";
	}
	
	//  http://localhost:8080/hello_admin
	@GetMapping("/hello_admin")
	public String getAdmin() {
		return "Hello Admin";
	}

}
