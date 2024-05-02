package com.kh.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;

public class BoardServiceImpl implements BoardService{

	@Override
	public boolean regist(BoardDTO board, MultipartFile[] files) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BoardDTO getDetail(long boardnum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardDTO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLastNum(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> getHotBoardList(List<BoardDTO> list) {
		ArrayList<String> hotList = new ArrayList<>();
		for(BoardDTO board : list) {
			
		}
		return hotList;
	}

	@Override
	public ArrayList<Integer> getReplyCntList(List<BoardDTO> list) {
		ArrayList<Integer> replyCntList = new ArrayList<>();
		for(BoardDTO board : list) {
			board.getBoardnum();
		}
		return replyCntList;
	}

	@Override
	public boolean modify(BoardDTO board, MultipartFile[] files, String updateCnt) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean increaseReadCount(long boardnum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(long boardnum) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
