package com.luckyGirls.ForYourNutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luckyGirls.ForYourNutrition.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{

}
