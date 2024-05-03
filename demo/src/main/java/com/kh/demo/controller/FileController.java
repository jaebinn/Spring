package com.kh.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.demo.service.FileService;

@RequestMapping("/file/*")
@Controller
public class FileController {
	@Autowired
	private FileService service;
	
	@GetMapping("thumbnail")
	public ResponseEntity<Resource> thumbnail(String systemname) throws Exception {
		System.out.println(systemname);
		return service.getThumbnailResource(systemname);
	}
	
	@GetMapping("download")
	public ResponseEntity<Resource> download(String systemname, String orgname) throws Exception{
		return service.downloadFile(systemname, orgname);
	}
}





