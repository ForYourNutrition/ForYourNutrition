package com.luckyGirls.ForYourNutrition.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.service.IRecommendService;
import com.luckyGirls.ForYourNutrition.service.ItemService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
/**
 *
 * DB에 저장 ctype:0 - target
 ctype:1 - 건강 고민
 ctype:2 - 카테고리
 기준으로 추천 item 저장 ,
 카테고리는 비교를 위한 item List임.
 *
 * **/
@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class IRecommendController {
	@Autowired
	private final IRecommendService iRecommendService;
	@Autowired
	private final ItemService itemService;

	// 개인 추천 아이템 조회
	@GetMapping("/viewMyRecommend")
	public String getPersonalRecommendation(
		@RequestParam("member_id") String memberId,
		HttpSession session, Model model
	) {
			MemberSession ms = (MemberSession)session.getAttribute("ms");
			Member member = ms.getMember();
			List<Item> recommendedItems = iRecommendService.getPersonalRecItem(member.getId());
			System.out.println("cont" + recommendedItems);
			model.addAttribute("recommendedItems", recommendedItems);
			return "item/viewMyRecommend"; // 개인 추천을 보여줄 뷰 이름
	}

	// 전체 추천 아이템 조회
	@GetMapping("/viewItemRecommend")
	public String getAllRecommendation(
		@RequestParam("item_id") int itemId,
		Model model
	) {
			List<Item> recommendedItems = iRecommendService.getAllItems(itemId);
			System.out.println("cont" + recommendedItems);
			model.addAttribute("recommendedItems", recommendedItems);
			return "item/viewItemRecommend"; // 전체 추천을 보여줄 뷰 이름
	}

	// 카테고리별 추천 아이템 조회
	@GetMapping("/viewCategoryRecommend")
	public String getCategoryRecommendation(
		@RequestParam("item_id") int itemId,
		@RequestParam("category") int ctype,
		Model model
	) {
			List<Item> recommendedItems = iRecommendService.getItem(itemId, ctype);
			System.out.println("cont" + recommendedItems);
			model.addAttribute("recommendedItems", recommendedItems);
			return "item/viewCategoryRecommend";
	}

	//비교 상품 불러오기
	@GetMapping("/viewComparativeItem")
	public String getComparativeItem(
		@RequestParam("item_id") int itemId,
		Model model
	) {
			List<Item> comparativeItems = iRecommendService.getItem(itemId, 2);
			Item item = itemService.getItemById(itemId);
			model.addAttribute("comparativeItems", comparativeItems);
			model.addAttribute("mainItem", item);
			return "item/viewComparativeItem";
	}
}
