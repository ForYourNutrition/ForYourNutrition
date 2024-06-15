package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	private int gender;
	private String phone_number;
	private String email;
	private int point;
	private int taking_time;
	
	public boolean matchPassword(String inputPassword) {
		return password.equals(inputPassword);
	}
  
}