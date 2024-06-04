package com.luckyGirls.ForYourNutrition.service;

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
		System.out.println("si" + id);
		Item item = itemJpaRepository.findById(id);
		System.out.println("ser" + item.toString());
		return ItemGetResponse.from(item);
	}

}
