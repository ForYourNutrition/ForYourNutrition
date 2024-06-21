package com.luckyGirls.ForYourNutrition.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.luckyGirls.ForYourNutrition.domain.Review;
import com.luckyGirls.ForYourNutrition.dto.response.ItemGetResponse;
import com.luckyGirls.ForYourNutrition.service.IRecommendService;
import com.luckyGirls.ForYourNutrition.service.ItemService;
import com.luckyGirls.ForYourNutrition.service.ReviewService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
@SessionAttributes("memberSession")
public class ItemController {
	private final ItemService itemService;
	private final IRecommendService iRecommendService;
	
	@Autowired
	private ReviewService reviewService;

	@GetMapping("/viewItem")
	public String getItem(@RequestParam("item_id") int item_id, Model model, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
		ItemGetResponse itemGetResponse = itemService.getItem(item_id);
		System.out.println(itemGetResponse.toString());
		model.addAttribute("item", itemGetResponse);
		System.out.println("img - " + itemGetResponse.getImg());
		Item item = itemService.getItemById(item_id);
        
        int pageSize = 10; // 한 페이지에 표시할 리뷰 수
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Review> reviewPage = reviewService.getReviewListForItem(item_id, pageable);
        
        int totalPages = reviewPage.getTotalPages();
        int startPage = Math.max(1, page - 2);
        int endPage = Math.min(totalPages, page + 2);

        model.addAttribute("item", item);
		model.addAttribute("img", item.getImg());
        model.addAttribute("reviews", reviewPage.getContent());
        model.addAttribute("nowPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
		List<Item> comparativeItems = iRecommendService.getItem(item_id, 2);
		model.addAttribute("comparativeItems", comparativeItems);
		return "item/viewItem"; // "item/viewItem" 뷰 이름 반환
	}

	@GetMapping("/searchItemList")
	public Page<Item> getItemList(@RequestParam("text") String text,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "9") int pageSize,
		@RequestParam(defaultValue = "sales") String sortBy,
		HttpSession session, Model model) {
		Page<Item> itemList = itemService.getSearchList(text, page, pageSize, sortBy);

		model.addAttribute("itemList", itemList);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", itemList.getTotalPages());
		model.addAttribute("text", text);
		model.addAttribute("sortBy", sortBy);

		System.out.println(itemList.getTotalElements());
		System.out.println(page +", " + itemList.getTotalPages());
		return itemList;
	}

	@GetMapping("/shopByCategoryItem")
	public Page<Item> getCategoryItemList(@RequestParam(value = "category", defaultValue = "비타민") String category,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "9") int pageSize,
		@RequestParam(defaultValue = "sales") String sortBy,
		@RequestParam(value = "all", defaultValue = "false", required = false) Boolean all,
		HttpSession session, Model model) {
		Page<Item> itemList;
		if(all) {
			itemList = itemService.getAllItem(page, pageSize, sortBy);

			model.addAttribute("itemList", itemList);
			model.addAttribute("currentPage", page);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("totalPages", itemList.getTotalPages());
			model.addAttribute("category", category);
			model.addAttribute("sortBy", sortBy);
			model.addAttribute("all", all);

			return itemList;
		}
		itemList = itemService.getCategoryList(category, page, pageSize, sortBy);

		model.addAttribute("itemList", itemList);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", itemList.getTotalPages());
		model.addAttribute("category", category);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("all", all);

		System.out.println(itemList.getTotalElements());
		System.out.println(page +", " + itemList.getTotalPages());
		return itemList;
	}

	@GetMapping("/shopByTargetItem")
	public Page<Item> getTargetItemList(@RequestParam(value = "target", defaultValue = "2") int target,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "9") int pageSize,
		@RequestParam(defaultValue = "sales") String sortBy,
		@RequestParam(value = "all", defaultValue = "false", required = false) Boolean all,
		HttpSession session, Model model) {
		Page<Item> itemList;
		if(all) {
			itemList = itemService.getAllItem(page, pageSize, sortBy);

			model.addAttribute("itemList", itemList);
			model.addAttribute("currentPage", page);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("totalPages", itemList.getTotalPages());
			model.addAttribute("target", target);
			model.addAttribute("sortBy", sortBy);
			model.addAttribute("all", all);

			return itemList;
		}
		itemList = itemService.getTargetList(target, page, 9, sortBy);

		model.addAttribute("itemList", itemList);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", 9);
		model.addAttribute("totalPages", itemList.getTotalPages());
		model.addAttribute("target", target);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("all", all);

		System.out.println(itemList.getTotalElements());
		System.out.println(page +", " + itemList.getTotalPages());
		return itemList;
	}

	@GetMapping("/shopByEffectItem")
	public Page<Item> getEffectItemList(@RequestParam(value = "effect", defaultValue = "3") int effect,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "9") int pageSize,
		@RequestParam(defaultValue = "sales") String sortBy,
		@RequestParam(value = "all", defaultValue = "false", required = false) Boolean all,
		HttpSession session, Model model) {
		Page<Item> itemList;
		if(all) {
			itemList = itemService.getAllItem(page, pageSize, sortBy);

			model.addAttribute("itemList", itemList);
			model.addAttribute("currentPage", page);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("totalPages", itemList.getTotalPages());
			model.addAttribute("effect", effect);
			model.addAttribute("sortBy", sortBy);
			model.addAttribute("all", all);

			return itemList;
		}
		itemList = itemService.getEffectList(effect, page, pageSize, sortBy);

		model.addAttribute("itemList", itemList);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", itemList.getTotalPages());
		model.addAttribute("effect", effect);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("all", all);

		System.out.println(itemList.getTotalElements());
		System.out.println(page +", " + itemList.getTotalPages());
		return itemList;
	}

	@GetMapping("/shopByDCItem")
	public Page<Item> getDcItemList(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "9") int pageSize,
		@RequestParam(defaultValue = "sales") String sortBy,
		HttpSession session, Model model) {
		Page<Item> itemList = itemService.getDcRateList(page, pageSize, sortBy);

		model.addAttribute("itemList", itemList);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", itemList.getTotalPages());
		model.addAttribute("sortBy", sortBy);

		System.out.println(itemList.getTotalElements());
		System.out.println(page +", " + itemList.getTotalPages());
		return itemList;
	}

	@GetMapping("/viewBestItemList")
	public List<Item> getDcItemList(Model model) {
		List<Item> itemList = itemService.getBestItemList();

		model.addAttribute("itemList", itemList);

		System.out.println(itemList.size());
		return itemList;
	}
}
