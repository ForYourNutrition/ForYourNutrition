package com.luckyGirls.ForYourNutrition.dto.response;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class OrderGetResponse {
	private int order_id;
	private Member member;
	private LocalDateTime orderDate;
	private int orderStatus;
	//private List<OrderItem> orderItems = new ArrayList<>();
	
	public static OrderGetResponse from(Order order) {
		return new OrderGetResponse(
			order.getOrder_id(),
			order.getMember(),
			order.getOrderDate(),
			order.getOrderStatus()
		);
	}
}
