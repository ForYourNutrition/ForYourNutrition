package com.luckyGirls.ForYourNutrition.controller;

import java.io.Serializable;

import com.luckyGirls.ForYourNutrition.domain.Address;
import com.luckyGirls.ForYourNutrition.domain.Member;


@SuppressWarnings("serial")
public class MemberForm implements Serializable {
	private Member member;
	
	private Address address;

	private boolean newMember;

	private String repeatedPassword;

	public MemberForm(Member member) {
		this.member = member;
		this.newMember = false;
	}

	public MemberForm() {
		this.member = new Member();
		this.newMember = true;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
