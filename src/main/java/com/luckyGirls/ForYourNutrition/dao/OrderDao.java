package com.luckyGirls.ForYourNutrition.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luckyGirls.ForYourNutrition.domain.Order;

public interface OrderDao {
	public Order getOrder(int order_id)throws DataAccessException;
	public void insertOrder(Order order)throws DataAccessException;
	public void deleteOrder(int order_id)throws DataAccessException;
}
