package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import com.luckyGirls.ForYourNutrition.domain.Order;

public interface OrderService {
	Order getOrder(int order_id); 
	int insertOrder(Order order);
	void deleteOrder(int order_id); 
	void updateOrder(Order order);
	List<Order> getOrderList(int member_id); 
}