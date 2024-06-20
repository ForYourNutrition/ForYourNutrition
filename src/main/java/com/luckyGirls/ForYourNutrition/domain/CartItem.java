package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CartItem")
@SuppressWarnings("serial")
public class CartItem implements Serializable {

	/* Private Fields */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartItem_seq")
	@SequenceGenerator(name = "cartItem_seq", sequenceName = "cartItem_seq", allocationSize = 1)
	private int cartItem_id;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	private int quantity;// 상품 개수

	/* JavaBeans Properties */

	public int getCartItem_id() {
		return cartItem_id;
	}

	public void setCartItem_id(int cartItem_id) {
		this.cartItem_id = cartItem_id;
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

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public CartItem() {
		super();
	}

	public CartItem(int cartItem_id, Cart cart, Member member, Item item, int quantity) {
		super();
		this.cartItem_id = cartItem_id;
		this.cart = cart;
		this.member = member;
		this.item = item;
		this.quantity = quantity;
	}

	@Override
    public String toString() {
        return "CartItem{" +
               "cartItem_id=" + cartItem_id +
               ", cart=" + (cart != null ? cart.getCart_id() : null) + // 순환 참조를 방지하기 위해 cart_id 출력
               '}';
    }
	
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}

	public void removeQuantity(int quantity) {
		this.quantity -= quantity;
	}
}
