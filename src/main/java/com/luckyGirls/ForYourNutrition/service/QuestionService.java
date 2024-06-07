package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import com.luckyGirls.ForYourNutrition.domain.Question;

public interface QuestionService {
	void insertQuestion(Question question); //문의 추가
	
	Question getQuestion(int question_id); //문의 하나 정보 조회
	
	Question getQuestionByTitle(String title);//제목으로 조회
	
	List<Question> getAllQuestionList(); //모든 사용자의 문의 조회
	
	List<Question> getQuestionListForMember(int member_id); //해당 멤버가 작성한 모든 문의 내역을 조회
	
	Question updateQuestion(Question question); //문의 수정
	
	void deleteQuestion(int question_id); //문의 삭제

}
