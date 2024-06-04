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
	void createQuestion(Question question)throws DataAccessException;
	List<Question> questionList()throws DataAccessException;
	Question detailQuestion(int question_id)throws DataAccessException;
	void updateQuestion(Question question)throws DataAccessException;
	void deleteQuestion(int question_id)throws DataAccessException;
}