package com.luckyGirls.ForYourNutrition.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.dao.MemberDao;
import com.luckyGirls.ForYourNutrition.dao.mybatis.mapper.MemberMapper;
import com.luckyGirls.ForYourNutrition.domain.Member;

@Repository
public class MybatisMemberDao implements MemberDao{
	
	@Autowired
	private MemberMapper memberMapper;
	
	public Member getMember(String id) throws DataAccessException{
		return memberMapper.getAccountById(id);
	} //회원 정보 가져오기

	public Member getMember(String id, String password) throws DataAccessException{
		return memberMapper.getAccountByIdAndPassword(id, password);
	} //id, password로 회원 정보 가져오기

	public void insertMember(Member member) throws DataAccessException{
		memberMapper.insertMember(member);
	} //회원 가입

	public void updateMember(Member member) throws DataAccessException{
		memberMapper.updateMember(member);
	} //회원 정보 수정
	
	public void deleteMember(String id) throws DataAccessException{
		memberMapper.deleteMember(id);
	} //회원 탈퇴
	
	public String findId(String email, String name) throws DataAccessException{
		return memberMapper.findId(email, name);
	} //아이디 찾기
	
	public String findPassword(String id, String email) throws DataAccessException{
		return memberMapper.findPassword(id, email);
	} //비밀번호 찾기

	public List<Member> getMembers(int taking_time) throws DataAccessException{
		return memberMapper.getMembers(taking_time);
	} //섭취시간으로 회원들 가져오기
}
