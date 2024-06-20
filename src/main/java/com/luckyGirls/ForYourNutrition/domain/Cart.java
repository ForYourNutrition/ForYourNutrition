package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cart")
@SuppressWarnings("serial")
public class Cart implements Serializable {

	/* Private Fields */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
	@SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 1)
	private int cart_id;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	private int quantity;// 카트에 담긴 총 개수

	@OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE)
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

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
        return "Cart{" +
               "cart_id=" + cart_id +
               ", member=" + member +
               ", cartItemList=" + cartItemList.size() + // 사이즈 출력
               '}';
    }
}
