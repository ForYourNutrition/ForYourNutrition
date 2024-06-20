package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.ReviewCommentDao;
import com.luckyGirls.ForYourNutrition.domain.ReviewComment;

@Service
public class ReviewCommentServiceImpl implements ReviewCommentService {

	@Autowired
	private ReviewCommentDao reviewCommentDao;

	@Override
	public ReviewComment getReviewComment(int rc_id) {
		// TODO Auto-generated method stub
		return reviewCommentDao.getReviewComment(rc_id);
	}

	@Override
	public void insertReviewComment(ReviewComment reviewComment) {
		// TODO Auto-generated method stub
		reviewCommentDao.insertReviewComment(reviewComment);
	}

	@Override
	public void updateReviewComment(ReviewComment reviewComment) {
		// TODO Auto-generated method stub
		reviewCommentDao.updateReviewComment(reviewComment);
	}

	@Override
	public void deleteReviewComment(ReviewComment reviewComment) {
		// TODO Auto-generated method stub
		reviewCommentDao.deleteReviewComment(reviewComment);
	}

	@Override
	public List<ReviewComment> getReviewCommentListForReview(int review_id) {
		// TODO Auto-generated method stub
		return reviewCommentDao.getReviewCommentListForReview(review_id);
	}

	@Override
	public List<ReviewComment> getReviewCommentListForMember(int member_id) {
		// TODO Auto-generated method stub
		return reviewCommentDao.getReviewCommentListForMember(member_id);
	}

}
