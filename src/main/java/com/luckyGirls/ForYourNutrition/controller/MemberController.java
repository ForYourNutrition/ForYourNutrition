package com.luckyGirls.ForYourNutrition.controller;

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.service.MemberService;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.util.WebUtils;

@Controller
@SessionAttributes("memberSession")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@Value("ModifyAccountForm")
	private String formViewName;

	/*
	//회원가입 시, 존재하는 session = return account, 존재하지 않으면 새로운 account form return
	@Autowired
	private AccountFormValidator validator;
	public void setValidator(AccountFormValidator validator) {
		this.validator = validator;
	}
	*/

	/*
	@ModelAttribute("memberForm")
	public MemberForm formBackingObject(HttpServletRequest request) throws Exception {
		MemberSession memberSession = (MemberSession) WebUtils.getSessionAttribute(request, "memberSession");
		return new MemberForm(memberService.getMember(memberSession.getMember().getId()));
	}
	*/

	//로그인 폼
	@GetMapping("/member/loginForm.do")
	public String loginForm(Model model){
		model.addAttribute("member", new Member());
		return "member/loginForm";
	}

	//로그인
	@PostMapping("/member/login.do")
	public ModelAndView login(HttpServletRequest request,
			@ModelAttribute("member") Member member,
			@RequestParam(value="forwardAction", required=false) String forwardAction,
			HttpSession session,
			Model model) throws Exception {
		/*
		new LoginFormValidator().validate(loginForm, bindingResult);

		// 검증 오류 발생 시 다시 form view로 이동
		if (bindingResult.hasErrors()) {
			return new ModelAndView(formViewName);
		}
		*/
		
		Member m = memberService.getMember(member.getId(), member.getPassword());
		
		if (m == null) {
			return new ModelAndView("Error", "message", 
					"Invalid username or password.  Signon failed.");
		}
		
		else {
			MemberSession memberSession = new MemberSession(m);
			
			model.addAttribute("member", memberSession.getMember());
			model.addAttribute("nickname", memberSession.getMember().getNickname());
			model.addAttribute("id", memberSession.getMember().getId());
			
			session.setAttribute("ms", memberSession);
			System.out.println(memberSession.getMember().getId());
			
			return new ModelAndView("/member/memberInfo");
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
		model.addAttribute("member", new Member());
		return "member/loginForm";
	}

	//회원가입, 수정 폼
	@GetMapping({"/member/joinForm.do", "/member/modifyMemberForm.do"})
	public String joinForm() {
		return "/member/joinForm";
	}

	
	//회원가입, 수정
	@PostMapping({"/member/join.do", "/member/modifyMember.do"})
	public String join(HttpServletRequest request, HttpSession session,
			@ModelAttribute("memberForm") MemberForm memberForm, BindingResult result, Model model) throws Exception {
		
		//new MemberFormValidator().validate(memberForm, result);
		
		if (result.hasErrors()) return "member/joinForm";
		try {
			if (memberForm.isNewMember()) {
				memberService.insertMember(memberForm.getMember());
			}
			else {
				memberService.updateMember(memberForm.getMember());
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("member.id", "USER_ID_ALREADY_EXISTS",
					"Member ID already exists: choose a different ID.");
			return "member/joinForm"; 
		}
		
		MemberSession memberSession = new MemberSession(memberService.getMember(memberForm.getMember().getId()));
		session.setAttribute("memberSession", memberSession);
		return "member/memberInfo"; 
	}

	@RequestMapping("member/delete.do")
	public String deleteMember(HttpSession session) throws Exception {
		MemberSession memberSession = (MemberSession) session.getAttribute("memberSession");
		memberService.deleteMember(memberSession.getMember().getId());
		session.removeAttribute("memberSession");
		session.invalidate();
		return "main";
	}
	
	//회원정보
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
}
