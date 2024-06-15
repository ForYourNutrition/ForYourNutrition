package com.luckyGirls.ForYourNutrition.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.luckyGirls.ForYourNutrition.domain.Item;

public interface ItemDao {
	void saveItem(Item item) throws DataAccessException; //item 추가
	
	void updateItem(Item item) throws DataAccessException; //item 수정
	
	void deleteItem(Item item) throws DataAccessException; //item 삭제
	
	Item findById(int item_id) throws DataAccessException; //item 하나 
	
	List<Item> findAll() throws DataAccessException; //item all
}
