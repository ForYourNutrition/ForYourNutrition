package com.luckyGirls.ForYourNutrition.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.domain.Cart;

public interface CartDao {
	void saveCart(Cart cart) throws DataAccessException; //장바구니 추가
	
	void updateCart(Cart cart) throws DataAccessException; //장바구니 수정
	
	void deleteCart(Cart cart) throws DataAccessException; //장바구니 삭제
	
	Cart findById(int cart_id) throws DataAccessException; //장바구니 하나 
	
	List<Cart> findAll() throws DataAccessException; //장바구니 all
}
