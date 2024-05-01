package com.kh.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.demo.domain.dto.UserDTO;

@Mapper
public interface UserMapper {
	//C
	int insertUser(UserDTO user);
	//R
	UserDTO getUserById(String userid);
	//U
	int updateUser(UserDTO user);
	//D
	int deleteUser(String userid);
}
