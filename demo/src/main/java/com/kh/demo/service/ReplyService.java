package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ReplyDTO;
import com.kh.demo.domain.dto.ReplyPageDTO;

public interface ReplyService {
	ReplyDTO regist(ReplyDTO reply);
	
	ReplyDTO getDetail(long replynum);
	//목록 가져가기
	ReplyPageDTO getList(Criteria cri, long boardnum);
	
	boolean modify(ReplyDTO reply);
	
	boolean remove(long replynum);

	
}
