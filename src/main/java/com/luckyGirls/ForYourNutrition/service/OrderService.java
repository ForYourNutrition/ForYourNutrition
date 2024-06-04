package com.luckyGirls.ForYourNutrition.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luckyGirls.ForYourNutrition.domain.Order;

public interface OrderService {
	 public int order(Order order, int memeber_id);
	 public Page<Order> getOrderList(int member_id, Pageable pageable);
	// public boolean validateOrder(Long orderId, String email);
	 public int cancelOrder(int order_id);
}
