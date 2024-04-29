package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {
	//Mapper 인터페이스
	//Mapper를 작성하는 작업은 XML을 이용할 수도 있지만,
	//최소한의 코드로 작성하기 위해서는 Mapper 인터페이스를 사용한다.
	@Select("select now() from dual")
	public String getTime1();
	
	public String getTime2();
}


