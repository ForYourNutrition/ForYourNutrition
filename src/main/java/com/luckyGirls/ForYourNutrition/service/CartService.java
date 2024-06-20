package com.luckyGirls.ForYourNutrition.service;

import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.CartItem;
import com.luckyGirls.ForYourNutrition.domain.Member;

public interface CartService {

	/* cart */

	void createCart(Member member); // cart 생성

	Cart getCartByMember(Member member); // 회원의 cart 조회

	/* cart item */
	void addCartItem(Member member, CartItem cartItem);// cart item 추가

	void removeCartItem(Member member, int cartItemId); // cart item 삭제

	void addQuantity(Member member, int cartItemId, int quantity); // cart item 수량 추가

	void removeQuantity(Member member, int cartItemId, int quantity); // cart item 수량 삭제
}
