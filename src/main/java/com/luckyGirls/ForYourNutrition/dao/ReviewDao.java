package com.luckyGirls.ForYourNutrition.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Review;

public interface ReviewDao {
	Review getReview(int review_id) throws DataAccessException; //후기 정보 조회
	
	void insertReview(Review review) throws DataAccessException; //후기 추가
	
	Review updateReview(Review review) throws DataAccessException; //후기 수정
	
	void deleteReview(Review review) throws DataAccessException; //후기 삭제

	Page<Review> getReviewListForItem(int item_id, Pageable pageable) throws DataAccessException;
	
	List<Review> getReviewListForMember(int member_id) throws DataAccessException; //해당 멤버가 작성한 모든 후기 내역을 조회

	void updateMemberPoint(Member member, int points);//포인트 추가
}
