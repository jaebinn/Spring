package com.kh.demo.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService{
	
	@Value("${file.dir}")
	private String saveFolder;
	
	@Override
	public ResponseEntity<Resource> downloadFile(String systemname, String orgname) throws Exception {
		Path path = Paths.get(saveFolder+systemname);
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		
		File file = new File(saveFolder,systemname);
		
		HttpHeaders headers = new HttpHeaders();
		String dwName = "";
		
		try {
			dwName = URLEncoder.encode(orgname,"UTF-8").replaceAll("\\+", "%20");
		} catch(UnsupportedEncodingException e) {
			dwName = URLEncoder.encode(file.getName(),"UTF-8").replaceAll("\\+","%20");
		}
		
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(dwName).build());
		return new ResponseEntity<>(resource,headers,HttpStatus.OK);
	}
	@Override
	public ResponseEntity<Resource> getThumbnailResource(String systemname) throws Exception {
		//경로에 관련된 객체(자원으로 가지고 와야 하는 파일에 대한 경로)
		Path path = Paths.get(saveFolder+systemname);
		//경로에 있는 파일의 MIME 타입을 조사해서 그대로 담기
		String contentType = Files.probeContentType(path);
		//응답 헤더 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		
		//해당 경로(path)에 있는 파일로부터 뻗어나오는 InputStream*Files.newInputStream(path)을
		//통해 자원화*new InputStreamResource()
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		return new ResponseEntity<>(resource,headers,HttpStatus.OK);
	}
}








