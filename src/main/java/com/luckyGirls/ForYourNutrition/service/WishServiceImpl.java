package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.WishDao;
import com.luckyGirls.ForYourNutrition.domain.Wish;
import com.luckyGirls.ForYourNutrition.domain.WishItem;
import com.luckyGirls.ForYourNutrition.domain.Member;

import jakarta.transaction.Transactional;

@Service
public class WishServiceImpl implements WishService {

	@Autowired
	private WishDao wishDao;

	@Transactional
	@Override
	public void createWish(Member member) {
		// TODO Auto-generated method stub
		Wish wish = new Wish(member);
		wishDao.saveWish(wish);
	}

	@Transactional
	@Override
	public Wish getWishByMember(Member member) {
		// TODO Auto-generated method stub
		Wish wish = wishDao.findWishByMember(member);
		if (wish != null) {
			Hibernate.initialize(wish.getWishItemList()); // 강제 초기화
		}
		return wish;
	}

	@Transactional
	@Override
	public boolean addWishItem(Member member, WishItem wishItem) {
		Wish wish = wishDao.findWishByMember(member);
		if (wish == null) {
			wish = new Wish(member);
			wishDao.saveWish(wish);
		} else {
			for (WishItem item : wish.getWishItemList()) {
				if (item.getItem().getItem_id() == wishItem.getItem().getItem_id()) {
					return false; // 이미 존재하는 경우 false 반환
				}
			}
		}
		wishItem.setWish(wish);
		wishDao.saveWishItem(wishItem);
		return true; // 성공적으로 추가된 경우 true 반환
	}

	@Transactional
	@Override
	public void removeWishItem(Member member, int wishItemId) {
		// TODO Auto-generated method stub
		wishDao.deleteWishItemById(wishItemId);
	}

	@Transactional
	@Override
	public List<WishItem> getWishItemsByMember(Member member) {
		Wish wish = wishDao.findWishByMember(member);
		return wish != null ? wish.getWishItemList() : null;
	}

	@Transactional
	@Override
	public WishItem findWishItemById(int wishItemId) {
		return wishDao.findWishItemById(wishItemId);
	}
}
