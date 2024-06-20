package com.luckyGirls.ForYourNutrition.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.CartItem;
import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.domain.Member;

public interface CartDao {

	/* cart */
	void saveCart(Cart cart) throws DataAccessException;

	Cart findCartByMember(Member member) throws DataAccessException;

	/* cart item */
	void saveCartItem(CartItem cartItem) throws DataAccessException;

	void deleteCartItemById(int cartItem_id) throws DataAccessException;

	CartItem findCartItemById(int cartItem_id) throws DataAccessException;

	List<CartItem> findCartItemsByCartAndItem(Cart cart, Item item) throws DataAccessException;
}
