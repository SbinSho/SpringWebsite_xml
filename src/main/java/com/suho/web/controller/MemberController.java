package com.suho.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@RequestMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping("/join")
	public String join() {
		return "member/join";
	}
	
	
}
