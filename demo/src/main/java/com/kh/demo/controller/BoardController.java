package com.kh.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.PageDTO;
import com.kh.demo.service.BoardService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping("list")
	public void list(Criteria cri, Model model) {
		System.out.println(cri);
		List<BoardDTO> list = service.getList(cri);
		model.addAttribute("list",list);
		model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
		model.addAttribute("hot_board",service.getHotBoardList(list));
		model.addAttribute("reply_cnt_list",service.getReplyCntList(list));
	}
	
	@GetMapping("write")
				//model.addAttribute() 를 바로 해줌
	public void write(@ModelAttribute("cri") Criteria cri, Model model) { }
	
	@PostMapping("write")
	public String write(BoardDTO board, MultipartFile[] files, Criteria cri) throws Exception {
		if(service.regist(board, files)) {
			long boardnum = service.getLastNum(board.getUserid());
			return "redirect:/board/get"+cri.getListLink()+"&boardnum="+boardnum;
		}
		else {
			return "redirect:/board/list"+cri.getListLink();
		}
	}
	
}












