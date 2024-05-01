package com.example.demo.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.demo.domain.UserDTO;
import com.example.demo.mapper.UserMapper;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //DB에 삽입안할려고, 없으면 삽입가능
public class UserMapperTests {
	@Autowired
	private UserMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void insertUserTest() {
		UserDTO user = new UserDTO();
		user.setId("test");
		mapper.insertUser(user);
	}
}




