package com.luckyGirls.ForYourNutrition.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.luckyGirls.ForYourNutrition.domain.QuestionComment;

public interface QuestionCommentDao {

	QuestionComment getQuestionComment(int qc_id) throws DataAccessException; // 후기 댓글 정보 조회

	void insertQuestionComment(QuestionComment questionComment) throws DataAccessException; // 문의 댓글 추가

	void updateQuestionComment(QuestionComment questionComment) throws DataAccessException; // 문의 댓글 수정

	void deleteQuestionComment(QuestionComment questionComment) throws DataAccessException; // 문의 댓글 삭제
		
	// 문의에 대한 모든 댓글 조회
	List<QuestionComment> getQuestionCommentListForQuestion(int question_id) throws DataAccessException;
	
	// 해당 멤버가 작성한 모든 문의 댓글 내역을 조회
	List<QuestionComment> getQuestionCommentListForMember(int member_id) throws DataAccessException;
}
