package com.qa.rpg.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@RestController //make bean
public class RPGController {
	// add service 
	
	//requests below
	@GetMapping("/hello")
	public String hello() {
		return "Hi hi please work";
	}
}
