package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.UserDTO;
import com.example.demo.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper mapper;

	@Override
	public boolean join(UserDTO user) {
		return mapper.insertUser(user) == 1;
	}

	@Override
	public boolean leaveId(String userid) {
		return mapper.deleteUser(userid) == 1;
	}

	@Override
	public UserDTO getDetail(String userid) {
		return mapper.getUserById(userid);
	}

	@Override
	public boolean login(String userid, String userpw) {
		UserDTO temp = mapper.getUserById(userid);
		if(temp != null) {
			if(userpw.equals("1234")) {
				return true;
			}
		}
		return false;
	}
	
}
