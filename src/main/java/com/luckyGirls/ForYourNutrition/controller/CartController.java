package com.luckyGirls.ForYourNutrition.controller;

import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.CartItem;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.service.CartService;
import com.luckyGirls.ForYourNutrition.service.ItemService;
import com.luckyGirls.ForYourNutrition.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    
    @Autowired
    private ItemService itemService;

    @Autowired
    private MemberService memberService;
    
    

    @GetMapping("/cart/viewCart")
    public String viewCart(HttpSession session, Model model) {
        MemberSession ms = (MemberSession) session.getAttribute("ms");
        Member member = ms.getMember();
        if (ms == null || member == null) {
            return "redirect:/login";
        }
        
        System.out.println("20");
        Cart cart = cartService.getCartByMember(member);
        System.out.println("21");
        model.addAttribute("cart", cart);
        System.out.println("22");
        return "cart/viewCart";
    }

    @PostMapping("/cart/addCartItem")
    public String addCartItem(@RequestParam int itemId, @RequestParam int quantity, HttpSession session) {
        MemberSession ms = (MemberSession) session.getAttribute("ms");
        if (ms == null) {
            return "redirect:/login";
        }
        Member member = ms.getMember();
        CartItem cartItem = new CartItem();
        cartItem.setItem(itemService.getItemById(itemId));
        cartItem.setQuantity(quantity);
        cartService.addCartItem(member, cartItem);
        return "redirect:/cart/viewCart";
    }

    @PostMapping("/cart/removeCartItem")
    public String removeCartItem(@RequestParam int cartItem_id, HttpSession session) {
        MemberSession ms = (MemberSession) session.getAttribute("ms");
        if (ms == null) {
            return "redirect:/login";
        }
        Member member = ms.getMember();
        cartService.removeCartItem(member, cartItem_id);
        return "redirect:/cart/viewCart";
    }

    @PostMapping("/cart/addQuantity")
    public String addQuantity(@RequestParam int cartItem_id, @RequestParam int quantity, HttpSession session) {
        MemberSession ms = (MemberSession) session.getAttribute("ms");
        if (ms == null) {
            return "redirect:/login";
        }
        Member member = ms.getMember();
        cartService.addQuantity(member, cartItem_id, quantity);
        return "redirect:/cart/viewCart";
    }

    @PostMapping("/cart/removeQuantity")
    public String removeQuantity(@RequestParam int cartItem_id, @RequestParam int quantity, HttpSession session) {
        MemberSession ms = (MemberSession) session.getAttribute("ms");
        if (ms == null) {
            return "redirect:/login";
        }
        Member member = ms.getMember();
        cartService.removeQuantity(member, cartItem_id, quantity);
        return "redirect:/cart/viewCart";
    }

  	/*@GetMapping("/order/createOrder")
    public String createOrderForm(HttpSession session, Model model) {
        MemberSession ms = (MemberSession) session.getAttribute("ms");
        if (ms == null) {
            return "redirect:/login";
        }
        Member member = ms.getMember();
        model.addAttribute("cart", cartService.getCartByMember(member));
        return "order/createOrder";
    }*/
}
