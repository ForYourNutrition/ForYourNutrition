package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CartItem")
@SuppressWarnings("serial")
public class CartItem implements Serializable {
	
	/* Private Fields */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int cart_id;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	@OneToOne
	private Item item;
	private int quantity;

	/* JavaBeans Properties */
	
	public int getCart_id() {
		return cart_id;
	}	
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CartItem() {
		super();
	}
	public CartItem(int cart_id, Member member, Item item, int quantity) {
		super();
		this.cart_id = cart_id;
		this.member = member;
		this.item = item;
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "CartItem [cart_id=" + cart_id + ", member=" + member + ", item=" + item + ", quantity=" + quantity
				+ "]";
	}	
	
}
