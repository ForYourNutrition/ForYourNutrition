package com.luckyGirls.ForYourNutrition.dao;

import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.CartItem;
import com.luckyGirls.ForYourNutrition.domain.Member;

public interface CartDao {
	/*void insertCart(Cart cart) throws DataAccessException; //장바구니 추가
	
	void updateCart(Cart cart) throws DataAccessException; //장바구니 수정
	
	void deleteCart(Cart cart) throws DataAccessException; //장바구니 삭제
	
	Cart findById(int cart_id) throws DataAccessException; //장바구니 하나 
	
	List<Cart> findAll() throws DataAccessException; //장바구니 all*/
	
	/*cart*/
	void saveCart(Cart cart);
	Cart findCartByMember(Member member);
	
	/*cart item*/
	void saveCartItem(CartItem cartItem);
    void deleteCartItemById(int cartItem_id);
    CartItem findCartItemById(int cartItem_id);
}
