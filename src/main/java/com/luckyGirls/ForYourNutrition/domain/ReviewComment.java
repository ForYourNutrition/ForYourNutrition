package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ReviewComment")
@SuppressWarnings("serial")
public class ReviewComment implements Serializable{
	
	 /* Private Fields */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int rc_id;
	
	@ManyToOne
	@JoinColumn(name="review_id")
	private Review review;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	private String content;
	private Date rcdate;
	
	/* JavaBeans Properties */
	public int getRc_id() {
		return rc_id;
	}
	public void setRc_id(int rc_id) {
		this.rc_id = rc_id;
	}
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRcdate() {
		return rcdate;
	}
	public void setRcdate(Date rcdate) {
		this.rcdate = rcdate;
	}
	
	public ReviewComment() {
		super();
	}
	public ReviewComment(int rc_id, Review review, Member member, String content, Date rcdate) {
		super();
		this.rc_id = rc_id;
		this.review = review;
		this.member = member;
		this.content = content;
		this.rcdate = rcdate;
	}
	@Override
	public String toString() {
		return "ReviewComment [rc_id=" + rc_id + ", review=" + review + ", member=" + member + ", content=" + content
				+ ", rcdate=" + rcdate + "]";
	}
	
}
