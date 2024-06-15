package com.luckyGirls.ForYourNutrition.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Order;
import com.luckyGirls.ForYourNutrition.domain.OrderItem;
import com.luckyGirls.ForYourNutrition.service.ItemService;
import com.luckyGirls.ForYourNutrition.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;	
	@Autowired
	private ItemService itemService;

	//주문서 작성 폼 띄우기
	@PostMapping("/order/createOrder")
	public String createForm(@RequestParam("item_id") int item_id,
            @RequestParam("quantity") int count,
			Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/login"; // 세션이 만료되었거나 없는 경우 로그인 페이지로 리다이렉트
		}
		Member member = ms.getMember();		
		String memberName = member.getName();

		Item item = itemService.getItemById(item_id);

		model.addAttribute("memberName", memberName);
		model.addAttribute("item", item);
		model.addAttribute("count", count);
		return "order/orderForm";

	}

	@PostMapping("/order/Order")
	public String saveOrder(@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("address") String address,
			@RequestParam("item_id") int item_id,
			@RequestParam("count") int count,
			Model model, HttpSession session) throws Exception {	
		try {
			MemberSession ms = (MemberSession) session.getAttribute("ms");

			Member member = ms.getMember();	

			// 현재 시간 받아오기
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedNow = now.format(formatter);

			Item item = itemService.getItemById(item_id);
			OrderItem orderItem = new OrderItem();
			
			orderItem.createOrderItem(item, count);
			
			Order order = new Order();
			
			order.setMember(member);
			order.setOrderDate(now);
			order.setOrderStatus(0);

			orderItem.setOrders(order);
			
			model.addAttribute("memberName", member.getName());
			model.addAttribute("order", order);
			return "order/orderStatus";
		} catch (NullPointerException ex) {
			model.addAttribute("orderForm", new OrderForm());
			return "order/createOrder";
		}

	}
	
	//삭제, 환불, 배송상태 + 결제구현


}