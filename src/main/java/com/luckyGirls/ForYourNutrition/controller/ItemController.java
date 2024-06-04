package com.luckyGirls.ForYourNutrition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.luckyGirls.ForYourNutrition.dto.response.ItemGetResponse;
import com.luckyGirls.ForYourNutrition.service.ItemService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
@SessionAttributes("memberSession")
public class ItemController {
	private final ItemService itemService;

	@GetMapping("/viewItem.do")
	public String getItem(HttpServletRequest request, @RequestParam("id") int id, HttpSession session, Model model) throws Exception {
		ItemGetResponse itemGetResponse = itemService.getItem(id);
		System.out.println(id);
		System.out.println(itemGetResponse.toString());
		model.addAttribute("item", itemGetResponse);
		return "item/viewItem"; // "item/viewItem" 뷰 이름 반환
	}


	// jpetStore 예제
	// @RequestMapping("/shop/viewItem.do")
	// public String handleRequest(
	// 	@RequestParam("itemId") String itemId,
	// 	ModelMap model) throws Exception {
	// 	Item item = this.petStore.getItem(itemId);
	// 	model.put("item", item);
	// 	model.put("product", item.getProduct());
	// 	return "Item";
	// }
}
