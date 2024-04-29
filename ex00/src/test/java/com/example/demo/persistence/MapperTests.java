package com.example.demo.persistence;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.demo.mapper.TimeMapper;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MapperTests {
	@Autowired
	private TimeMapper mapper;
	
	@Test
	public void getTime1Test(){
		System.out.println(mapper.getTime1());
	}
	@Test
	public void getTime2Test() {
		System.out.println(mapper.getTime2());
	}
}
