package com.kh.demo.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.kh.demo.domain.dto.UserDTO;
import com.kh.demo.mapper.UserMapper;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTests {
	@Autowired
	private UserMapper mapper;
	
//	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void insertUserTest() {
		UserDTO user = new UserDTO();
		user.setUserid("testid");
		user.setUserpw("testpw");
		user.setUsername("testname");
		user.setUsergender("IVE");
		user.setZipcode("testzipcode");
		user.setAddr("testaddr");
		user.setAddrdetail("testaddrdetail");
		user.setAddretc("testaddretc");
		user.setUserhobby("testhobby");
		
		boolean result = mapper.insertUser(user) == 1;	
		System.out.println("RESULT : "+result);
	}
	
//	@Test
	public void getUserByIdTest() {
		UserDTO	user = mapper.getUserById("testid");
		System.out.println("RESULT : "+user);
	}
}





