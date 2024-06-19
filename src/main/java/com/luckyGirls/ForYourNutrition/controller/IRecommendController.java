package com.luckyGirls.ForYourNutrition.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.service.IRecommendService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class IRecommendController {
	private static IRecommendService iRecommendService;

	// 개인 추천 아이템 조회
	@GetMapping("/personalRecommendation")
	public String getPersonalRecommendation(
		@RequestParam("member_id") String memberId,
		HttpSession session, Model model
	) {
		try {
			List<Item> recommendedItems = iRecommendService.getPersonalRecItem(memberId);
			System.out.println("cont" + recommendedItems);
			model.addAttribute("recommendedItems", recommendedItems);
			return "personal_recommendation_view"; // 개인 추천을 보여줄 뷰 이름
		} catch (Exception e) {
			model.addAttribute("error", "Failed to retrieve personal recommendations");
			return "error_view"; // 에러 발생 시 보여줄 뷰 이름
		}
	}

	// 전체 추천 아이템 조회
	@GetMapping("/allRecommendation")
	public String getAllRecommendation(
		@RequestParam("item_id") int itemId,
		Model model
	) {
		try {
			List<Item> recommendedItems = iRecommendService.getAllItems(itemId);
			System.out.println("cont" + recommendedItems);
			model.addAttribute("recommendedItems", recommendedItems);
			return "all_recommendation_view"; // 전체 추천을 보여줄 뷰 이름
		} catch (Exception e) {
			model.addAttribute("error", "Failed to retrieve all recommendations");
			return "error_view"; // 에러 발생 시 보여줄 뷰 이름
		}
	}

	// 카테고리별 추천 아이템 조회
	@GetMapping("/categoryRecommendation")
	public String getCategoryRecommendation(
		@RequestParam("item_id") int itemId,
		@RequestParam("category") String category,
		Model model
	) {
		try {
			List<Item> recommendedItems = iRecommendService.getItem(itemId, category);
			System.out.println("cont" + recommendedItems);
			model.addAttribute("recommendedItems", recommendedItems);
			return "category_recommendation_view"; // 카테고리별 추천을 보여줄 뷰 이름
		} catch (Exception e) {
			model.addAttribute("error", "Failed to retrieve category recommendations");
			return "error_view"; // 에러 발생 시 보여줄 뷰 이름
		}
	}
}
