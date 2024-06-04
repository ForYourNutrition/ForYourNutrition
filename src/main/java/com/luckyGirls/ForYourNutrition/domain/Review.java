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
@Table(name = "Review")
@SuppressWarnings("serial")
public class Review implements Serializable{
	
	 /* Private Fields */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int review_id;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item;
	
	private String title;
	private Date rdate;
	private String content;
	private String img;
	private int rating;
	
	/* JavaBeans Properties */
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Review() {
		super();
	}
	public Review(int review_id, Member member, Item item, String title, Date rdate, String content, String img,
			int rating) {
		super();
		this.review_id = review_id;
		this.member = member;
		this.item = item;
		this.title = title;
		this.rdate = rdate;
		this.content = content;
		this.img = img;
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Review [review_id=" + review_id + ", member=" + member + ", item=" + item + ", title=" + title
				+ ", rdate=" + rdate + ", content=" + content + ", img=" + img + ", rating=" + rating + "]";
	}
	
}
