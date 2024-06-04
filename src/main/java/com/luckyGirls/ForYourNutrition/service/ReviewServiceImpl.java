package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.ReviewDao;
import com.luckyGirls.ForYourNutrition.domain.Review;

@Service("reviewServiceImpl")
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	@Qualifier("jpaReviewDao")
	private ReviewDao reviewDao;
	
	@Override
	public Review getReview(int review_id) {
		// TODO Auto-generated method stub
		return reviewDao.getReview(review_id);
	}

	@Override
	public void insertReview(Review review) {
		// TODO Auto-generated method stub
		reviewDao.insertReview(review);
	}

	@Override
	public void updateReview(Review review) {
		// TODO Auto-generated method stub
		reviewDao.updateReview(review);
	}

	@Override
	public void deleteReview(Review review) {
		// TODO Auto-generated method stub
		reviewDao.deleteReview(review);
	}

	@Override
	public List<Review> getReviewListForItem(int item_id) {
		// TODO Auto-generated method stub
		return reviewDao.getReviewListForItem(item_id);
	}

	@Override
	public List<Review> getReviewListForMember(int member_id) {
		// TODO Auto-generated method stub
		return reviewDao.getReviewListForMember(member_id);
	}

}
