package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.QuestionComment;

public interface QuestionCommentService {
	QuestionComment getQuestionComment(int qc_id); // 후기 댓글 정보 조회

	void insertQuestionComment(QuestionComment questionComment); // 문의 댓글 추가

	void updateQuestionComment(QuestionComment questionComment); // 문의 댓글 수정

	void deleteQuestionComment(QuestionComment questionComment); // 문의 댓글 삭제

	List<QuestionComment> getQuestionCommentListForQuestion(int question_id); // 문의에 대한 모든 댓글 조회

	List<QuestionComment> getQuestionCommentListForMember(int member_id); // 해당 멤버가 작성한 모든 문의 댓글 내역을 조회

	void addCommentToQuestion(int question_id, String content, Member member, String qcdate);// 댓글 쓰기
}
