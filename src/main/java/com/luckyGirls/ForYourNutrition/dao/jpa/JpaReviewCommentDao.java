package com.luckyGirls.ForYourNutrition.dao.jpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.dao.ReviewCommentDao;
import com.luckyGirls.ForYourNutrition.domain.ReviewComment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaReviewCommentDao implements ReviewCommentDao {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public ReviewComment getReviewComment(int rc_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return em.find(ReviewComment.class, rc_id);
	}

	@Transactional
	@Override
	public void insertReviewComment(ReviewComment reviewComment) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(reviewComment);
	}

	@Transactional
	@Override
	public void updateReviewComment(ReviewComment reviewComment) throws DataAccessException {
		// TODO Auto-generated method stub
		em.merge(reviewComment);
	}

	@Transactional
	@Override
	public void deleteReviewComment(ReviewComment reviewComment) throws DataAccessException {
		// TODO Auto-generated method stub
		ReviewComment managedRC = em.merge(reviewComment);
		em.remove(managedRC);
	}

	@Transactional
	@Override
	public List<ReviewComment> getReviewCommentListForReview(int review_id) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<ReviewComment> query = em.createQuery(
				"SELECT rc FROM ReviewComment rc JOIN rc.review r WHERE r.review_id=?1", ReviewComment.class);
		query.setParameter(1, review_id);
		return query.getResultList();
	}

	@Transactional
	@Override
	public List<ReviewComment> getReviewCommentListForMember(int member_id) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<ReviewComment> query = em.createQuery(
				"SELECT rc FROM ReviewComment rc JOIN rc.member m WHERE m.member_id=?1", ReviewComment.class);
		query.setParameter(1, member_id);
		return query.getResultList();
	}

}
