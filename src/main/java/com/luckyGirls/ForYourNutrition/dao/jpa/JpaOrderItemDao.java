package com.luckyGirls.ForYourNutrition.dao.jpa;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.dao.OrderItemDao;
import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.OrderItem;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class JpaOrderItemDao implements OrderItemDao{
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void insertOrderItem(OrderItem orderItem) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(orderItem);
	}

}

