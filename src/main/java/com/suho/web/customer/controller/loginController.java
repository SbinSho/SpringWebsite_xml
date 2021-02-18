package com.suho.web.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class loginController {

	@RequestMapping("/login")
	public String login() {
		
		return "customer/login";
	}
	
	
}
