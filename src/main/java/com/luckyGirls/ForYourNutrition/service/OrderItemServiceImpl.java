package com.luckyGirls.ForYourNutrition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.OrderItemDao;

import jakarta.transaction.Transactional;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	@Autowired
	private OrderItemDao orderItemDao;
<<<<<<< HEAD
	
=======

>>>>>>> 9c54a40b224c987d308b814212938df069de40d1
	@Override
    @Transactional
    public void insertOrderItem(int member_id, int item_id, int count) {
        orderItemDao.insertOrderItem(member_id, item_id, count);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 9c54a40b224c987d308b814212938df069de40d1
