package com.kh.demo.service;

import com.kh.demo.domain.dto.UserDTO;

public interface UserService {
	boolean join(UserDTO user);
	boolean login(String userid, String userpw);
	boolean checkId(String userid);
}
