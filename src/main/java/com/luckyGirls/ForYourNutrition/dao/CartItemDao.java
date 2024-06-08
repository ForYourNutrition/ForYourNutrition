package com.luckyGirls.ForYourNutrition.dao;

import org.springframework.dao.DataAccessException;

import com.luckyGirls.ForYourNutrition.domain.CartItem;

public interface CartItemDao {
	void saveCartItem(CartItem cartItem) throws DataAccessException; //장바구니 item 추가
	
	void updateCartItem(CartItem cartItem) throws DataAccessException; //장바구니 item 수정
	
	void deleteCartItem(CartItem cartItem) throws DataAccessException; //장바구니 item 삭제
	
	CartItem findById(int cartItem_id) throws DataAccessException; //장바구니 item 하나 
}
