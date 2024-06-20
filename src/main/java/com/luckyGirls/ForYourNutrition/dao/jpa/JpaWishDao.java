package com.luckyGirls.ForYourNutrition.dao.jpa;

import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.dao.WishDao;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Wish;
import com.luckyGirls.ForYourNutrition.domain.WishItem;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaWishDao implements WishDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void saveWish(Wish wish) {
		// TODO Auto-generated method stub
		if (wish.getWish_id() == 0) {
			em.persist(wish);
		} else {
			em.merge(wish);
		}
	}

	@Transactional
	@Override
	public Wish findWishByMember(Member member) {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Wish> query = em.createQuery("SELECT w FROM Wish w WHERE w.member = :member", Wish.class);
			query.setParameter("member", member);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional
	@Override
	public void saveWishItem(WishItem wishItem) {
		// TODO Auto-generated method stub
		if (wishItem.getWishItem_id() == 0) {
			em.persist(wishItem);
		} else {
			em.merge(wishItem);
		}
	}

	@Transactional
	@Override
	public void deleteWishItemById(int wishItem_id) {
		// TODO Auto-generated method stub
		WishItem wishItem = em.find(WishItem.class, wishItem_id);
		if (wishItem != null) {
			em.remove(wishItem);
		}
	}

	@Transactional
	@Override
	public WishItem findWishItemById(int wishItem_id) {
		// TODO Auto-generated method stub
		return em.find(WishItem.class, wishItem_id);
	}

}
