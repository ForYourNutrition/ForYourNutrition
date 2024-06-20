package com.luckyGirls.ForYourNutrition.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.luckyGirls.ForYourNutrition.domain.Question;

public interface QuestionDao {
	
	void insertQuestion(Question question) throws DataAccessException; // 문의 추가

	Question getQuestion(int question_id) throws DataAccessException; // 문의 하나 정보 조회

	// 해당 멤버가 작성한 모든 문의 내역을 조회
	List<Question> getQuestionListForMember(int member_id) throws DataAccessException;

	Question updateQuestion(Question question) throws DataAccessException; // 문의 수정

	void deleteQuestion(int question_id) throws DataAccessException; // 문의 삭제

	// 모든 사용자의 문의 조회 시, 정렬, 검색, 페이지 기능 추가
	List<Question> getQuestionList(String sort, int page, String keyword) throws DataAccessException;

	long countQuestions(String keyword) throws DataAccessException; // 키워드 검색
}