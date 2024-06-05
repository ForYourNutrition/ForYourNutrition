package com.luckyGirls.ForYourNutrition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.service.MemberService;

@Component
public class AuthenticatorImpl implements Authenticator {

	@Autowired
	private MemberService memberService;
	
	@Override
	public void authenticate(LoginForm loginForm) {
		Member member = memberService.getMember(loginForm.getId());
		
		// id에 해당하는 member가 없을 경우
		if (member == null) {
			throw new AuthenticationException("noSuchMember");
		}
		
		// id과 비밀번호가 일치하지 않는 경우
		if (!member.matchPassword(loginForm.getPassword())) {
			throw new AuthenticationException("notMatchPassword");
		}
	}
	
}
