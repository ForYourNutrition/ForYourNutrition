package com.luckyGirls.ForYourNutrition.dao.jpa;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.dao.OrderDao;
import com.luckyGirls.ForYourNutrition.domain.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class JpaOrderDao implements OrderDao {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public Order getOrder(int order_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return em.find(Order.class, order_id);
	}

	@Override
	public void insertOrder(Order order) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(order);
	}

	@Transactional
	@Override
	public void deleteOrder(int order_id) throws DataAccessException {
		// TODO Auto-generated method stub
		Order cancelOrder = em.find(Order.class, order_id);
		if (cancelOrder != null) {
			em.remove(cancelOrder);
		}

	}

}