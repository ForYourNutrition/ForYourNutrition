package com.luckyGirls.ForYourNutrition.dao.jpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.dao.QuestionDao;
import com.luckyGirls.ForYourNutrition.domain.Question;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaQuestionDao implements QuestionDao{

	@PersistenceContext
	private EntityManager em;
	/*
	@Transactional
	@Override
	public Question getQuestion(int question_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return em.find(Question.class, question_id);
	}
	@Transactional
	@Override
	public void insertQuestion(Question question) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(question);
	}
	@Transactional
	@Override
	public void updateQuestion(Question question) throws DataAccessException {
		// TODO Auto-generated method stub
		em.merge(question);
	}
	@Transactional
	@Override
	public void deleteQuestion(int question_id) throws DataAccessException {
		// TODO Auto-generated method stub
		Question managedQuestion = em.merge(getQuestion(question_id));
		em.remove(managedQuestion);
	}

	@Transactional
	@Override
	public List<Question> getQuestionListForMember(int member_id) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<Question> query = em.createQuery(
				"select q from Question q JOIN q.member m" + 
				"where m.member_id=?1", Question.class);
		query.setParameter(1, member_id);
		List<Question> questionList = query.getResultList();
		return questionList;
	}*/

	@Override
	public void createQuestion(Question question) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Question> questionList() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question detailQuestion(int question_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateQuestion(Question question) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteQuestion(int question_id) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	
}