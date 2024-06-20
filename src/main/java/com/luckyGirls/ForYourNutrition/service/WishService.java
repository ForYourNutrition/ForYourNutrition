package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Wish;
import com.luckyGirls.ForYourNutrition.domain.WishItem;

public interface WishService {

	/* wish */
	void createWish(Member member);

	Wish getWishByMember(Member member);

	/* wishList */
	boolean addWishItem(Member member, WishItem wishItem);

	void removeWishItem(Member member, int wishItemId);

	List<WishItem> getWishItemsByMember(Member member);

	WishItem findWishItemById(int wishItemId);
}
