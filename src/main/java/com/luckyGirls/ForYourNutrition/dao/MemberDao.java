package com.luckyGirls.ForYourNutrition.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.domain.Member;

public interface MemberDao {
	Member getMember(String id) throws DataAccessException; //회원 정보 가져오기

	Member getMember(String id, String password) throws DataAccessException; //id, password로 회원 정보 가져오기

	void insertMember(Member member) throws DataAccessException; //회원 가입

	void updateMember(Member member) throws DataAccessException; //회원 정보 수정
	
	void deleteMember(String id) throws DataAccessException; //회원 탈퇴
	
	String findId(String email, String name) throws DataAccessException; //아이디 찾기
	
	String findPassword(String id, String email) throws DataAccessException; //비밀번호 찾기
	
	List<Member> getMembers(int taking_time) throws DataAccessException; //섭취시간으로 회원들 가져오기
}
