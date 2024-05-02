package com.kh.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;

@Mapper
public interface BoardMapper {
	List<BoardDTO> getList(Criteria cri);
	long getTotal(Criteria cri);
	long getLastNum(String userid);
	BoardDTO getBoardByNum(long boardnum);
}
