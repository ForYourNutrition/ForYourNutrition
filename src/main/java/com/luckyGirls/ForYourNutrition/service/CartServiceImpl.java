package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.CartDao;
import com.luckyGirls.ForYourNutrition.dao.CartItemDao;
import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.CartItem;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private CartItemDao cartItemDao;
	
	public CartServiceImpl(CartDao cartDao) {
		this.cartDao = cartDao;
	}
	@Transactional
	@Override
	public void saveCart(Cart cart) throws DataAccessException {
		// TODO Auto-generated method stub
		cartDao.saveCart(cart);
	}
	@Transactional
	@Override
	public void updateCart(Cart cart) throws DataAccessException {
		// TODO Auto-generated method stub
		cartDao.updateCart(cart);
	}
	@Transactional
	@Override
	public void deleteCart(Cart cart) throws DataAccessException {
		// TODO Auto-generated method stub
		cartDao.deleteCart(cart);
	}

	@Transactional
	@Override
	public Cart findById(int cart_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return cartDao.findById(cart_id);
	}
	@Transactional
	@Override
	public List<Cart> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return cartDao.findAll();
	}
	@Transactional
	public Cart createCart(Cart cart) {
		cartDao.saveCart(cart);
		return cart;
	}
	
	@Transactional
	public CartItem addItemToCart(CartItem cartItem) {
        cartItemDao.saveCartItem(cartItem);
        return cartItem;
    }
	@Transactional
    public List<CartItem> getCartItems(int cart_id) {
        return cartDao.findById(cart_id).getCartItems();
    }
	@Transactional
    public void removeItemFromCart(int cartItem_id) {
        CartItem cartItem = cartItemDao.findById(cartItem_id);
        cartItemDao.deleteCartItem(cartItem);
    }
	@Transactional
    public void clearCart(int cart_id) {
        Cart cart = cartDao.findById(cart_id);
        cart.getCartItems().clear();
        cartDao.updateCart(cart);
    }
}
