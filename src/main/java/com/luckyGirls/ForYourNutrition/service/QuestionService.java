package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import com.luckyGirls.ForYourNutrition.domain.Question;

public interface QuestionService {
	/*Question getQuestion(int question_id); //문의 정보 조회

	void insertQuestion(Question question); //문의 추가
	
	void updateQuestion(Question question); //문의 수정
	
	void deleteQuestion(int question_id); //문의 삭제
	
	List<Question> getQuestionListForMember(int member_id); //해당 멤버가 작성한 모든 문의 내역을 조회
*/
	
	void createQuestion(Question question);
	List<Question> questionList();
	Question detailQuestion(int question_id);
	void updateQuestion(Question question);
	void deleteQuestion(int question_id);
}
