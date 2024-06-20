package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Question")
@SuppressWarnings("serial")
public class Question implements Serializable {

	/* Private Fields */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_seq")
	@SequenceGenerator(name = "question_seq", sequenceName = "question_seq", allocationSize = 1)
	private int question_id;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	private String qdate;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<QuestionComment> qcList;

	/* JavaBeans Properties */

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getQdate() {
		return qdate;
	}

	public void setQdate(String qdate) {
		this.qdate = qdate;
	}

	public int getMemberId() {
		return member != null ? member.getMember_id() : null;
	}

	public List<QuestionComment> getQcList() {
		return qcList;
	}

	public void setQcList(List<QuestionComment> qcList) {
		this.qcList = qcList;
	}

	public Question() {
		super();
	}

	public Question(int question_id, Member member, String qdate, String title, String content) {
		super();
		this.question_id = question_id;
		this.member = member;
		this.qdate = qdate;
		this.title = title;
		this.content = content;
	}

	public Question(int question_id, Member member, String qdate, String title, String content,
			List<QuestionComment> qcList) {
		super();
		this.question_id = question_id;
		this.member = member;
		this.qdate = qdate;
		this.title = title;
		this.content = content;
		this.qcList = qcList;
	}

	@Override
	public String toString() {
		return "Question [question_id=" + question_id + ", member=" + member + ", qdate=" + qdate + ", title=" + title
				+ ", content=" + content + ", qcList=" + qcList + "]";
	}

}
