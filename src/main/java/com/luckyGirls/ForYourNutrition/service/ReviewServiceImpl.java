package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.ReviewDao;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Review;

import jakarta.transaction.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;

	@Transactional
	@Override
	public Review getReview(int review_id) {
		// TODO Auto-generated method stub
		return reviewDao.getReview(review_id);
	}

	@Transactional
	@Override
	public void insertReview(Review review) {
		// TODO Auto-generated method stub
		reviewDao.insertReview(review);
	}

	@Transactional
	@Override
	public Review updateReview(Review review) {
		// TODO Auto-generated method stub
		return reviewDao.updateReview(review);
	}

	@Transactional
	@Override
	public void deleteReview(Review review) {
		// TODO Auto-generated method stub
		reviewDao.deleteReview(review);
	}

	@Transactional
	@Override
	public Page<Review> getReviewListForItem(int item_id, Pageable pageable) {
		return reviewDao.getReviewListForItem(item_id, pageable);
	}

	@Transactional
	@Override
	public List<Review> getReviewListForMember(int member_id) {
		// TODO Auto-generated method stub
		return reviewDao.getReviewListForMember(member_id);
	}

	@Transactional
	@Override
	public void addPoint(Member member, int point) {
		// TODO Auto-generated method stub
		reviewDao.updateMemberPoint(member, point);
	}

}
