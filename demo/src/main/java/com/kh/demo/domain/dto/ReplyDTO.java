package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ReplyDTO {
	private long replynum;
	private String replycontents;
	private String regdate;
	private String updatedate;
	private long boardnum;
	private String userid;
	
	
}
