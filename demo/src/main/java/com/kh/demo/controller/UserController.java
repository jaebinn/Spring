package com.kh.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.demo.domain.dto.UserDTO;
import com.kh.demo.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user/*")
public class UserController {
	@Autowired
	private UserService service;
//	GET
//	/user/join		회원가입 페이지로 이동
//	/user/login		로그인 페이지로 이동(인덱스)
//	/user/checkId	넘겨진 파라미터로 아이디 중복 체크(성공시 O, 실패시 X 문자열 응답)
//	/user/logout	로그아웃 처리
	
//	POST
//	/user/join		넘겨진 파라미터로 회원가입 처리
//	/user/login		넘겨진 파라미터로 로그인 처리
	
	@GetMapping("join")
	public void replace() {}
	
	@GetMapping("login")
	public String login() {
		return "redirect:/";
	}
	
	@GetMapping("checkId")
	@ResponseBody
	public String checkId(String userid) {
		if(service.checkId(userid)) {
			return "O";
		}
		else {
			return "X";
		}
	}
	
	@GetMapping("logout")
	public String logout(HttpServletRequest req) {
		req.getSession().invalidate();
		return "redirect:/";
	}
	
	@PostMapping("join")
	public String join(UserDTO user,HttpServletResponse resp) {
		//회원가입 처리
		if(service.join(user)) {
			Cookie cookie = new Cookie("joinid", user.getUserid());
			cookie.setPath("/");
			cookie.setMaxAge(60);
			resp.addCookie(cookie);
		}
		else {
			//
		}
		return "redirect:/";
	}
	
	@PostMapping("login")
	public String login(String userid, String userpw, HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(service.login(userid, userpw)) {
			session.setAttribute("loginUser", userid);
			return "redirect:/board/list";
		}
		else {
			//
		}
		return "redirect:/";
	}
}










