package com.luckyGirls.ForYourNutrition.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.luckyGirls.ForYourNutrition.domain.Item;

public interface CartDao {
	List<Item> getCartList(int member_id) throws DataAccessException; //장바구니 조회
	
	void addItemToCart(int member_id, int item_id) throws DataAccessException; //장바구니에 상품 추가
	
	void deleteItemFromCart(int member_id, int item_id) throws DataAccessException; //장바구니에서 상품 삭제
	
	void updateQuantity(int cart_id, int quantity) throws DataAccessException; //수량 업데이트
}
