package com.kh.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.demo.domain.dto.FileDTO;

@Mapper
public interface FileMapper {
	//C
	int insertFile(FileDTO file);
	//R
	FileDTO getFileBySystemname(String systemname);
	List<FileDTO> getFiles(long boardnum);
	//D
	int deleteFileBySystemname(String systemname);
	int deleteFilesByBoardnum(long boardnum);
}



