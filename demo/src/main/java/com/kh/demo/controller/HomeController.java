package com.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String home(HttpServletRequest req,Model model) {
		//데이터를 들고 index.html로 이동할 수 있는 선택지가 생김
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("joinid")) {
					model.addAttribute("joinid",cookie.getValue());
					break;
				}
			}
		}
		return "index";
	}
}
