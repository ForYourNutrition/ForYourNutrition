package com.luckyGirls.ForYourNutrition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.OrderItemDao;

import jakarta.transaction.Transactional;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Override
    @Transactional
    public void insertOrderItem(int member_id, int item_id, int count) {
        orderItemDao.insertOrderItem(member_id, item_id, count);
    }
}
