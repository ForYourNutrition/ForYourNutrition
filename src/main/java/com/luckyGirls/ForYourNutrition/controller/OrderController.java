package com.luckyGirls.ForYourNutrition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Order;

import jakarta.servlet.http.HttpServletRequest;

@Controller
//@SessionAttributes({"", "orderForm"})
public class OrderController {
	@Autowired
	private Order order;
	
	@Autowired
	private Member member;
	
//	@Autowired
//	private OrderValidator orderValidator;
	
//	@ModelAttribute("orderForm")
//	public OrderForm createOrderForm() {
//		return new OrderForm();
//	}
	
	@RequestMapping("/order/newOrder.do")
	public String initNewOrder(HttpServletRequest request,
			@ModelAttribute("sessionCart") Cart cart
			//@ModelAttribute("orderForm") OrderForm orderForm
			) throws ModelAndViewDefiningException {
	//	UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		if (cart != null) {
			// Re-read account from DB at team's request.
			//Member member = member.getMember(userSession.getAccount().getMemberId());
			//orderForm.getOrder().initOrder(member_id, cart);
			return "NewOrderForm";	
		}
		else {
			ModelAndView modelAndView = new ModelAndView("Error");
			modelAndView.addObject("message", "An order could not be created because a cart could not be found.");
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}
	/*
	@RequestMapping("/shop/newOrderSubmitted.do")
	public String bindAndValidateOrder(HttpServletRequest request,
			@ModelAttribute("orderForm") OrderForm orderForm, 
			BindingResult result){
		if (orderForm.didShippingAddressProvided() == false) {	
			// from NewOrderForm
			orderValidator.validateCreditCard(orderForm.getOrder(), result);
			orderValidator.validateBillingAddress(orderForm.getOrder(), result);
			if (result.hasErrors()) return "NewOrderForm";
			
			if (orderForm.isShippingAddressRequired() == true) {
				orderForm.setShippingAddressProvided(true);
				return "ShippingForm";
			}
			else {			
				return "ConfirmOrder";
			}
		}
		else {		// from ShippingForm
			orderValidator.validateShippingAddress(orderForm.getOrder(), result);
			if (result.hasErrors()) return "ShippingForm";
			return "ConfirmOrder";
		}
	}
	
	@RequestMapping("/shop/confirmOrder.do")
	protected ModelAndView confirmOrder(
			@ModelAttribute("orderForm") OrderForm orderForm, 
			SessionStatus status) {
		petStore.insertOrder(orderForm.getOrder());
		ModelAndView mav = new ModelAndView("ViewOrder");
		mav.addObject("order", orderForm.getOrder());
		mav.addObject("message", "Thank you, your order has been submitted.");
		status.setComplete();  // remove sessionCart and orderForm from session
		return mav;
	}
	*/
}
