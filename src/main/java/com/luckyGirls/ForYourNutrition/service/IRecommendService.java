package com.luckyGirls.ForYourNutrition.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckyGirls.ForYourNutrition.domain.IRecommend;
import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.dto.response.ItemGetResponse;
import com.luckyGirls.ForYourNutrition.repository.IRecommendJpaRepository;
import com.luckyGirls.ForYourNutrition.repository.ItemJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class IRecommendService {
	private static ItemJpaRepository itemJpaRepository;
	private static IRecommendJpaRepository iRecommendJpaRepository;

	//아이템 추천
	// public List<ItemGetResponse> getItem(int item_id, ) throws Exception {
	// 	IRecommend iRecommend = iRecommendJpaRepository.findByItemAndCtype()
	// 	Item item = itemJpaRepository.findById(item_id);
	// 	System.out.println("item: " + item.toString());
	// 	return ItemGetResponse.from(item);
	// }
}
