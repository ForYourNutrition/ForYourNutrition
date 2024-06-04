package com.luckyGirls.ForYourNutrition.service;

import com.luckyGirls.ForYourNutrition.domain.Member;

public interface MemberService {
	Member getMember(String id); //회원 정보 가져오기
	Member getMember(String id, String password); //id, password로 회원 정보 가져오기
	void insertMember(Member member); //회원 가입
	void updateMember(Member member); //회원 정보 수정
	void deleteMember(String id); //회원 탈퇴
	String findId(String email, String name); //아이디 찾기
	String findPassword(String id, String email); //비밀번호 찾기
}
