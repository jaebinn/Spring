package com.example.demo.service;

import com.example.demo.domain.UserDTO;

public interface UserService {
	boolean join(UserDTO user);
	boolean leaveId(String userid);
	UserDTO getDetail(String userid);
	boolean login(String userid,String userpw);
}
