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
		System.out.println("ㅅㅂ" + cart.toString());
	}

	@Override
	@Transactional
	public Cart getCartByMember(Member member) {
		// TODO Auto-generated method stub
		return cartDao.findCartByMember(member);
	}

	@Override
	@Transactional
	public void addCartItem(Member member, CartItem cartItem) {
		// TODO Auto-generated method stub
		Cart cart = cartDao.findCartByMember(member);
		if (cart == null) {
            cart = new Cart(member);
            cartDao.saveCart(cart);
        }
        cartItem.setCart(cart);
        cartDao.saveCartItem(cartItem);
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
