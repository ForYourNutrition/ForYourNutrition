package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Wish;
import com.luckyGirls.ForYourNutrition.domain.WishItem;

public interface WishService {
	
	/*wish*/
	void createWish(Member member);
	Wish getWishByMember(Member member);
	
	/*wishList*/
	void addWishItem(Member member, WishItem wishItem);
	void removeWishItem(Member member, int wishItemId);
	
	// 추가: 멤버의 위시리스트 아이템 목록을 반환하는 메서드
	List<WishItem> getWishItemsByMember(Member member);
		
	// 추가: 아이템 ID를 통해 위시리스트 아이템을 찾는 메서드
	WishItem findWishItemById(int wishItemId);
}
