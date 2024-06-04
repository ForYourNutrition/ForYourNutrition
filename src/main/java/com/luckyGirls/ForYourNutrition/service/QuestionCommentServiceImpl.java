package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.QuestionCommentDao;
import com.luckyGirls.ForYourNutrition.domain.QuestionComment;

@Service("questionCommentServiceImpl")
public class QuestionCommentServiceImpl implements QuestionCommentService{

	@Autowired
	@Qualifier("jpaQuestionCommentDao")
	private QuestionCommentDao questionCommentDao;
	
	@Override
	public QuestionComment getQuestionComment(int qc_id) {
		// TODO Auto-generated method stub
		return questionCommentDao.getQuestionComment(qc_id);
	}

	@Override
	public void insertQuestionComment(QuestionComment questionComment) {
		// TODO Auto-generated method stub
		questionCommentDao.insertQuestionComment(questionComment);
		
	}

	@Override
	public void updateQuestionComment(QuestionComment questionComment) {
		// TODO Auto-generated method stub
		questionCommentDao.updateQuestionComment(questionComment);
	}

	@Override
	public void deleteQuestionComment(QuestionComment questionComment) {
		// TODO Auto-generated method stub
		questionCommentDao.deleteQuestionComment(questionComment);
	}

	@Override
	public List<QuestionComment> getQuestionCommentListForQuestion(int question_id) {
		// TODO Auto-generated method stub
		return questionCommentDao.getQuestionCommentListForQuestion(question_id);
	}

	@Override
	public List<QuestionComment> getQuestionCommentListForMember(int member_id) {
		// TODO Auto-generated method stub
		return questionCommentDao.getQuestionCommentListForMember(member_id);
	}

}
