package com.kh.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	//@DeleteMapping : REST 방식의 설계 이용 시 삭제 요청을 받을 때 사용하는 매핑 방
	@DeleteMapping(value="{replynum}")
	public ResponseEntity<String> remove(@PathVariable("replynum") long replynum){
		return service.remove(replynum) ?
				new ResponseEntity<String>(HttpStatus.OK) :
				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//PUT
	//	모든 데이터들을 다 전달, 자원의 전체 수정, 자원 내의 모든 필드의 값을 다 전달해야 함.
	//PATCH
	//	자원의 일부 수정, 수정할 필드의 값만 전달
	@PutMapping(value="{replynum}", consumes="application/json")
	public ResponseEntity<String> modify(@RequestBody ReplyDTO reply){
		return service.modify(reply) ?
				new ResponseEntity<String>(HttpStatus.OK) :
				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}










