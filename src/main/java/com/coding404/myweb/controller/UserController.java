package com.coding404.myweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
	
	@GetMapping("/userDetail")
	public String userDetail() {
		return "user/userDetail";
	}
	
	@PostMapping("/loginForm")
	public String loginForm(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session) {
		
		// 로그인처리 
		// select * from users where id =? and pw=? ;
		// null이 아니라면 로그인 성공
		session.setAttribute("user_id", "admin");
		
		return "redirect:/";
	}
}
