package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import com.luckyGirls.ForYourNutrition.domain.Address;

public interface AddressService {
	void insertAddress(Address address); // 배송지 추가
	
	List<Address> getAddressList(int memberId); // 회원의 모든 배송지 조회
	
	Address getAddress(int address_id); // 배송지 정보 조회
	
	void deleteAddress(int address_id); // 배송지 삭제
	
	void updateAddress(Address address); // 배송지 수정
}
