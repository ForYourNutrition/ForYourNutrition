package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.CartItem;
import com.luckyGirls.ForYourNutrition.domain.Member;

public interface CartService {
	
	/*cart*/
	
	void createCart(Member member); // cart 생성
    Cart getCartByMember(Member member); // 회원의 cart 조회
    
    
	/*void insertCart(Cart cart); //장바구니 추가
	
	void updateCart(Cart cart); //장바구니 수정
	
	void deleteCart(Cart cart); //장바구니 삭제
	
	Cart findById(int cart_id); //장바구니 하나 조회 
	
	List<Cart> findAll(); //전체 장바구니 조회
	
	Cart createCart(Cart cart); // 새로운 장바구니 생성&저장
	
	/*cart item*/
    void addCartItem(Member member, CartItem cartItem);//cart item 추가
    void removeCartItem(Member member, int cartItemId); //cart item 삭제
    void addQuantity(Member member, int cartItemId, int quantity); //cart item 수량 추가
    void removeQuantity(Member member, int cartItemId, int quantity); //cart item 수량 삭제
	/*CartItem addItemToCart(CartItem cartItem); //cartItem 추가
	
	List<CartItem> getCartItems(int cart_id); //cartItem List
	
	void removeItemFromCart(int cartItem_id); //cartItem 삭제 

	void clearCart(int cart_id); //장바구니 비우기*/
}
