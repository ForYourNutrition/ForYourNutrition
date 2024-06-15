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
<<<<<<< HEAD
	
=======

>>>>>>> 9c54a40b224c987d308b814212938df069de40d1
	@Transactional
	@Override
	public void insertOrderItem(int member_id, int item_id, int count) throws DataAccessException {
		// TODO Auto-generated method stub
		Member member = em.find(Member.class, member_id);
		Item item = em.find(Item.class, item_id);
		OrderItem orderItem = new OrderItem();
		orderItem.setMember(member);
		orderItem.setItem(item);
		orderItem.setCount(count);
		orderItem.setOrderPrice(item.getPrice());
		em.persist(orderItem);
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 9c54a40b224c987d308b814212938df069de40d1
