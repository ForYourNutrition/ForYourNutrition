package com.luckyGirls.ForYourNutrition.dao;


import org.springframework.dao.DataAccessException;

import com.luckyGirls.ForYourNutrition.domain.OrderItem;

public interface OrderItemDao {
	void insertOrderItem(OrderItem orderItem) throws DataAccessException; 
}
