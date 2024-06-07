package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckyGirls.ForYourNutrition.dao.QuestionDao;
import com.luckyGirls.ForYourNutrition.domain.Question;
import com.luckyGirls.ForYourNutrition.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	  private final QuestionDao questionDao;

	    @Autowired
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
	    public Question getQuestion(int questionId){
	        return questionDao.getQuestion(questionId);
	    }

	    @Override
	    @Transactional
	    public List<Question> getAllQuestionList() {
	        return questionDao.getAllQuestionList();
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

		@Override
		public Question getQuestionByTitle(String title) {
			// TODO Auto-generated method stub
			return questionDao.getQuestionByTitle(title);
		}
	    
	
}
