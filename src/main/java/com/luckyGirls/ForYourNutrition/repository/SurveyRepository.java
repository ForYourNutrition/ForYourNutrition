package com.luckyGirls.ForYourNutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {
	Survey findByMember(Member member);
}
