package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import com.luckyGirls.ForYourNutrition.domain.Review;

public interface ReviewService {
	Review getReview(int review_id); //후기 정보 조회
	
	void insertReview(Review review); //후기 추가
	
	void updateReview(Review review); //후기 수정
	
	void deleteReview(Review review); //후기 삭제
	
	List<Review> getReviewListForItem(int item_id); //상품에 대한 모든 후기 조회
	
	List<Review> getReviewListForMember(int member_id); //해당 멤버가 작성한 모든 후기 내역을 조회
}
