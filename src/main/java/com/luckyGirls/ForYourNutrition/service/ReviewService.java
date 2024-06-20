package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Review;

public interface ReviewService {
	Review getReview(int review_id); // 후기 정보 조회

	void insertReview(Review review); // 후기 추가

	Review updateReview(Review review); // 후기 수정

	void deleteReview(Review review); // 후기 삭제

	Page<Review> getReviewListForItem(int item_id, Pageable pageable);

	List<Review> getReviewListForMember(int member_id); // 해당 멤버가 작성한 모든 후기 내역을 조회

	void addPoint(Member member, int point); // 포인트 추가
}
