package com.kh.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@GetMapping("list")
	public void list(Criteria cri, Model model) {
		System.out.println(cri);
		
	}
}
