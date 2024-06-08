package com.luckyGirls.ForYourNutrition.dao.jpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.dao.CartDao;
import com.luckyGirls.ForYourNutrition.domain.Cart;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class JpaCartDao implements CartDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void saveCart(Cart cart) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(cart);
	}

	@Override
	public void updateCart(Cart cart) throws DataAccessException {
		// TODO Auto-generated method stub
		em.merge(cart);
	}

	@Override
	public void deleteCart(Cart cart) throws DataAccessException {
		// TODO Auto-generated method stub
		em.remove(cart);
	}

	@Override
	public Cart findById(int cart_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return em.find(Cart.class, cart_id);
	}

	@Override
	public List<Cart> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return em.createQuery("from Cart", Cart.class).getResultList();
	}

}
