package com.kh.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ReplyDTO;
import com.kh.demo.domain.dto.ReplyPageDTO;
import com.kh.demo.service.ReplyService;

@RequestMapping("/reply/*")
@RestController
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	//ResponseEntity : 서버의 상태코드, 응답 메세지, 응답 데이터 등을 담을 수 있는 타입
	//consumes : 이 메소드가 호출될 때 소비할 데이터의 타입(넘겨지는 RequestBody의 데이터 타입)
	//@RequestBody : 넘겨지는 body의 내용을 해석해서 해당 파라미터에 채워넣기
	//produces : 이 메소드가 호출될 때 생성할 데이터의 타입(돌려주는 ResponseBody의 데이터 타입)
	@PostMapping(value="regist", consumes = "application/json", produces = "application/json;charset=utf-8")
	public ResponseEntity<ReplyDTO> regist(@RequestBody ReplyDTO reply){
		ReplyDTO result = service.regist(reply);
		System.out.println(result);
		if(result == null) {
			return new ResponseEntity<ReplyDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
			return new ResponseEntity<ReplyDTO>(result,HttpStatus.OK);
		}
	}
	
	// /reply/list/3/1 : 3번 게시글의 1 페이지 댓글 리스트
	@GetMapping(value = "list/{boardnum}/{pagenum}")
	public ResponseEntity<ReplyPageDTO> getList(
			@PathVariable("boardnum")long boardnum,
			@PathVariable("pagenum")int pagenum
	){
		Criteria cri = new Criteria(pagenum, 5);
		return new ResponseEntity<ReplyPageDTO>(service.getList(cri,boardnum), HttpStatus.OK);
	}
}










