package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class BoardDTO {
	private long boardnum;
	private String boardtitle;
	private String boardcontents;
	private String regdate;
	private String updatedate;
	private int readcount;
	private String userid;
}
