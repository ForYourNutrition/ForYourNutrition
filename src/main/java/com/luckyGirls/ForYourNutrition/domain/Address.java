package com.luckyGirls.ForYourNutrition.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int address_id;

	@ManyToOne
	@JoinColumn(name="member_id", referencedColumnName="member_id")
	private Member member;
	
	private String zipCode; // 우편 번호
	
	private String streetAddress; // 지번 주소
	
	private String detailAddress; // 상세 주소
}
