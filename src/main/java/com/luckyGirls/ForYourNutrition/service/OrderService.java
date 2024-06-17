package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import com.luckyGirls.ForYourNutrition.domain.Order;

public interface OrderService {
	Order getOrder(int order_id); //주문 조회
	int insertOrder(Order order); //주문 추가
	void deleteOrder(int order_id); //주문 취소
	void updateOrder(Order order);//주문 상태 변경
	List<Order> getOrderList(int member_id); //주문 리스트 조회
}