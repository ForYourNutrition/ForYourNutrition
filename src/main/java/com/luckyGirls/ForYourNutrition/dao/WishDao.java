package com.luckyGirls.ForYourNutrition.dao;

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Wish;
import com.luckyGirls.ForYourNutrition.domain.WishItem;

public interface WishDao {
	/*wish*/
	void saveWish(Wish wish);
	Wish findWishByMember(Member member);
	
	/*wish item*/
	void saveWishItem(WishItem wishItem);
    void deleteWishItemById(int wishItem_id);
    WishItem findWishItemById(int wishItem_id);
}