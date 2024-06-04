package com.luckyGirls.ForYourNutrition.controller;

import java.io.Serializable;

import com.luckyGirls.ForYourNutrition.domain.Member;


@SuppressWarnings("serial")
public class MemberForm implements Serializable {

	private Member member;

	private boolean newMember;

	private String repeatedPassword;

	public MemberForm(Member account) {
		this.member = account;
		this.newMember = false;
	}

	public MemberForm() {
		this.member = new Member();
		this.newMember = true;
	}

	public Member getMember() {
		return member;
	}

	public boolean isNewMember() {
		return newMember;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}
}
