package com.luckyGirls.ForYourNutrition.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

import com.luckyGirls.ForYourNutrition.domain.Address;
import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.CartItem;
import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Order;
import com.luckyGirls.ForYourNutrition.domain.OrderItem;
import com.luckyGirls.ForYourNutrition.service.AddressService;
import com.luckyGirls.ForYourNutrition.service.CartService;
import com.luckyGirls.ForYourNutrition.service.ItemService;
import com.luckyGirls.ForYourNutrition.service.OrderItemService;
import com.luckyGirls.ForYourNutrition.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;	
	@Autowired
	private ItemService itemService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private CartService cartService;

	@PostMapping("/order/createOrder")
	public String createForm(@RequestParam("item_id") int item_id,
			@RequestParam("quantity") int count,
			Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/login"; 
		}
		Member member = ms.getMember();		

		Item item = itemService.getItemById(item_id);
		List<Address> addressList = addressService.getAddressList(member.getMember_id());

		int totalPrice = item.getPrice()*count;

		model.addAttribute("member", member);
		model.addAttribute("item", item);
		model.addAttribute("count", count);
		model.addAttribute("address", addressList.get(0));
		model.addAttribute("totalPrice", totalPrice);
		return "order/orderForm";

	}

	@PostMapping("/order/fromCartOrder")
	public String createForm2(@RequestParam("cart_id") int cart_id,
			Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/login";
		}
		Member member = ms.getMember();		

		Cart cart = cartService.getCartByMember(member);
		List<CartItem> cartList = cart.getCartItemList();

		List<Address> addressList = addressService.getAddressList(member.getMember_id());

		int totalPrice = 0;
		int quantity = 0;
		int price = 0;
		int itemPrice = 0;

		for(int i=0;i<cartList.size(); i++) {
			quantity = cartList.get(i).getQuantity();
			price = cartList.get(i).getItem().getPrice();
			itemPrice = quantity * price;
			totalPrice += itemPrice;

		}
		model.addAttribute("cart_id", cart_id);
		model.addAttribute("member", member);
		model.addAttribute("cartList", cartList);
		model.addAttribute("address", addressList.get(0));
		model.addAttribute("totalPrice", totalPrice);
		return "order/orderCartForm";

	}

	@PostMapping("/order/Order")
	public String saveOrder(@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("zipCode") String zipCode,
			@RequestParam("streetAddress") String streetAddress,
			@RequestParam("detailAddress") String detailAddress,
			@RequestParam("item_id") int item_id,
			@RequestParam("count") int count,
			@RequestParam("payment-method") String paymentType,
			Model model, HttpSession session) throws Exception {	
		try {
			MemberSession ms = (MemberSession) session.getAttribute("ms");

			Member member = ms.getMember();	

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedNow = now.format(formatter);

			Address address = new Address();
			address.setZipCode(zipCode);
			address.setStreetAddress(streetAddress);
			address.setDetailAddress(detailAddress);
			address.setMember(member);

			Item item = itemService.getItemById(item_id);
			OrderItem orderItem = new OrderItem();

			orderItem.setMember(member);
			orderItem.setOrderPrice(item.getPrice());
			orderItem.setCount(count);
			orderItem.setItem(item);
			Order order = new Order();

			order.setMember(member);
			order.setOrderDate(formattedNow);
			order.setOrderStatus(0);
			order.setTotalPrice(item.getPrice()*count);

			model.addAttribute("memberName", member.getName());
			model.addAttribute("order", order);

			int order_id = orderService.insertOrder(order);
			orderItem.setOrders(orderService.getOrder(order_id));

			orderItemService.insertOrderItem(orderItem);

			if(paymentType.equals("bank-transfer")) {
				List<Order> orderList = orderService.getOrderList(member.getMember_id());
				model.addAttribute("orderList", orderList);
				return "order/orderStatus";
			}
			else {
				model.addAttribute("order_id", order_id);
				return "order/cardForm";
			}
		} catch (NullPointerException ex) {
			model.addAttribute("orderForm", new OrderForm());
			return "order/createOrder";
		}

	}

	@PostMapping("/order/saveCartOrder")
	public String saveCartOrder(@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("zipCode") String zipCode,
			@RequestParam("streetAddress") String streetAddress,
			@RequestParam("detailAddress") String detailAddress,
			@RequestParam("cart_id") int cart_id,
			@RequestParam("totalPrice") int totalPrice,
			@RequestParam("payment-method") String paymentType,
			Model model, HttpSession session) throws Exception {	
		try {
			MemberSession ms = (MemberSession) session.getAttribute("ms");

			Member member = ms.getMember();	

			// 현재 시간 받아오기
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedNow = now.format(formatter);

			Address address = new Address();
			address.setZipCode(zipCode);
			address.setStreetAddress(streetAddress);
			address.setDetailAddress(detailAddress);
			address.setMember(member);

			Cart cart = cartService.getCartByMember(member);
			List<CartItem> cartList = cart.getCartItemList();

			Order order = new Order();
			order.setMember(member);
			order.setOrderDate(formattedNow);
			order.setOrderStatus(0);
			order.setTotalPrice(totalPrice);
			int order_id = orderService.insertOrder(order);

			for(int i = 0; i < cartList.size(); i++) {
				OrderItem orderItem = new OrderItem();
				orderItem.setMember(member);
				orderItem.setOrderPrice(cartList.get(i).getItem().getPrice());
				orderItem.setCount(cartList.get(i).getQuantity());
				orderItem.setItem(cartList.get(i).getItem());
				orderItem.setOrders(orderService.getOrder(order_id));
				orderItemService.insertOrderItem(orderItem);
			}


			model.addAttribute("memberName", member.getName());
			model.addAttribute("order", order);

			if(paymentType.equals("bank-transfer")) {
				List<Order> orderList = orderService.getOrderList(member.getMember_id());
				model.addAttribute("orderList", orderList);
				return "order/orderStatus";
			}
			else {
				model.addAttribute("order_id", order_id);
				return "order/cardForm";
			}
		} catch (NullPointerException ex) {
			model.addAttribute("orderForm", new OrderForm());
			return "order/createOrder";
		}

	}

	@PostMapping("/order/orderByCard")
	public String orderByCardForm(@RequestParam("order_id") int order_id,
			Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/login"; 
		}
		Member member = ms.getMember();		

		Order order = orderService.getOrder(order_id);

		order.setOrderStatus(1);
		orderService.updateOrder(order);

		List<Order> orderList = orderService.getOrderList(member.getMember_id());
		model.addAttribute("orderList", orderList);
		return "/order/orderStatus";

	}

	@GetMapping("/order/forMypageOrderStatus")
	public String viewOrderStatus(Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/login"; 
		}
		Member member = ms.getMember();		

		List<Order> orderList = orderService.getOrderList(member.getMember_id());
		model.addAttribute("orderList", orderList);
		return "/order/orderStatus";

	}
	
	@PostMapping("/order/cancleOrder")
	public String cancleOrder(@RequestParam("order_id") int order_id,
			Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/login"; 
		}
		Member member = ms.getMember();		

		Order order = orderService.getOrder(order_id);

		order.setOrderStatus(5);
		orderService.updateOrder(order);

		List<Order> orderList = orderService.getOrderList(member.getMember_id());
		model.addAttribute("orderList", orderList);

		return "order/orderStatus";

	}

	@PostMapping("/order/refundOrder")
	public String refundOrder(@RequestParam("order_id") int order_id,
			Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/login"; 
		}
		Member member = ms.getMember();		

		Order order = orderService.getOrder(order_id);

		order.setOrderStatus(4);
		orderService.updateOrder(order);

		List<Order> orderList = orderService.getOrderList(member.getMember_id());
		model.addAttribute("orderList", orderList);

		return "order/orderStatus";

	}
}