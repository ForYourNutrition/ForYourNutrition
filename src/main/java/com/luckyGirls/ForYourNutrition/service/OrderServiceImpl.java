package com.luckyGirls.ForYourNutrition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.OrderDao;
import com.luckyGirls.ForYourNutrition.domain.Order;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService{
	private final OrderDao orderDao;

	@Autowired
	public OrderServiceImpl(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	@Transactional
	public void insertOrder(Order order) {
		orderDao.insertOrder(order);
	}

	@Override
	@Transactional
	public Order getOrder(int order_id){
		return orderDao.getOrder(order_id);
	}

	@Override
	@Transactional
	public void deleteOrder(int order_id) {
		orderDao.deleteOrder(order_id);
	}
}

