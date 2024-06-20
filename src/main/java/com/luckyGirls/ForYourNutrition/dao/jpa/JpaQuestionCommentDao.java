package com.luckyGirls.ForYourNutrition.dao.jpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.dao.QuestionCommentDao;
import com.luckyGirls.ForYourNutrition.domain.QuestionComment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class JpaQuestionCommentDao implements QuestionCommentDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public QuestionComment getQuestionComment(int qc_id) throws DataAccessException {
		// TODO Auto-generated method stub
		return em.find(QuestionComment.class, qc_id);
	}

	@Transactional
	@Override
	public void insertQuestionComment(QuestionComment questionComment) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(questionComment);
	}

	@Transactional
	@Override
	public void updateQuestionComment(QuestionComment questionComment) throws DataAccessException {
		// TODO Auto-generated method stub
		em.merge(questionComment);
	}

	@Transactional
	@Override
	public void deleteQuestionComment(QuestionComment questionComment) throws DataAccessException {
		// TODO Auto-generated method stub
		QuestionComment managedQC = em.merge(questionComment);
		em.remove(managedQC);
	}

	@Transactional
	@Override
	public List<QuestionComment> getQuestionCommentListForQuestion(int question_id) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<QuestionComment> query = em.createQuery(
				"SELECT qc FROM QuestionComment qc JOIN qc.question q " + "WHERE q.question_id=?1",
				QuestionComment.class);
		query.setParameter(1, question_id);
		List<QuestionComment> questionCommentList = query.getResultList();
		return questionCommentList;
	}

	@Transactional
	@Override
	public List<QuestionComment> getQuestionCommentListForMember(int member_id) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<QuestionComment> query = em.createQuery(
				"SELECT qc FROM QuestionComment qc JOIN qc.member m " + "WHERE m.member_id=?1", QuestionComment.class);
		query.setParameter(1, member_id);
		List<QuestionComment> questionCommentList = query.getResultList();
		return questionCommentList;
	}

}
