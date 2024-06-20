package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckyGirls.ForYourNutrition.dao.QuestionDao;
import com.luckyGirls.ForYourNutrition.domain.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDao questionDao;

	public QuestionServiceImpl(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	@Override
	@Transactional
	public List<Question> getQuestionListForMember(int memberId) {
		return questionDao.getQuestionListForMember(memberId);
	}

	@Override
	@Transactional
	public void insertQuestion(Question question) {
		questionDao.insertQuestion(question);
	}

	@Override
	@Transactional
	public Question getQuestion(int questionId) {
		return questionDao.getQuestion(questionId);
	}

	@Transactional
	public List<Question> getQuestionList(String sort, int page, String keyword) {
		return questionDao.getQuestionList(sort, page, keyword);
	}

	@Transactional
	public int getTotalPages(String keyword) {
		long totalCount = questionDao.countQuestions(keyword);
		return (int) Math.ceil((double) totalCount / 15);
	}

	@Override
	@Transactional
	public Question updateQuestion(Question question) {
		return questionDao.updateQuestion(question);
	}

	@Override
	@Transactional
	public void deleteQuestion(int questionId) {
		questionDao.deleteQuestion(questionId);
	}

}
