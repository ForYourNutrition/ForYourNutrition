package com.luckyGirls.ForYourNutrition.service;

import com.luckyGirls.ForYourNutrition.domain.Survey;

public interface SurveyService {
	Survey getSurvey(int member_id); //사용자의 문진표 조회
	
	void insertSurvey(Survey survey); //사용자의 문진표 생성

	void updateSurvey(Survey survey); //문진표 수정
}
