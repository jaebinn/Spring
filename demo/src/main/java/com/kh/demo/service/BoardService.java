package com.kh.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.FileDTO;

public interface BoardService {
	boolean regist(BoardDTO board, MultipartFile[] files) throws Exception;
	
	BoardDTO getDetail(long boardnum);
	List<BoardDTO> getList(Criteria cri);
	long getTotal(Criteria cri);
	long getLastNum(String userid);
	ArrayList<String> getHotBoardList(List<BoardDTO> list);
	ArrayList<Integer> getReplyCntList(List<BoardDTO> list);
	
	boolean modify(BoardDTO board, MultipartFile[] files, String updateCnt) throws Exception;
	boolean increaseReadCount(long boardnum);
	
	boolean remove(long boardnum);
	
	List<FileDTO> getFiles(long boardnum);
	
}






