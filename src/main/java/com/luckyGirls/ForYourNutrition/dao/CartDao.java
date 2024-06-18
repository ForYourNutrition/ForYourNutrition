package com.luckyGirls.ForYourNutrition.dao;

import java.util.List;

import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.CartItem;
import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.domain.Member;

public interface CartDao {

	/*cart*/
	void saveCart(Cart cart);
	Cart findCartByMember(Member member);
	
	/*cart item*/
	void saveCartItem(CartItem cartItem);
    void deleteCartItemById(int cartItem_id);
    CartItem findCartItemById(int cartItem_id);
    List<CartItem> findCartItemsByCartAndItem(Cart cart, Item item);
}
