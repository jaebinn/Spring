package com.kh.demo.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/*")
public class APIController {
	final static String serviceKey = "ZozYPsbDHolwF6rSkvE7tBJkrhnYPKLAfO1DsF3m9I84NjSr0hrlGmG7oBUO6HKhzwFpt5F09UShOUwWTmAPTQ%3D%3D";
	@GetMapping("get")
	public void replace() {}
	
	@PostMapping(value = "get", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getData(String sidoName) throws Exception {
		//공공데이터포탈과 통신
		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey="+serviceKey;
		url += "&returnType=json";
		url += "&sidoName="+URLEncoder.encode(sidoName,"UTF-8");
		url += "&numOfRows="+10000;
		
		//단순한 문자열로 정의한 url 변수를 자바에서 네트워킹 때 활용할 수 있는 객체로 변환
		URL requestURL = new URL(url);
		//목적지로 향하는 다리 건설
		HttpURLConnection conn = (HttpURLConnection)requestURL.openConnection();
		
		conn.setRequestMethod("GET");
		
		//conn 다리가 건설되어 있는 목적지로부터 데이터를 읽어오기 위한 IS
		InputStream is = conn.getInputStream();
		//열려있는 IS 통로를 통해 들어오는 데이터를 읽기위한 리더기
		InputStreamReader isr = new InputStreamReader(is);
		
		BufferedReader br = new BufferedReader(isr);
		
		String result = "";
		String line = "";
		while(true) {
			line = br.readLine();
			if(line == null) { break; }
			result += line;
		}
		
		//System.out.println(result);
		
		return result;
	}
}















