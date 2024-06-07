package com.luckyGirls.ForYourNutrition.service;

import com.luckyGirls.ForYourNutrition.domain.Order;

public interface OrderService {
	Order getOrder(int order_id); //주문 조회
	void insertOrder(Order order); //주문 추가
	void deleteOrder(int order_id); //주문 취소
}