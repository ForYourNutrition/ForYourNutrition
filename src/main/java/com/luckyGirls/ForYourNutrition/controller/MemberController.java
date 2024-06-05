package com.luckyGirls.ForYourNutrition.controller;

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.service.MemberService;
import com.luckyGirls.ForYourNutrition.validator.LoginFormValidator;
import com.luckyGirls.ForYourNutrition.validator.MemberFormValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes("memberSession")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private Authenticator authenticator;

	@ModelAttribute("loginForm")
	public LoginForm formBacking(HttpServletRequest request) throws Exception {
		return new LoginForm();
	}
	
	//로그인 폼
	@GetMapping("/member/loginForm.do")
	public String loginForm(Model model){
		return "member/loginForm";
	}

	//로그인
	@PostMapping("/member/login.do")
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
			return new ModelAndView("redirect:/main.do");
		} catch (AuthenticationException e) { // 검증 실패 시
			bindingResult.reject(e.getMessage()); // error message
			return new ModelAndView("member/loginForm");
		}
	}
    
	/*
	@RequestMapping(value = "/searchId.do", method = RequestMethod.GET)
	public ModelAndView viewSerchIdForm(HttpServletRequest request) throws Exception {
		//추후 구현
	}

	@RequestMapping(value = "/searchId.do", method = RequestMethod.POST)
	public ModelAndView searchId(HttpServletRequest request,
			@RequestParam("name") String name,
			@RequestParam("email") String email) throws Exception {
		//추후 구현
	}

	@RequestMapping(value = "/searchPwd.do", method = RequestMethod.GET)
	public ModelAndView viewSearchPwdForm(HttpServletRequest request) throws Exception {
		//추후 구현
	}

	@RequestMapping(value = "/searchPwd.do", method = RequestMethod.POST)
	public ModelAndView searchPwd(HttpServletRequest request,
			@RequestParam("id") String id,
			@RequestParam("email") String email) throws Exception {
		//추후 구현
	}

	 */

	//로그아웃
	@RequestMapping("/member/logout.do")
	public String handleRequest(HttpSession session, Model model) throws Exception {
		session.removeAttribute("ms");
		session.invalidate();
		return "redirect:/main.do";
	}
	
	//회원가입 폼
	@GetMapping("member/join.do")
	public String joinForm(Model model, HttpSession session) {
		model.addAttribute("memberForm", new MemberForm());
		return "member/joinForm";
	}
	
	//회원가입
	@PostMapping("member/join.do")
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
			model.addAttribute("loginForm", new LoginForm());
			return "member/loginForm";
		}
	}

	//회원 수정 폼
	@GetMapping("member/modifyMember.do")
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
	@PostMapping("member/modifyMember.do")
	public String modifyMember(HttpServletRequest request, HttpSession session,
			@ModelAttribute("memberForm") MemberForm memberForm, BindingResult result, Model model) throws Exception {
		new MemberFormValidator().validate(memberForm, result);
		
		if (result.hasErrors()) {
			return "member/updateForm";
		} else {
			memberService.updateMember(memberForm.getMember());
			Member m = memberService.getMember(memberForm.getMember().getId());
			MemberSession memberSession = new MemberSession(m);
			session.setAttribute("ms", memberSession);
			System.out.println(memberSession.getMember().getId());
			
			return "redirect:/member/memberInfo.do";
		}
	}
	
	//회원 삭제
	@GetMapping("member/delete.do")
	public String deleteMember(HttpSession session) throws Exception {
		MemberSession memberSession = (MemberSession) session.getAttribute("ms");
		memberService.deleteMember(memberSession.getMember().getId());
		session.removeAttribute("memberSession");
		session.invalidate();
		return "redirect:/member/loginForm.do";
	}
	
	//회원 정보 조회
	@GetMapping("/member/memberInfo.do")
	public ModelAndView memberInfo(HttpSession session, Model model) throws Exception {
		try {
			MemberSession ms = (MemberSession)session.getAttribute("ms");
			Member member = ms.getMember();
			model.addAttribute("member", member);
			return new ModelAndView("/member/memberInfo");
		}
		catch (NullPointerException ex) {
			model.addAttribute("member", new Member());
			return new ModelAndView("member/loginForm");
		}
	}
	
	@GetMapping("/header")
	public String getHeader(Model model, HttpSession session) {
		try {
			MemberSession memberSession = (MemberSession) session.getAttribute("ms");
			System.out.println(memberSession.getMember().getId());
			System.out.println(memberSession != null);
			model.addAttribute("isLoggedIn", true);
			return "header";
		}
		catch (NullPointerException ex) {
			model.addAttribute("isLoggedIn", false);
			return "header";
		}
	}
	
	@GetMapping("/main.do")
	public String getMain(Model model, HttpSession session) {
		try {
			MemberSession ms = (MemberSession) session.getAttribute("ms");
			Member member = ms.getMember();
			System.out.println(ms.getMember().getId());
			System.out.println(ms != null);
			model.addAttribute("isLoggedIn", true);
			model.addAttribute("member", member);
			return "main";
		}
		catch (NullPointerException ex) {
			model.addAttribute("isLoggedIn", false);
			return "main";
		}
	}
}
