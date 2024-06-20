package com.luckyGirls.ForYourNutrition.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	@Autowired
	private final ItemJpaRepository itemJpaRepository;

	public ItemGetResponse getItem(int item_id) throws Exception {
		Item item = itemJpaRepository.findById(item_id);
		System.out.println("item: " + item.toString());
		return ItemGetResponse.from(item);
	}

	public Page<Item> getAllItem(int page, int pageSize, String sortBy) {
		Sort sort;
		if ("sales".equals(sortBy)) {
			sort = Sort.by(Sort.Order.desc("sales"));
		} else if ("dcRate".equals(sortBy)) {
			sort = Sort.by(Sort.Order.desc("dcRate"));
		} else {
			sort = Sort.by(Sort.Order.asc("name"));
		}
		Pageable pageable = PageRequest.of(page, pageSize, sort);

		Page<Item> items = itemJpaRepository.findAll(pageable);
		System.out.println("2 = " + items.getTotalPages());
		return items;
	}
	@Transactional
	public Page<Item> getSearchList(String text, int page, int pageSize, String sortBy){
		Sort sort;
		if ("sales".equals(sortBy)) {
			sort = Sort.by(Sort.Order.desc("sales"));
		} else if ("dcRate".equals(sortBy)) {
			sort = Sort.by(Sort.Order.desc("dcRate"));
		} else {
			sort = Sort.by(Sort.Order.asc("name"));
		}
		Pageable pageable = PageRequest.of(page, pageSize, sort);

		Page<Item> items = itemJpaRepository.findByNameContainingIgnoreCase(text, pageable);
		System.out.println("2 = " + items.getTotalPages());
		return items;
	}

	@Transactional
	public Page<Item> getCategoryList(String category, int page, int pageSize, String sortBy){
		Sort sort;
		if ("sales".equals(sortBy)) { //판매순
			sort = Sort.by(Sort.Order.desc("sales"));
		} else if ("dcRate".equals(sortBy)) {
			sort = Sort.by(Sort.Order.desc("dcRate"));
		} else {
			sort = Sort.by(Sort.Order.asc("name"));
		}
		Pageable pageable = PageRequest.of(page, pageSize, sort);

		Page<Item> items = itemJpaRepository.findAllByCategory(category, pageable);
		System.out.println("cate = " + items.getTotalPages());
		return items;
	}

	@Transactional
	public Page<Item> getTargetList(int target, int page, int pageSize, String sortBy){
		Sort sort;
		if ("sales".equals(sortBy)) {
			sort = Sort.by(Sort.Order.desc("sales"));
		} else if ("dcRate".equals(sortBy)) {
			sort = Sort.by(Sort.Order.desc("dcRate"));
		} else {
			sort = Sort.by(Sort.Order.asc("name"));
		}
		Pageable pageable = PageRequest.of(page, pageSize, sort);

		Page<Item> items = itemJpaRepository.findAllByTarget(target, pageable);
		System.out.println("tar = " + items.getTotalPages());
		return items;
	}

	@Transactional
	public Page<Item> getEffectList(int effect, int page, int pageSize, String sortBy){
		Sort sort;
		if ("sales".equals(sortBy)) {
			sort = Sort.by(Sort.Order.desc("sales"));
		} else if ("dcRate".equals(sortBy)) {
			sort = Sort.by(Sort.Order.desc("dcRate"));
		} else {
			sort = Sort.by(Sort.Order.asc("name"));
		}
		Pageable pageable = PageRequest.of(page, pageSize, sort);

		Page<Item> items = itemJpaRepository.findAllByEffect(effect, pageable);
		System.out.println("ef = " + items.getTotalPages());
		return items;
	}

	@Transactional
	public Page<Item> getDcRateList(int page, int pageSize, String sortBy){
		Sort sort;
		if ("sales".equals(sortBy)) {
			sort = Sort.by(Sort.Order.desc("sales"));
		} else if ("dcRate".equals(sortBy)) {
			sort = Sort.by(Sort.Order.desc("dcRate"));
		} else {
			sort = Sort.by(Sort.Order.asc("name"));
		}
		Pageable pageable = PageRequest.of(page, pageSize, sort);

		Page<Item> items = itemJpaRepository.findAllByDcRate(pageable);
		System.out.println("dc = " + items.getTotalPages());
		return items;
	}

	@Transactional
	public List<Item> getBestItemList(){
		List<Item> items = itemJpaRepository.findAllOrderBySalesDESC();
		System.out.println("ef = " + items.get(0));
		return items.stream().limit(6).collect(Collectors.toList());
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
