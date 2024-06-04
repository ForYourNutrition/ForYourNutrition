package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.QuestionDao;
import com.luckyGirls.ForYourNutrition.domain.Question;
import com.luckyGirls.ForYourNutrition.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
/*
	@Autowired
	@Qualifier("jpaQuestionDao")
	private QuestionDao questionDao;
	
	@Override
	public Question getQuestion(int question_id) {
		// TODO Auto-generated method stub
		return questionDao.getQuestion(question_id);
	}

	@Override
	public void insertQuestion(Question question) {
		// TODO Auto-generated method stub
		questionDao.insertQuestion(question);
	}

	@Override
	public void updateQuestion(Question question) {
		// TODO Auto-generated method stub
		questionDao.updateQuestion(question);
	}

	@Override
	public void deleteQuestion(Question question) {
		// TODO Auto-generated method stub
		questionDao.deleteQuestion(question);
	}

	@Override
	public List<Question> getQuestionListForMember(int member_id) {
		// TODO Auto-generated method stub
		return questionDao.getQuestionListForMember(member_id);
	}*/
	/*===============================================================*/
	private QuestionRepository questionRepository;
	
	public void createQuestion(Question question) {
		questionRepository.save(question);
	}
	
	public List<Question> questionList(){
		return questionRepository.findAll(Sort.by(Sort.Direction.DESC,"question_id"));
	}
	
	public Question detailQuestion(int question_id) {
		return questionRepository.findById(question_id).orElse(null);
	}
	
	public void updateQuestion(Question question) {
		questionRepository.save(question);
	}	
	public void deleteQuestion(int question_id) {
		questionRepository.deleteById(question_id);
	}	
	
	
}
