package com.luckyGirls.ForYourNutrition.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.luckyGirls.ForYourNutrition.domain.ReviewComment;

public interface ReviewCommentDao {
	
	ReviewComment getReviewComment(int rc_id) throws DataAccessException; // 후기 댓글 정보 조회

	void insertReviewComment(ReviewComment reviewComment) throws DataAccessException; // 후기 댓글 추가

	void updateReviewComment(ReviewComment reviewComment) throws DataAccessException; // 후기 댓글 수정

	void deleteReviewComment(ReviewComment reviewComment) throws DataAccessException; // 후기 댓글 삭제

	List<ReviewComment> getReviewCommentListForReview(int review_id) throws DataAccessException; // 후기에 대한 모든 댓글 조회

	// 해당 멤버가 작성한 모든 후기 댓글 내역을 조회
	List<ReviewComment> getReviewCommentListForMember(int member_id) throws DataAccessException;

}
