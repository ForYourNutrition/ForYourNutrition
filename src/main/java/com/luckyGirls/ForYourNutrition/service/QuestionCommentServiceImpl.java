package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.QuestionCommentDao;
import com.luckyGirls.ForYourNutrition.dao.QuestionDao;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Question;
import com.luckyGirls.ForYourNutrition.domain.QuestionComment;

import jakarta.transaction.Transactional;

@Service
public class QuestionCommentServiceImpl implements QuestionCommentService {

	@Autowired
	private QuestionCommentDao questionCommentDao;

	@Autowired
	private QuestionDao questionDao;

	public QuestionCommentServiceImpl(QuestionCommentDao questionCommentDao) {
		this.questionCommentDao = questionCommentDao;
	}

	@Transactional
	@Override
	public QuestionComment getQuestionComment(int qc_id) {
		// TODO Auto-generated method stub
		return questionCommentDao.getQuestionComment(qc_id);
	}

	@Transactional
	@Override
	public void insertQuestionComment(QuestionComment questionComment) {
		// TODO Auto-generated method stub
		questionCommentDao.insertQuestionComment(questionComment);

	}

	@Transactional
	public void addCommentToQuestion(int question_id, String content, Member member, String qcdate) {
		Question question = questionDao.getQuestion(question_id);
		if (question != null) {
			QuestionComment comment = new QuestionComment();
			comment.setQuestion(question);
			comment.setMember(member);
			comment.setContent(content);
			comment.setQcdate(qcdate);
			questionCommentDao.insertQuestionComment(comment);
		}
	}

	@Transactional
	@Override
	public void updateQuestionComment(QuestionComment questionComment) {
		// TODO Auto-generated method stub
		questionCommentDao.updateQuestionComment(questionComment);
	}

	@Transactional
	@Override
	public void deleteQuestionComment(QuestionComment questionComment) {
		// TODO Auto-generated method stub
		questionCommentDao.deleteQuestionComment(questionComment);
	}

	@Transactional
	@Override
	public List<QuestionComment> getQuestionCommentListForQuestion(int question_id) {
		// TODO Auto-generated method stub
		return questionCommentDao.getQuestionCommentListForQuestion(question_id);
	}

	@Transactional
	@Override
	public List<QuestionComment> getQuestionCommentListForMember(int member_id) {
		// TODO Auto-generated method stub
		return questionCommentDao.getQuestionCommentListForMember(member_id);
	}

}
