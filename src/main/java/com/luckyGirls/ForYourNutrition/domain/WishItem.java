package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Table(name = "WishItem")
public class WishItem implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int wish_id;
	private Member member;
	private Item item;
	
	/* JavaBeans Properties */
	
	public int getWish_id() {
		return wish_id;
	}
	public void setWish_id(int wish_id) {
		this.wish_id = wish_id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	
}
