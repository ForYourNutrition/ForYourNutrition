package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@SuppressWarnings("serial")
@Table(name = "WishItem")
public class WishItem implements Serializable {
	/* Private Fields */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wishItem_seq")
	@SequenceGenerator(name = "wishItem_seq", sequenceName = "wishItem_seq", allocationSize = 1)
	private int wishItem_id;

	@ManyToOne
	@JoinColumn(name = "wish_id")
	private Wish wish;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	/* JavaBeans Properties */

	public int getWishItem_id() {
		return wishItem_id;
	}

	public void setWishItem_id(int wishItem_id) {
		this.wishItem_id = wishItem_id;
	}

	public Wish getWish() {
		return wish;
	}

	public void setWish(Wish wish) {
		this.wish = wish;
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

	public WishItem() {
		super();
	}

	public WishItem(Member member, Item item) {
		this.member = member;
		this.item = item;
	}

	public WishItem(int wishItem_id, Member member, Item item) {
		super();
		this.wishItem_id = wishItem_id;
		this.member = member;
		this.item = item;
	}
	
	@Override
    public String toString() {
        return "WishItem{" +
               "wishItem_id=" + wishItem_id +
               ", wish=" + (wish != null ? wish.getWish_id() : null) + // 순환 참조를 방지하기 위해 wish의 id만 출력
               '}';
    }
}
