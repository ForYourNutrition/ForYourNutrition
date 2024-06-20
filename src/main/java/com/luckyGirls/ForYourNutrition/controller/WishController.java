package com.luckyGirls.ForYourNutrition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Wish;
import com.luckyGirls.ForYourNutrition.domain.WishItem;
import com.luckyGirls.ForYourNutrition.service.ItemService;
import com.luckyGirls.ForYourNutrition.service.WishService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WishController {

	@Autowired
	private WishService wishService;

	@Autowired
	private ItemService itemService;

	@GetMapping("/wish/viewWish")
	public String viewWish(HttpSession session, Model model) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/member/loginForm";
		}
		Member member = ms.getMember();
		Wish wish = null;

		try {
			wish = wishService.getWishByMember(member);
			if (wish != null) {
				System.out.println(wish.getWishItemList().toString());
				model.addAttribute("wishItems", wish.getWishItemList());
			} else {
				System.out.println("Wish is null");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String memNickName = member.getNickname();
		
		model.addAttribute("memNickName", memNickName);
		model.addAttribute("wish", wish);
		return "wish/viewWish";
	}

	@PostMapping("/wish/addWishItem")
	public String addWishItem(@RequestParam int item_id, HttpSession session, Model model) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/member/loginForm";
		}

		Member member = ms.getMember();
		Item item = itemService.getItemById(item_id);

		Wish wish = wishService.getWishByMember(member);
		WishItem wishItem = new WishItem(member, item);
		boolean added = wishService.addWishItem(member, wishItem);

		System.out.println("wishItem 정보: " + wishItem.getWishItem_id());
		System.out.println("wishItem mem 정보: " + wishItem.getMember().getName());

		if (!added) {
			String memNickName = member.getNickname();
			
			model.addAttribute("error", "이미 Wish에 있는 상품입니다.");
			model.addAttribute("wish", wish);
			model.addAttribute("memNickName", memNickName);
			return "wish/viewWish";
		}
		return "redirect:/wish/viewWish";
	}

	@PostMapping("/wish/removeWishItem")
	public String removeWishItem(@RequestParam int wishItem_id, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/member/loginForm";
		}
		Member member = ms.getMember();
		wishService.removeWishItem(member, wishItem_id);

		return "redirect:/wish/viewWish";
	}

}
