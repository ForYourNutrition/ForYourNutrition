package com.luckyGirls.ForYourNutrition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.OrderItemDao;
import com.luckyGirls.ForYourNutrition.domain.OrderItem;

import jakarta.transaction.Transactional;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	@Autowired
	private OrderItemDao orderItemDao;

	@Override
    @Transactional
    public void insertOrderItem(OrderItem orderItem) {
        orderItemDao.insertOrderItem(orderItem);
    }

}
