package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "QuestionComment")
public class QuestionComment implements Serializable {

	/* Private Fields */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questionCommment_seq")
	@SequenceGenerator(name = "questionCommment_seq", sequenceName = "questionCommment_seq", allocationSize = 1)
	private int qc_id;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;

	@Column(nullable = false)
	private String content;
	private String qcdate;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	/* JavaBeans Properties */

	public int getQc_id() {
		return qc_id;
	}

	public void setQc_id(int qc_id) {
		this.qc_id = qc_id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getQcdate() {
		return qcdate;
	}

	public void setQcdate(String qcdate) {
		this.qcdate = qcdate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public QuestionComment() {
		super();
	}

	public QuestionComment(int qc_id, Question question, String content, String qcdate, Member member) {
		super();
		this.qc_id = qc_id;
		this.question = question;
		this.content = content;
		this.qcdate = qcdate;
		this.member = member;
	}

	@Override
	public String toString() {
		return "QuestionComment [qc_id=" + qc_id + ", question=" + question + ", content=" + content + ", qcdate="
				+ qcdate + ", member=" + member + "]";
	}
}
