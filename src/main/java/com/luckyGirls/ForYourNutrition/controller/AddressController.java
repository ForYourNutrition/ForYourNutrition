package com.luckyGirls.ForYourNutrition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luckyGirls.ForYourNutrition.domain.Address;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.service.AddressService;
import com.luckyGirls.ForYourNutrition.validator.AddressFormValidator;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/member/viewAddressList")
    public String viewAddress(HttpSession session, Model model) {
    	MemberSession ms = (MemberSession)session.getAttribute("ms");
		Member member = ms.getMember();
        List<Address> addresses = addressService.getAddressList(member.getMember_id());
        model.addAttribute("addresses", addresses);
        return "/member/viewAddressList";
    }

    @GetMapping("/member/addressForm")
    public String viewAddressForm(Model model) {
        model.addAttribute("address", new Address());
        return "/member/addressForm";
    }

    @PostMapping("/member/addAddress")
    public String addAddress(@ModelAttribute("address") Address address, BindingResult result, HttpSession session) {
    	new AddressFormValidator().validate(address, result);
		
		if (result.hasErrors()) {
			return "member/addressForm";
		} else {
			MemberSession ms = (MemberSession)session.getAttribute("ms");
			Member member = ms.getMember();
	        address.setMember(member);
	        addressService.insertAddress(address);
	        return "redirect:/member/viewAddressList";
		}
    	
    }
 
    @PostMapping("/member/deleteAddress")
    public String deleteAddress(@RequestParam("address_id") int addressId) {
        addressService.deleteAddress(addressId);
        return "redirect:/member/viewAddressList";
    }

    @GetMapping("/member/updateAddress")
    public String editAddressForm(@RequestParam("address_id") int addressId, Model model) {
        Address address = addressService.getAddress(addressId);
        model.addAttribute("address", address);
        return "member/addressUpdateForm";
    }

    @PostMapping("/member/updateAddress")
    public String updateAddress(@ModelAttribute("address") Address address, BindingResult result, HttpSession session) {
    	new AddressFormValidator().validate(address, result);
		
		if (result.hasErrors()) {
			return "member/addressUpdateForm";
		} else {
	    	MemberSession ms = (MemberSession)session.getAttribute("ms");
			Member member = ms.getMember();
	        address.setMember(member);
	        addressService.updateAddress(address);
	        return "redirect:/member/viewAddressList";
		}
    }
    
}
