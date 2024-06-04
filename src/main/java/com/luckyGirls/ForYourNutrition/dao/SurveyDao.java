package com.luckyGirls.ForYourNutrition.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.domain.SRecommend;
import com.luckyGirls.ForYourNutrition.domain.Survey;

/**
 * @author Yeonwoo Nam
 *
 */
public interface SurveyDao {
	Survey getSurvey(int member_id) throws DataAccessException; //사용자의 문진표 조회
	
	void insertSurvey(Survey survey) throws DataAccessException; //사용자의 문진표 생성

	void updateSurvey(Survey survey) throws DataAccessException; //문진표 수정
	
	List<SRecommend> viewSRecommendList(Survey survey) throws DataAccessException; //문진표 바탕 추천
}
