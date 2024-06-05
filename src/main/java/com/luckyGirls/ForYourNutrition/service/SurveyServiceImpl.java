package com.luckyGirls.ForYourNutrition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.domain.Survey;
import com.luckyGirls.ForYourNutrition.repository.SurveyRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;
    
    // 사용자의 문진표 조회
    @Override
    public Survey getSurvey(int member_id) {
        return surveyRepository.findById(member_id).orElse(null);
    }
    
    // 사용자의 문진표 생성
    @Override
    public void insertSurvey(Survey survey) {
        surveyRepository.save(survey);
    }
    
    // 문진표 수정
    @Override
    public void updateSurvey(Survey survey) {
        surveyRepository.save(survey);
    }
}
