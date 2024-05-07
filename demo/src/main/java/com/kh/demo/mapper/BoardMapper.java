package com.kh.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;

@Mapper
public interface BoardMapper {
	//insert
	int insertBoard(BoardDTO board);
	
	List<BoardDTO> getList(Criteria cri);
	long getTotal(Criteria cri);
	long getLastNum(String userid);
	BoardDTO getBoardByNum(long boardnum);

	int updateBoard(BoardDTO board);

	//@Param : MyBatis에 일반 변수로 두 개 이상을 넘길 때, XML쪽에서 사용하기 위해 @Param 어노테이션을 이용해서 name을 달아준다.
	int updateReadCount(@Param("boardnum")long boardnum,@Param("readcount") int readcount);
	
	int deleteBoard(long boardnum);
}
