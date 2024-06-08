package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.support.PagedListHolder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "Cart")
@SuppressWarnings("serial")
public class Cart implements Serializable{
	
	/* Private Fields */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    @SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 1) 
	private int cart_id;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<CartItem> cartItemList = new ArrayList<>();

	/* JavaBeans Properties */
	
	public Cart() {
		super();
	}

	public Cart(int cart_id, Member member, List<CartItem> cartItemList) {
		super();
		this.cart_id = cart_id;
		this.member = member;
		this.cartItemList = cartItemList;
	}
	 // Parameterized constructor
    public Cart(Member member) {
        this.member = member;
    }

    // Getters and Setters
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

    public List<CartItem> getCartItems() {
        return cartItemList;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItemList = cartItems;
    }

    // Utility methods
    public void addCartItem(CartItem cartItem) {
        cartItemList.add(cartItem);
        cartItem.setCart(this);
    }

    public void removeCartItem(CartItem cartItem) {
        cartItemList.remove(cartItem);
        cartItem.setCart(null);
    }

    @Override
    public String toString() {
        return "Cart [cart_id=" + cart_id + ", member=" + member + ", cartItems=" + cartItemList + "]";
    }
	
	
}
