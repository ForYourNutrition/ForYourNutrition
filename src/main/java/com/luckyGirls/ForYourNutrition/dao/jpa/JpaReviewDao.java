package com.luckyGirls.ForYourNutrition.dao.jpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.dao.ReviewDao;
import com.luckyGirls.ForYourNutrition.domain.Review;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaReviewDao implements ReviewDao {
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	@Override
	public Review getReview(int review_id) throws DataAccessException {
		// TODO Auto-generated method stub
		Review review = em.find(Review.class, review_id);
		/*if(review == null) {
			throw new ReviewNotFoundException();
		}*/
		return review;
	}

	@Transactional
	@Override
	public void insertReview(Review review) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(review);
	}

	@Transactional
	@Override
	public void updateReview(Review review) throws DataAccessException {
		// TODO Auto-generated method stub
		em.merge(review);
	}

	@Transactional
	@Override
	public void deleteReview(Review review) throws DataAccessException {
		// TODO Auto-generated method stub
		Review managedReview = em.merge(review);
		em.remove(managedReview);
	}

	@Transactional
	@Override
	public List<Review> getReviewListForItem(int item_id) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<Review> query = em.createQuery(
				"SELECT r FROM Review r JOIN r.item i"
				+ "WHERE i.item_id=?1", Review.class);
		query.setParameter(1, item_id);
		List<Review> reviewList = query.getResultList();
		return reviewList;
	}

	@Transactional
	@Override
	public List<Review> getReviewListForMember(int member_id) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<Review> query = em.createQuery(
				"SELECT r FROM Review r JOIN r.member m"
				+ "WHERE m.member_id=?1", Review.class);
		query.setParameter(1, member_id);
		List<Review> reviewList = query.getResultList();
		return reviewList;
	}

}
