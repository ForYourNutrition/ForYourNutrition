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
public class JpaQuestionDao implements QuestionDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public List<Question> getQuestionListForMember(int member_id) throws DataAccessException {
		TypedQuery<Question> query = em
				.createQuery("select q from Question q JOIN q.member m where m.member_id = :member_id", Question.class);
		query.setParameter("member_id", member_id);
		return query.getResultList();
	}

	@Transactional
	@Override
	public void insertQuestion(Question question) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(question);
	}

	@Transactional
	@Override
	public Question getQuestion(int question_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return em.find(Question.class, question_id);
	}

	@Transactional
	@Override
	public List<Question> getQuestionList(String sort, int page, String keyword) {

		if (page < 0) {
			page = 0;
		}

		String baseQuery = "SELECT q FROM Question q";
		String orderClause = " ORDER BY q.question_id DESC";
		if ("oldest".equals(sort)) {
			orderClause = " ORDER BY q.question_id ASC";
		}

		if (keyword != null && !keyword.isEmpty()) {
			baseQuery += " WHERE q.title LIKE :keyword";
		}

		baseQuery += orderClause;

		TypedQuery<Question> query = em.createQuery(baseQuery, Question.class);
		if (keyword != null && !keyword.isEmpty()) {
			query.setParameter("keyword", "%" + keyword + "%");
		}

		query.setFirstResult(page * 15);
		query.setMaxResults(15);

		return query.getResultList();
	}

	public long countQuestions(String keyword) {
		String countQuery = "SELECT COUNT(q) FROM Question q";
		if (keyword != null && !keyword.isEmpty()) {
			countQuery += " WHERE q.title LIKE :keyword";
		}

		TypedQuery<Long> query = em.createQuery(countQuery, Long.class);
		if (keyword != null && !keyword.isEmpty()) {
			query.setParameter("keyword", "%" + keyword + "%");
		}

		return query.getSingleResult();
	}

	@Transactional
	@Override
	public Question updateQuestion(Question question) throws DataAccessException {
		// TODO Auto-generated method stub
		return em.merge(question);
	}

	@Transactional
	@Override
	public void deleteQuestion(int question_id) throws DataAccessException {
		// TODO Auto-generated method stub
		Question managedQuestion = em.find(Question.class, question_id);
		if (managedQuestion != null) {
			em.remove(managedQuestion);
		}
	}
}