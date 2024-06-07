package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Question")
@SuppressWarnings("serial")
public class Question implements Serializable{
	
	 /* Private Fields */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_seq")
    @SequenceGenerator(name = "question_seq", sequenceName = "question_seq", allocationSize = 1) 
	private int question_id;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	private String qdate;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String content;
	
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
	@Override
	public String toString() {
		return "Question [question_id=" + question_id + ", member=" + member + ", qdate=" + qdate + ", title=" + title
				+ ", content=" + content + "]";
	}

}
