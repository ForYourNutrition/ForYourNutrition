package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Review")
@SuppressWarnings("serial")
public class Review implements Serializable {

	/* Private Fields */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wish_seq")
	@SequenceGenerator(name = "wish_seq", sequenceName = "wish_seq", allocationSize = 1)
	private int review_id;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	private String rdate;

	private int rating; // 평가

	@OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<ReviewComment> rcList;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public List<ReviewComment> getRcList() {
		return rcList;
	}

	public void setRcList(List<ReviewComment> rcList) {
		this.rcList = rcList;
	}

	public Review() {
		super();
	}

	public Review(int review_id, Member member, Item item, String title, String content, String rdate, int rating,
			List<ReviewComment> rcList) {
		super();
		this.review_id = review_id;
		this.member = member;
		this.item = item;
		this.title = title;
		this.content = content;
		this.rdate = rdate;
		this.rating = rating;
		this.rcList = rcList;
	}

	@Override
	public String toString() {
		return "Review [review_id=" + review_id + ", member=" + member + ", item=" + item + ", title=" + title
				+ ", content=" + content + ", rdate=" + rdate + ",  rating=" + rating + ", rcList=" + rcList + "]";
	}
}
