package com.luckyGirls.ForYourNutrition.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luckyGirls.ForYourNutrition.domain.Order;

public interface OrderDao {
	public int createOrder(Order order, int member_id)throws DataAccessException;
	public Page<Order> getOrderList(int memberId, Pageable pageable)throws DataAccessException;;
	public int cancelOrder(int order_id)throws DataAccessException;;

}
