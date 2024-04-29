package com.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample/*")
public class SampleController {
	
//	@GetMapping("")
//	public String empty() {
//		System.out.println("======== ========");
//		return "a";
//	}
	
	@RequestMapping("basic")
	public void alsgejaselg() {
		System.out.println("========basic========");
	}
	
	@GetMapping("basic1")
	public void basicGet(int age) {
		System.out.println("========GET 방식으로 요청========");
		System.out.println("age : "+age);
	}
	
	@PostMapping("basic1")
	public void basicPost(int age) {
		System.out.println("========POST 방식으로 요청========");
		System.out.println("age : "+age);
	}
	
}





