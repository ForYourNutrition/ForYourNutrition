package com.luckyGirls.ForYourNutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	Order findById(int order_id);
	int createOrder(Order order, int member_id);
}
	