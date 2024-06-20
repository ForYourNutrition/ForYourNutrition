package com.luckyGirls.ForYourNutrition.dao.jpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.dao.ReviewDao;
import com.luckyGirls.ForYourNutrition.domain.Member;
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
		return em.find(Review.class, review_id);
	}

	@Transactional
	@Override
	public void insertReview(Review review) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(review);
	}

	@Transactional
	@Override
	public Review updateReview(Review review) throws DataAccessException {
		// TODO Auto-generated method stub
		return em.merge(review);
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
	public Page<Review> getReviewListForItem(int item_id, Pageable pageable) throws DataAccessException {
		TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r JOIN r.item i WHERE i.item_id = :item_id",
				Review.class);
		query.setParameter("item_id", item_id);

		int totalRows = query.getResultList().size();
		List<Review> reviewList = query.setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize())
				.getResultList();

		return new PageImpl<>(reviewList, pageable, totalRows);
	}

	@Transactional
	@Override
	public List<Review> getReviewListForMember(int member_id) throws DataAccessException {
		TypedQuery<Review> query = em
				.createQuery("SELECT r FROM Review r JOIN r.member m WHERE m.member_id = :member_id", Review.class);
		query.setParameter("member_id", member_id);

		return query.getResultList();
	}

	@Override
	public void updateMemberPoint(Member member, int point) {
		// TODO Auto-generated method stub
		member.setPoint(member.getPoint() + point);
	}
}
