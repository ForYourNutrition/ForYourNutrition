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

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Order;
import com.luckyGirls.ForYourNutrition.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;	

	//주문서 작성 폼 띄우기
	@GetMapping("/order/createOrder")
	public String createForm(Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/login"; // 세션이 만료되었거나 없는 경우 로그인 페이지로 리다이렉트
		}
		Member member = ms.getMember();		
		String memberName = member.getName();

		model.addAttribute("memberName", memberName);
		return "order/orderForm";
	}

	@PostMapping("/order/Order")
	public String saveQuestion(HttpServletRequest request, HttpSession session,
			@ModelAttribute("orderForm") QuestionForm questionForm, BindingResult result, Model model) throws Exception {

		try {
			MemberSession ms = (MemberSession) session.getAttribute("ms");

	        Member member = ms.getMember();	

	        // 현재 시간 받아오기
	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedNow = now.format(formatter);

	        Order order = new Order();
	        order.setMember(member);
	        order.setOrderDate(now);
	        order.setOrderStatus(0);

	        model.addAttribute("memberName", member.getName());
	        model.addAttribute("order", order);
	        return "order/orderStatus";
		} catch (NullPointerException ex) {
            model.addAttribute("orderForm", new OrderForm());
            return "order/orderForm";
        }
	}


}