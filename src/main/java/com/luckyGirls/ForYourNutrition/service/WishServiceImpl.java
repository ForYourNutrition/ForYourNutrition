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
public class WishServiceImpl implements WishService{

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
    public void addWishItem(Member member, WishItem wishItem) {
        Wish wish = wishDao.findWishByMember(member);
        if (wish == null) {
            wish = new Wish(member);
            wishDao.saveWish(wish);
        }
        wishItem.setWish(wish);
        wishDao.saveWishItem(wishItem);  // 저장
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
