package com.kh.demo.service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.FileDTO;
import com.kh.demo.mapper.BoardMapper;
import com.kh.demo.mapper.FileMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Value("${file.dir}")
	private String saveFolder;
	
	@Autowired
	private BoardMapper bmapper;
	@Autowired
	private FileMapper fmapper;
	
	@Override
	public boolean regist(BoardDTO board, MultipartFile[] files) throws Exception {
		if(bmapper.insertBoard(board) != 1) {
			return false;
		}
		if(files == null || files.length == 0) {
			return true;
		}
		else {
			//방금 등록한 게시글 번호
			long boardnum = bmapper.getLastNum(board.getUserid());
			boolean flag = false;
			System.out.println("파일 개수 : "+files.length);
			
			for(int i=0;i<files.length-1;i++) {
				MultipartFile file = files[i];
				System.out.println(file.getOriginalFilename());
				
				//apple.png
				String orgname = file.getOriginalFilename();
				//5
				int lastIdx = orgname.lastIndexOf(".");
				//.png
				String extension = orgname.substring(lastIdx);
				
				LocalDateTime now = LocalDateTime.now();
				String time = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

				//20240502162130141랜덤문자열.png
				String systemname = time+UUID.randomUUID().toString()+extension;
				
				//실제 생성될 파일의 경로
				//D:/0900_GB_JDS/7_spring/file/20240502162130141랜덤문자열.png
				String path = saveFolder+systemname;
				
				//File DB 저장
				FileDTO fdto = new FileDTO();
				fdto.setBoardnum(boardnum);
				fdto.setOrgname(orgname);
				fdto.setSystemname(systemname);
				flag = fmapper.insertFile(fdto) == 1;
				
				//실제 파일 업로드
				file.transferTo(new File(path));
				
				if(!flag) {
					//업로드했던 파일 삭제, 게시글 데이터 삭제, 파일 data 삭제, ...
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public BoardDTO getDetail(long boardnum) {
		return bmapper.getBoardByNum(boardnum);
	}

	@Override
	public List<FileDTO> getFiles(long boardnum) {
		return fmapper.getFiles(boardnum);
	}
	
	@Override
	public List<BoardDTO> getList(Criteria cri) {
		return bmapper.getList(cri);
	}

	@Override
	public long getTotal(Criteria cri) {
		return bmapper.getTotal(cri);
	}

	@Override
	public long getLastNum(String userid) {
		return bmapper.getLastNum(userid);
	}

	@Override
	public ArrayList<String> getHotBoardList(List<BoardDTO> list) {
		ArrayList<String> hotList = new ArrayList<>();
		for(BoardDTO board : list) {
			hotList.add("O");
		}
		return hotList;
	}

	@Override
	public ArrayList<Integer> getReplyCntList(List<BoardDTO> list) {
		ArrayList<Integer> replyCntList = new ArrayList<>();
		for(BoardDTO board : list) {
			replyCntList.add(0);
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
