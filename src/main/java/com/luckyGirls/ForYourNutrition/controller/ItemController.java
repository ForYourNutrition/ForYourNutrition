package com.luckyGirls.ForYourNutrition.controller;

import org.springframework.data.domain.Page;
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

import com.luckyGirls.ForYourNutrition.domain.Item;
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
	public String getItem(@RequestParam("id") int id, Model model) throws Exception {
		ItemGetResponse itemGetResponse = itemService.getItem(id);
		System.out.println(itemGetResponse.toString());
		model.addAttribute("item", itemGetResponse);
		return "item/viewItem"; // "item/viewItem" 뷰 이름 반환
	}

	@GetMapping("/searchItemList.do")
	public Page<Item> getItemList(@RequestParam("text") String text,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "9") int pageSize, HttpSession session, Model model) {
		Page<Item> itemList = itemService.getSearchList(text, page, pageSize);
		model.addAttribute("itemList", itemList);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", itemList.getTotalPages());
		model.addAttribute("text", text);

		System.out.println(itemList.getTotalElements());
		System.out.println(page +", " + itemList.getTotalPages());
		return itemList;
	}

}
