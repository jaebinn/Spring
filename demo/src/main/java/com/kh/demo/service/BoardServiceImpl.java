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
import com.kh.demo.mapper.ReplyMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Value("${file.dir}")
	private String saveFolder;
	
	@Autowired
	private BoardMapper bmapper;
	@Autowired
	private FileMapper fmapper;
	@Autowired
	private ReplyMapper rmapper;
	
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
		if(bmapper.updateBoard(board) != 1) {
			return false;
		}
		List<FileDTO> orgFileList = fmapper.getFiles(board.getBoardnum());
		if(orgFileList.size() == 0 && (files == null || files.length == 0)) {
			return true;
		}
		else {
			System.out.println("service : "+files.length);
			if(files != null && files.length != 0) {
				boolean flag = false;
				//후에 비즈니스 로직 실패 시 원래대로 복구하기 위해 업로드 성공했던 파일들도 삭제해 주어야 한다.
				//업로드 성공한 파일들의 이름을 해당 리스트에 추가하면서 로직을 진행한다.
				ArrayList<String> sysnames = new ArrayList<>();
				for(int i=0;i<files.length-1;i++) {
					MultipartFile file = files[i];
					String orgname = file.getOriginalFilename();
					//수정의 경우 중간에 있는 파일이 수정되지 않은 경우도 있다.
					//그런 경우의 file의 orgname은 null이거나 "" 이다.
					//따라서 파일 처리를 할 필요가 없으므로 반복문을 넘어간다.
					if(orgname == null || orgname.equals("")) {
						continue;
					}
					//파일 업로드 과정(regist와 동일)
					int lastIdx = orgname.lastIndexOf(".");
					String extension = orgname.substring(lastIdx);
					LocalDateTime now = LocalDateTime.now();
					String time = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
					String systemname = time+UUID.randomUUID().toString()+extension;
					
					String path = saveFolder+systemname;
					
					//File DB 저장
					FileDTO fdto = new FileDTO();
					fdto.setBoardnum(board.getBoardnum());
					fdto.setOrgname(orgname);
					fdto.setSystemname(systemname);
					flag = fmapper.insertFile(fdto) == 1;
					
					//실제 파일 업로드
					file.transferTo(new File(path));
					//업로드 성공한 파일의 이름을 sysnames에 추가
					sysnames.add(systemname);
					
					if(!flag) {
						break;
					}
				}
				//강제탈출
				if(!flag){
					//업로드했던 파일 삭제, 게시글 데이터 삭제, 파일 data 삭제, ...
					//아까 추가했던 systemname들(업로드 성공한 파일의 이름)을 꺼내오면서
					for(String systemname : sysnames){
						//실제 파일이 존재한다면 삭제
						File file = new File(saveFolder,systemname);
						if(file.exists()) {
							file.delete();
						}
						//DB에서도 삭제
						fmapper.deleteFileBySystemname(systemname);
					}
					//bmapper.deleteBoard();
				}
			}
			//지워져야 할 파일(기존에 있었던 파일들 중 수정된 파일)들의 이름 추출
			String[] deleteNames = updateCnt.split("\\\\");
			for(String systemname : deleteNames) {
				File file = new File(saveFolder,systemname);
				if(file.exists()) {
					file.delete();
				}
				fmapper.deleteFileBySystemname(systemname);
			}
			return true;
		}
	}

	@Override
	public boolean increaseReadCount(long boardnum) {
		BoardDTO board = bmapper.getBoardByNum(boardnum);
		return bmapper.updateReadCount(boardnum,board.getReadcount()+1) == 1;
	}

	@Override
	public boolean remove(long boardnum) {
		if(bmapper.deleteBoard(boardnum) == 1) {
			List<FileDTO> files = fmapper.getFiles(boardnum);
			for(FileDTO fdto : files) {
				File file = new File(saveFolder,fdto.getSystemname());
				if(file.exists()) {
					file.delete();
					fmapper.deleteFileBySystemname(fdto.getSystemname());
				}
			}
			
			rmapper.deleteRepliesByBoardnum(boardnum);
			return true;
		}
		return false;
	}

}






