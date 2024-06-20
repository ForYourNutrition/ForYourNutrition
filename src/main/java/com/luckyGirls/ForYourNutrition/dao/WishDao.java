package com.luckyGirls.ForYourNutrition.dao;

import org.springframework.dao.DataAccessException;

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Wish;
import com.luckyGirls.ForYourNutrition.domain.WishItem;

public interface WishDao {
	
	/*wish*/
	void saveWish(Wish wish);
	
	Wish findWishByMember(Member member)throws DataAccessException; 
	
	/*wish item*/
	void saveWishItem(WishItem wishItem)throws DataAccessException; 
	
    void deleteWishItemById(int wishItem_id)throws DataAccessException; 
    
    WishItem findWishItemById(int wishItem_id)throws DataAccessException; 
}