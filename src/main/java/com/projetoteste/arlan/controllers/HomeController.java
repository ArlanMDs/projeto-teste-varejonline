package com.projetoteste.arlan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
//	@RequestMapping("/")
//	public String home1() {
//		return "/home";
//	}
	
	@RequestMapping("/home")
	public String home() {
		return "/home";
	}

	@RequestMapping("/login")
	public String login() {
		return "/login";
	}
	
}
