package com.luckyGirls.ForYourNutrition.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.domain.Question;

public interface QuestionDao {
	/*Question getQuestion(int question_id) throws DataAccessException; //문의 정보 조회
	
	void insertQuestion(Question question) throws DataAccessException; //문의 추가
	
	void updateQuestion(Question question) throws DataAccessException; //문의 수정
	
	void deleteQuestion(int question_id) throws DataAccessException; //문의 삭제
	
	List<Question> getQuestionListForMember(int member_id) throws DataAccessException; //해당 멤버가 작성한 모든 문의 내역을 조회
	*/
	void insertQuestion(Question question) throws DataAccessException; //문의 추가
	
	Question getQuestion(int question_id) throws DataAccessException; //문의 하나 정보 조회
	
	List<Question> getAllQuestionList() throws DataAccessException; //모든 사용자의 문의 조회
	
	List<Question> getQuestionListForMember(int member_id)throws DataAccessException; //해당 멤버가 작성한 모든 문의 내역을 조회
	
	Question updateQuestion(Question question) throws DataAccessException; //문의 수정
	
	void deleteQuestion(int question_id) throws DataAccessException; //문의 삭제
	
	Question getQuestionByTitle(String title) throws DataAccessException;//제목으로 조회
}