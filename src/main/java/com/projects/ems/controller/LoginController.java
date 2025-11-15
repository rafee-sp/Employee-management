package com.projects.ems.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projects.ems.entity.User;
import com.projects.ems.service.UserService;


@RestController
public class LoginController {


	private UserService userService;
	
	public LoginController(UserService userService) {
		this.userService = userService;
	}

		
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		
		String response = userService.userLogin(user);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}

}
