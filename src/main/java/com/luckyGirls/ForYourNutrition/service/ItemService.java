package com.luckyGirls.ForYourNutrition.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckyGirls.ForYourNutrition.repository.ItemJpaRepository;
import com.luckyGirls.ForYourNutrition.dao.ItemDao;
import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.dto.response.ItemGetResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
	private final ItemJpaRepository itemJpaRepository;

	public ItemGetResponse getItem(int item_id) throws Exception {
		Item item = itemJpaRepository.findById(item_id);
		System.out.println("item: " + item.toString());
		return ItemGetResponse.from(item);
	}

	@Transactional
	public Page<Item> getSearchList(String text, int page, int pageSize, String sortBy){
		Sort sort;
		if ("sales".equals(sortBy)) {
			sort = Sort.by(Sort.Order.desc("sales"));  // 수정: 필드 이름 "sales"로 정렬
		} else if ("dcRate".equals(sortBy)) {
			sort = Sort.by(Sort.Order.desc("dcRate"));  // 수정: 필드 이름 "discountRate"로 정렬
		} else {
			sort = Sort.by(Sort.Order.asc("name"));
		}
		Pageable pageable = PageRequest.of(page, pageSize, sort);

		Page<Item> items = itemJpaRepository.findByNameContainingIgnoreCase(text, pageable);
		System.out.println("2 = " + items.getTotalPages());
		return items;
	}
	
	
	//혜지 추가..
	 @Autowired
	 private ItemDao itemDao;

	 public List<Item> getAllItems() {
		 return itemDao.findAll();
	 }

	 public Item getItemById(int item_id) {
		 return itemDao.findById(item_id);
	 }
}
