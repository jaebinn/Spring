package com.example.demo.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
//@AllArgsConstructor : 인스턴스 변수로 선언된 모든 것을 파라미터로 받는 생성자 작성
//@AllArgsConstructor
//@RequiredArgsConstructor : 특정 변수를 위한 생성자를 만들 때 작성
//final이나 @NonNull이 붙은 인스턴스 변수에 대한 생성자를 작성
@RequiredArgsConstructor
public class Hotel {
	@NonNull
	private Chef chef;
	int data;
}
