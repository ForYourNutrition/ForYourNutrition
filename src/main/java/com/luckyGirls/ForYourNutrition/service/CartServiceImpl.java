package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.CartDao;
import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.CartItem;
import com.luckyGirls.ForYourNutrition.domain.Member;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;

	@Override
	@Transactional
	public void createCart(Member member) {
		// TODO Auto-generated method stub
		Cart cart = new Cart(member);
		cartDao.saveCart(cart);
		System.out.println(cart.toString());
	}

	@Override
	@Transactional
	public Cart getCartByMember(Member member) {
		// TODO Auto-generated method stub
		try {
	        Cart cart = cartDao.findCartByMember(member);
	        if (cart != null) {
	            // Lazy loading 문제 방지를 위해 cartItems를 강제 초기화
	            cart.getCartItems().size();
	        }
	        return cart;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	}

	@Override
	@Transactional
	public void addCartItem(Member member, CartItem cartItem) {
	    try {
	        Cart cart = cartDao.findCartByMember(member);
	        if (cart == null) {
	            cart = new Cart(member);
	            cartDao.saveCart(cart);
	        }

	        // 동일한 아이템이 있는지 확인
	        List<CartItem> existingCartItems = cartDao.findCartItemsByCartAndItem(cart, cartItem.getItem());
	        if (!existingCartItems.isEmpty()) {
	            // 동일한 아이템이 있을 경우 첫 번째 항목의 수량만 증가
	            CartItem existingCartItem = existingCartItems.get(0);
	            existingCartItem.addQuantity(cartItem.getQuantity());
	            cartDao.saveCartItem(existingCartItem);

	            // 중복된 항목 제거
	            for (int i = 1; i < existingCartItems.size(); i++) {
	                cartDao.deleteCartItemById(existingCartItems.get(i).getCartItem_id());
	            }
	        } else {
	            // 동일한 아이템이 없을 경우 새로운 항목 추가
	            cartItem.setCart(cart);
	            cart.addCartItem(cartItem);
	            cartDao.saveCartItem(cartItem);
	        }
	        System.out.println("!!Done!!");
	    } catch (Exception e) {
	        // 예외 발생 시 로그 출력
	        e.printStackTrace();
	        throw e;
	    }
	}




	@Override
	@Transactional
	public void removeCartItem(Member member, int cartItemId) {
		// TODO Auto-generated method stub
		cartDao.deleteCartItemById(cartItemId);
	}

	@Override
	@Transactional
	public void addQuantity(Member member, int cartItemId, int quantity) {
		// TODO Auto-generated method stub
		CartItem cartItem = cartDao.findCartItemById(cartItemId);
        if (cartItem != null) {
            cartItem.addQuantity(quantity);
            cartDao.saveCartItem(cartItem);
        }
	}

	@Override
	@Transactional
	public void removeQuantity(Member member, int cartItemId, int quantity) {
		// TODO Auto-generated method stub
		CartItem cartItem = cartDao.findCartItemById(cartItemId);
        if (cartItem != null) {
            cartItem.removeQuantity(quantity);
            cartDao.saveCartItem(cartItem);
        }
	}
}
