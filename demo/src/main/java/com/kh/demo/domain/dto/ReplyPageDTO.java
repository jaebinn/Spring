package com.kh.demo.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyPageDTO {
	private long replyCnt;
	private List<ReplyDTO> list;
}
