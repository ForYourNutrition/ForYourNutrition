package com.luckyGirls.ForYourNutrition.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckyGirls.ForYourNutrition.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

import com.luckyGirls.ForYourNutrition.domain.Order;
import com.luckyGirls.ForYourNutrition.dto.response.OrderGetResponse;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
	private final OrderRepository orderRepository;
	
	public OrderGetResponse getOrder(int order_id) throws Exception {
		Order order = orderRepository.findById(order_id);
		System.out.println("order: " + order.toString());
		return OrderGetResponse.from(order);
	}
	
	@Transactional
	public int order(Order order, int member_id) {
		return orderRepository.createOrder(order, member_id);
	}
	// public Page<Order> getOrderList(int member_id, Pageable pageable);
	// public boolean validateOrder(Long orderId, String email);
	// public int cancelOrder(int order_id);
}
