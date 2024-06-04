package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Member")
public class Member implements Serializable {

	/* Private Fields */
	
	@Id
	@SequenceGenerator( 
		     name = "MEMBER_SEQ_GENERATOR", 
		     sequenceName = "MEMBERID_SEQ",
		     initialValue = 1, allocationSize = 50)
	private int member_id;
	private String id;
	private String password;
	private String name;
	private String nickname;
	private Date birth;
	private int gender;
	private String phone_number;
	private String email;
	private int point;
	private int taking_time;
  
	/* JavaBeans Properties */
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getTaking_time() {
		return taking_time;
	}
	public void setTaking_time(int taking_time) {
		this.taking_time = taking_time;
	} 
  
}
