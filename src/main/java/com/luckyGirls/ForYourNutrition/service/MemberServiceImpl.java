package com.luckyGirls.ForYourNutrition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.CartDao;
import com.luckyGirls.ForYourNutrition.dao.MemberDao;
import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.Member;

import jakarta.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired 
	private CartDao cartDao;
	
	public Member getMember(String id) {
		return memberDao.getMember(id);
	}
	
	//id, password로 회원 정보 가져오기
	public Member getMember(String id, String password) {
		return memberDao.getMember(id, password);
	}
	
	//회원 가입
	@Transactional
	public void insertMember(Member member) {
		memberDao.insertMember(member);
		
		/* 회원 가입 시, 자동으로 장바구니 생성
        Cart cart = new Cart();
        System.out.println("999");
        cart.setMember(member);
        System.out.println("9999");
        cartDao.saveCart(cart);
        System.out.println("999999");*/
	}
	
	//회원 정보 수정
	public void updateMember(Member member) {
		memberDao.updateMember(member);
	}
	
	//회원 탈퇴
	public void deleteMember(String id) {
		memberDao.deleteMember(id);
	}
	
	//아이디 찾기
	public String findId(String email, String name) {
		return memberDao.findId(email, name);
	}
	
	//비밀번호 찾기
	public String findPassword(String id, String email) {
		return memberDao.findPassword(id, email);
	}
}
