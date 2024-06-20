package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "Wish")
@SuppressWarnings("serial")
public class Wish implements Serializable {

	/* Private Fields */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wish_seq")
	@SequenceGenerator(name = "wish_seq", sequenceName = "wish_seq", allocationSize = 1)
	private int wish_id;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "wish", cascade = CascadeType.REMOVE)
	private List<WishItem> wishItemList = new ArrayList<>();

	public int getWish_id() {
		return wish_id;
	}

	public void setWish_id(int wish_id) {
		this.wish_id = wish_id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<WishItem> getWishItemList() {
		return wishItemList;
	}

	public void setWishItemList(List<WishItem> wishItemList) {
		this.wishItemList = wishItemList;
	}

	public Wish(int wish_id, Member member, List<WishItem> wishItemList) {
		super();
		this.wish_id = wish_id;
		this.member = member;
		this.wishItemList = wishItemList;
	}

	public Wish() {
		super();
	}

	public Wish(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Wish{" + "wish_id=" + wish_id + ", member=" + member + ", wishItemList=" + wishItemList.size() + '}';
	}
}
