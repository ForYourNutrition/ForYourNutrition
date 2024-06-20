package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.CartDao;
import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.CartItem;
import com.luckyGirls.ForYourNutrition.domain.Item;
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

			Item item = cartItem.getItem();
			int availableStock = item.getStock();
			int requestedQuantity = Math.min(cartItem.getQuantity(), availableStock);

			if (requestedQuantity < 1) {
				requestedQuantity = 1;
			}

			cartItem.setQuantity(requestedQuantity);

			List<CartItem> existingCartItems = cartDao.findCartItemsByCartAndItem(cart, item);
			if (!existingCartItems.isEmpty()) {
				CartItem existingCartItem = existingCartItems.get(0);
				int newQuantity = Math.min(existingCartItem.getQuantity() + requestedQuantity, availableStock);
				existingCartItem.setQuantity(newQuantity);
				cartDao.saveCartItem(existingCartItem);

				for (int i = 1; i < existingCartItems.size(); i++) {
					cartDao.deleteCartItemById(existingCartItems.get(i).getCartItem_id());
				}
			} else {
				cartItem.setCart(cart);
				cart.addCartItem(cartItem);
				cartDao.saveCartItem(cartItem);
			}
		} catch (Exception e) {
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
			int availableStock = cartItem.getItem().getStock();
			int newQuantity = Math.min(cartItem.getQuantity() + quantity, availableStock);
			cartItem.setQuantity(newQuantity);
			cartDao.saveCartItem(cartItem);
		}
	}

	@Override
	@Transactional
	public void removeQuantity(Member member, int cartItemId, int quantity) {
		// TODO Auto-generated method stub
		CartItem cartItem = cartDao.findCartItemById(cartItemId);
		if (cartItem != null) {
			int newQuantity = Math.max(cartItem.getQuantity() - quantity, 1);
			cartItem.setQuantity(newQuantity);
			cartDao.saveCartItem(cartItem);
		}
	}
}
