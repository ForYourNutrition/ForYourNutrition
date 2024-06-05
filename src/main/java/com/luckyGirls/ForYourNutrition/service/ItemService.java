package com.luckyGirls.ForYourNutrition.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckyGirls.ForYourNutrition.repository.ItemJpaRepository;
import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.dto.response.ItemGetResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
	private final ItemJpaRepository itemJpaRepository;

	public ItemGetResponse getItem(int id) throws Exception {
		Item item = itemJpaRepository.findById(id);
		System.out.println("item: " + item.toString());
		return ItemGetResponse.from(item);
	}

	@Transactional
	public Page<Item> getSearchList(String text, int page, int pageSize){
		// 페이지 번호와 페이지 크기를 이용하여 페이징된 목록 조회
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<Item> itemList = itemJpaRepository.findByNameContaining(text, pageable);

		return itemList;
	}

}
