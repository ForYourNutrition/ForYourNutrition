package com.luckyGirls.ForYourNutrition.dao.jpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.dao.OrderDao;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
	public int insertOrder(Order order) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(order);
		return order.getOrder_id();
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
	
	@Transactional
	@Override
	public void updateOrder(Order order) throws DataAccessException {
		// TODO Auto-generated method stub
		em.merge(order);
	}
	
	@Transactional
	@Override
	public List<Order> getOrderList(int member_id) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.member.member_id = :member_id", Order.class);
		
		query.setParameter("member_id", member_id);
		List<Order> orderList = query.getResultList();
		return orderList;
	}

}