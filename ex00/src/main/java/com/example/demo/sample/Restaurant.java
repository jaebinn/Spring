package com.example.demo.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data : @Setter, @Getter, @ToString, ... 을 하나로 합친 어노테이션
//@Getter
@Data
@Component
public class Restaurant {
	//setter가 호출될 때 @Autowired(주입)
	//@Setter(onMethod_ = @Autowired)
	@Autowired
	private Chef chef;
}