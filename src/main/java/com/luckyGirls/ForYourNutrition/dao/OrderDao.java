package com.luckyGirls.ForYourNutrition.dao;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.luckyGirls.ForYourNutrition.domain.Order;

public interface OrderDao {
	public Order getOrder(int order_id)throws DataAccessException;
	public int insertOrder(Order order)throws DataAccessException;
	public void deleteOrder(int order_id)throws DataAccessException;
	public void updateOrder(Order order)throws DataAccessException;
	public List<Order> getOrderList(int member_id)throws DataAccessException;
}

