package com.luckyGirls.ForYourNutrition.controller;

import java.util.List;

import com.luckyGirls.ForYourNutrition.domain.Address;
import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Survey;
import com.luckyGirls.ForYourNutrition.domain.Wish;
import com.luckyGirls.ForYourNutrition.service.AddressService;
import com.luckyGirls.ForYourNutrition.service.CartService;
import com.luckyGirls.ForYourNutrition.service.IRecommendService;
import com.luckyGirls.ForYourNutrition.service.ItemService;
import com.luckyGirls.ForYourNutrition.service.MemberService;
import com.luckyGirls.ForYourNutrition.service.SurveyService;
import com.luckyGirls.ForYourNutrition.service.WishService;
import com.luckyGirls.ForYourNutrition.validator.LoginFormValidator;
import com.luckyGirls.ForYourNutrition.validator.MemberFormValidator;
import com.luckyGirls.ForYourNutrition.validator.MemberUpdateFormValidator;
import com.luckyGirls.ForYourNutrition.validator.SearchIdFormValidator;
import com.luckyGirls.ForYourNutrition.validator.SearchPasswordFormValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes("memberSession")
public class MemberController {
	@Autowired
	private IRecommendService iRecommendService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private Authenticator authenticator;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private WishService wishService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private SurveyService surveyService;

	@ModelAttribute("loginForm")
	public LoginForm formBacking(HttpServletRequest request) throws Exception {
		return new LoginForm();
	}
	
	//로그인 폼
	@GetMapping("/member/loginForm")
	public String loginForm(Model model){
		return "member/loginForm";
	}

	//로그인
	@PostMapping("/member/login")
	public ModelAndView handleRequest(HttpServletRequest request, HttpSession session,
			@ModelAttribute("loginForm") LoginForm loginForm, Model model, BindingResult bindingResult) throws Exception {
		
		new LoginFormValidator().validate(loginForm, bindingResult);

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return new ModelAndView("member/loginForm");
		}
		
		Member m = memberService.getMember(loginForm.getId(), loginForm.getPassword());
		
		try {
			authenticator.authenticate(loginForm); // id과 password가 맞는지 검증
			MemberSession memberSession = new MemberSession(m);
			session.setAttribute("ms", memberSession);
			return new ModelAndView("redirect:/main");
		} catch (AuthenticationException e) { // 검증 실패 시
			bindingResult.reject(e.getMessage()); // error message
			return new ModelAndView("member/loginForm");
		}
	}
    
	//아이디 찾기 폼
	@GetMapping("member/searchIdForm")
	public String viewSerchIdForm(Model model){
		model.addAttribute("searchIdForm", new SearchIdForm());
		return "member/searchIdForm";
	}

	//아이디 찾기
	@PostMapping("member/searchId")
	public ModelAndView searchId(HttpServletRequest request, HttpSession session,
			@ModelAttribute("searchIdForm") SearchIdForm searchIdForm, Model model, BindingResult bindingResult) throws Exception {
		new SearchIdFormValidator().validate(searchIdForm, bindingResult);
		
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return new ModelAndView("member/searchIdForm");
		}
		
		String id = memberService.findId(searchIdForm.getEmail(), searchIdForm.getName());
        if (id != null) {
            memberService.sendIdEmail(searchIdForm.getEmail(), id);
            model.addAttribute("type", "아이디");
            model.addAttribute("message", "회원님의 이메일로 아이디가 전송되었습니다.");
        } else {
        	model.addAttribute("type", "아이디");
            model.addAttribute("message", "존재하지 않는 회원입니다.");
        }
        return new ModelAndView("member/searchResult");
	}
	
	//비밀번호 찾기 폼
	@GetMapping("member/searchPasswordForm")
	public String viewSerchPasswordForm(Model model){
		model.addAttribute("searchPasswordForm", new SearchPasswordForm());
		return "member/searchPasswordForm";
	}

	//비밀번호 찾기
	@PostMapping("member/searchPassword")
	public ModelAndView searchPassword(HttpServletRequest request, HttpSession session,
			@ModelAttribute("searchPasswordForm") SearchPasswordForm searchPasswordForm, Model model, BindingResult bindingResult) throws Exception {
		new SearchPasswordFormValidator().validate(searchPasswordForm, bindingResult);
		
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return new ModelAndView("member/searchIdForm");
		}
		
		String pw = memberService.findPassword(searchPasswordForm.getId(), searchPasswordForm.getEmail());
        if (pw != null) {
            memberService.sendPasswordEmail(searchPasswordForm.getEmail(), pw);
            model.addAttribute("type", "비밀번호");
            model.addAttribute("message", "회원님의 이메일로 비밀번호가 전송되었습니다.");
        } else {
        	model.addAttribute("type", "비밀번호");
            model.addAttribute("message", "존재하지 않는 회원입니다.");
        }
        return new ModelAndView("member/searchResult");
	}

	//로그아웃
	@GetMapping("/member/logout")
	public String handleRequest(HttpSession session, Model model) throws Exception {
		session.removeAttribute("ms");
		session.invalidate();
		return "redirect:/main";
	}
	
	//회원가입 폼
	@GetMapping("member/join")
	public String joinForm(Model model, HttpSession session) {
		model.addAttribute("memberForm", new MemberForm());
		return "member/joinForm";
	}
	
	//회원가입
	@PostMapping("member/join")
	public String join(HttpServletRequest request, HttpSession session,
			@ModelAttribute("memberForm") MemberForm memberForm, BindingResult result, Model model) throws Exception {
		new MemberFormValidator().validate(memberForm, result);
		
		if (memberService.getMember(memberForm.getMember().getId()) != null) {
			result.reject("sameIdExist", new Object[] {}, null);
			return "member/joinForm";
		}
		
		if (result.hasErrors()) {
			return "member/joinForm";
		} else {
			memberService.insertMember(memberForm.getMember());
			
			Member new_m = memberService.getMember(memberForm.getMember().getId());
			
			Address address = memberForm.getAddress();
			address.setMember(new_m);
			addressService.insertAddress(address);

			//회원가입 시 자동으로 cart 생성
			Cart cart = new Cart();
			cart.setMember(new_m);;
			cartService.createCart(new_m);
			
			//회원가입 시 자동으로 wish 생성
			Wish wish = new Wish();
			wish.setMember(new_m);
			wishService.createWish(new_m);
			
			model.addAttribute("loginForm", new LoginForm());
			
			return "member/loginForm";
		}
	}

	//회원 수정 폼
	@GetMapping("member/modifyMember")
	public String updateForm(Model model, HttpSession session) {
		try {
			MemberSession ms = (MemberSession)session.getAttribute("ms");
			Member member = ms.getMember();
			MemberForm memberForm = new MemberForm();
			memberForm.setMember(member);
			model.addAttribute("memberForm", memberForm);
			return "member/updateForm";
		}
		catch (NullPointerException ex) {
			model.addAttribute("memberForm", new MemberForm());
			return "member/loginForm";
		}
	}
	
	//회원 수정
	@PostMapping("member/modifyMember")
	public String modifyMember(HttpServletRequest request, HttpSession session,
			@ModelAttribute("memberForm") MemberForm memberForm, BindingResult result, Model model) throws Exception {
		new MemberUpdateFormValidator().validate(memberForm, result);
		
		if (result.hasErrors()) {
			return "member/updateForm";
		} else {
			memberService.updateMember(memberForm.getMember());
			Member m = memberService.getMember(memberForm.getMember().getId());
			MemberSession memberSession = new MemberSession(m);
			session.setAttribute("ms", memberSession);
			System.out.println(memberSession.getMember().getId());
			
			return "redirect:/member/memberDetail";
		}
	}
	
	//회원 삭제
	@GetMapping("member/delete")
	public String deleteMember(HttpSession session) throws Exception {
		MemberSession memberSession = (MemberSession) session.getAttribute("ms");
		memberService.deleteMember(memberSession.getMember().getId());
		session.removeAttribute("memberSession");
		session.invalidate();
		return "redirect:/member/loginForm";
	}
	
	//회원 정보 조회
	@GetMapping("/member/memberDetail")
	public ModelAndView memberDetail(HttpSession session, Model model) throws Exception {
		try {
			MemberSession ms = (MemberSession)session.getAttribute("ms");
			Member member = ms.getMember();
			model.addAttribute("member", member);
			return new ModelAndView("/member/memberDetail");
		}
		catch (NullPointerException ex) {
			model.addAttribute("member", new Member());
			return new ModelAndView("member/loginForm");
		}
	}
	
	//마이페이지(상세)
	@GetMapping("/member/memberInfo")
	public String memberInfo(HttpSession session, Model model){
		return "member/memberInfo";
	}
	
	//마이페이지
	@GetMapping("/member/myPage")
	public ModelAndView myPage(HttpSession session, Model model){
		try {
			MemberSession ms = (MemberSession)session.getAttribute("ms");
			Member member = ms.getMember();
			model.addAttribute("member", member);
			return new ModelAndView("/member/myPage");
		}
		catch (NullPointerException ex) {
			model.addAttribute("member", new Member());
			return new ModelAndView("member/loginForm");
		}
	}
	
	@GetMapping("/header")
	public String getHeader(Model model, HttpSession session) {
		return "header";
	}
	
	@GetMapping({"/main", "/"})
	public ModelAndView getMain(Model model, HttpSession session) {
		try {
			MemberSession ms = (MemberSession)session.getAttribute("ms");
			Member member;
			if(ms != null) {
				member = ms.getMember();
				List<Item> recommendedItems = iRecommendService.getPersonalRecItem(member.getId());
				System.out.println("cont" + recommendedItems);
				model.addAttribute("recommendedItems", recommendedItems);

				Survey sv = surveyService.getSurvey(member.getMember_id());
				if (sv != null) {
					System.out.println(sv.getBirth_year());
					model.addAttribute("isSurvey", true);
				} else {
					model.addAttribute("isSurvey", false);
				}
			}
			List<Item> itemList = itemService.getBestItemList();
			model.addAttribute("itemList", itemList);
			
			return new ModelAndView("main");
		}
		catch (NullPointerException ex) {
			List<Item> itemList = itemService.getBestItemList();

			model.addAttribute("itemList", itemList);
			model.addAttribute("isSurvey", false);
			return new ModelAndView("main");
		}
	}
}
