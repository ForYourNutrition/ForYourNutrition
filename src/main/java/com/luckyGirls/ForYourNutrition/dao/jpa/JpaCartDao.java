package com.luckyGirls.ForYourNutrition.dao.jpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.dao.CartDao;
import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.CartItem;
import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.domain.Member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaCartDao implements CartDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void saveCart(Cart cart) throws DataAccessException {
		// TODO Auto-generated method stub
		if (cart.getCart_id() == 0) {
			em.persist(cart);
		} else {
			em.merge(cart);
		}
	}

	@Transactional
	@Override
	public Cart findCartByMember(Member member) throws DataAccessException {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Cart> query = em.createQuery("SELECT c FROM Cart c WHERE c.member = :member", Cart.class);
			query.setParameter("member", member);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional
	@Override
	public List<CartItem> findCartItemsByCartAndItem(Cart cart, Item item) throws DataAccessException {
		TypedQuery<CartItem> query = em
				.createQuery("SELECT ci FROM CartItem ci WHERE ci.cart = :cart AND ci.item = :item", CartItem.class);
		query.setParameter("cart", cart);
		query.setParameter("item", item);
		return query.getResultList();
	}

	@Transactional
	@Override
	public void saveCartItem(CartItem cartItem) throws DataAccessException {
		// TODO Auto-generated method stub
		if (cartItem.getCartItem_id() == 0) {
			em.persist(cartItem);
		} else {
			em.merge(cartItem);
		}
	}

	@Transactional
	@Override
	public void deleteCartItemById(int cartItem_id) throws DataAccessException {
		// TODO Auto-generated method stub
		CartItem cartItem = em.find(CartItem.class, cartItem_id);
		if (cartItem != null) {
			em.remove(cartItem);
		}
	}

	@Transactional
	@Override
	public CartItem findCartItemById(int cartItem_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return em.find(CartItem.class, cartItem_id);
	}
}
