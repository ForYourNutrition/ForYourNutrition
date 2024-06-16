package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.domain.Address;
import com.luckyGirls.ForYourNutrition.repository.AddressRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressRepository addressRepository;
	
	// 배송지 추가
	@Override
	public void insertAddress(Address address) {
		addressRepository.save(address);
	}
	
	// 회원의 모든 배송지 조회
	@Override
    public List<Address> getAddressList(int memberId) {
        return addressRepository.findByMemberId(memberId);
    }
	
	// 배송지 정보 조회
	@Override
	public Address getAddress(int address_id) {
		return addressRepository.findById(address_id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
	}
	
	// 배송지 삭제
	@Override
	public void deleteAddress(int address_id) {
		 addressRepository.deleteById(address_id);
	}
	
	// 배송지 수정
	@Override
	public void updateAddress(Address address) {
		addressRepository.save(address);
	}
}
