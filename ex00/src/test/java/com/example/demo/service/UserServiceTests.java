package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.UserDTO;

@SpringBootTest
public class UserServiceTests {
	@Autowired @Qualifier("userServiceImpl")
	private UserService service;
	
	@Test
	public void testExist() {
		assertNotNull(service);
	}
	
//	@Test
//	public void joinTest() {
//		UserDTO user = new UserDTO();
//		user.setId("test");
//		service.join(user);
//	}
	
	@Test
	public void getDetailTest() {
//		assertNotNull(service.getDetail("test"));
		System.out.println(service.getDetail("test"));
	}
}
