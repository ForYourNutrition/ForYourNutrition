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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Question;
import com.luckyGirls.ForYourNutrition.domain.QuestionComment;
import com.luckyGirls.ForYourNutrition.service.QuestionCommentService;
import com.luckyGirls.ForYourNutrition.service.QuestionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	QuestionCommentService questionCommentService;

	@ModelAttribute("questionForm")
	public QuestionForm formBacking(HttpServletRequest request, HttpSession session) throws Exception {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		Question q = new Question();
		if (ms != null) {
			// Member member = ms.getMember();
			Question question = questionService.getQuestion(q.getQuestion_id());

			if (question != null) {
				QuestionForm questionForm = new QuestionForm();

				questionForm.setTitle(question.getTitle());
				questionForm.setContent(question.getContent());

				return questionForm;
			}
		}
		return new QuestionForm();
	}

	// 문의글 폼 띄우기
	@GetMapping("question/createQuestion")
	public String createForm(Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/member/loginForm";
		}
		Member member = ms.getMember();
		//String memberName = member.getName();
		String memNickName = member.getNickname();
		System.out.println(memNickName);
		model.addAttribute("memNickName", memNickName);
		return "question/questionForm";
	}

	// 문의글 작성
	@PostMapping("/question/saveQuestion")
	public String saveQuestion(HttpServletRequest request, HttpSession session,
			@ModelAttribute("questionForm") QuestionForm questionForm, BindingResult result, Model model)
			throws Exception {

		try {
			MemberSession ms = (MemberSession) session.getAttribute("ms");

			if (ms == null) {
				return "redirect:/member/loginForm";
			}
			Member member = ms.getMember();

			// 현재 시간 받아오기
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String formattedNow = now.format(formatter);

			Question question = new Question();
			question.setMember(member);
			question.setTitle(questionForm.getTitle());
			question.setContent(questionForm.getContent());
			question.setQdate(formattedNow);

			questionService.insertQuestion(question);

			questionForm.setQuestion_id(question.getQuestion_id());
			String memNickName = question.getMember().getNickname();
			
			model.addAttribute("memNickName", memNickName);
			model.addAttribute("questionForm", questionForm);
			model.addAttribute("question", question);
			return "question/viewQuestion";

		} catch (NullPointerException ex) {
			model.addAttribute("questionForm", new QuestionForm());
			return "question/questionForm";
		}
	}

	// 문의글 수정 폼을 보여주는 메소드
	@GetMapping("/question/updateQuestionForm")
	public String showUpdateQuestionForm(@RequestParam("question_id") int question_id, Model model,
			HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");

		Member member = ms.getMember();

		Question question = questionService.getQuestion(question_id);

		// 게시글 작성자와 로그인 한 member가 동일해야 수정 가능함.
		if (question != null && question.getMember().getMember_id() == member.getMember_id()) {
			QuestionForm questionForm = new QuestionForm();
			questionForm.setQuestion_id(question.getQuestion_id());
			questionForm.setTitle(question.getTitle());
			questionForm.setContent(question.getContent());

			String memNickName = question.getMember().getNickname();
			model.addAttribute("memNickName", memNickName);
			
			model.addAttribute("questionForm", questionForm);
			model.addAttribute("question", question);

			return "question/updateQuestion";
		}
		return "redirect:/question/questionList";
	}

	// 문의글 수정
	@PostMapping("/question/updateQuestion")
	public String updateQuestion(HttpServletRequest request, HttpSession session,
			@ModelAttribute("questionForm") QuestionForm questionForm, BindingResult result, Model model)
			throws Exception {

		try {
			MemberSession ms = (MemberSession) session.getAttribute("ms");
			Member member = ms.getMember();

			Question question = questionService.getQuestion(questionForm.getQuestion_id());
			if (question == null) {
				throw new Exception("Question not found");
			}

			question.setTitle(questionForm.getTitle());
			question.setContent(questionForm.getContent());

			questionService.updateQuestion(question);
			
			String memNickName = member.getNickname();
			model.addAttribute("memNickName", memNickName);
			
			model.addAttribute("question", question);

			List<QuestionComment> comments = questionCommentService
					.getQuestionCommentListForQuestion(questionForm.getQuestion_id());
			model.addAttribute("comments", comments);

			return "question/viewQuestion";
		} catch (NullPointerException ex) {
			model.addAttribute("questionForm", new QuestionForm());
			return "question/questionForm";
		} catch (Exception ex) {
			model.addAttribute("errorMessage", ex.getMessage());
			return "error";
		}
	}

	// 문의글 삭제
	@PostMapping("/question/deleteQuestion")
	public String deleteQuestion(@ModelAttribute("questionForm") QuestionForm questionForm, HttpSession session,
			Model model) {
		try {
			MemberSession ms = (MemberSession) session.getAttribute("ms");
			if (ms == null) {
				return "redirect:/member/loginForm";
			}
			Member member = ms.getMember();
			int question_id = questionForm.getQuestion_id();

			// 문의글 객체 가져오기
			Question q = questionService.getQuestion(question_id);

			if (q != null && q.getMember().getMember_id() == member.getMember_id()) {
				questionService.deleteQuestion(question_id);
				return "redirect:/question/questionList";
			}
			return "redirect:/question/questionList"; // 질문 목록 페이지로 리다이렉트
		} catch (Exception ex) {
			model.addAttribute("errorMessage", ex.getMessage());
			return "error";
		}
	}

	// 문의글 list view 처리
	@GetMapping("/question/questionList")
	public String listQuestions(@RequestParam(value = "sort", required = false, defaultValue = "latest") String sort,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "keyword", required = false) String keyword, Model model) {

		List<Question> questions = questionService.getQuestionList(sort, page, keyword);
		int totalPages = questionService.getTotalPages(keyword);

		model.addAttribute("questions", questions);
		model.addAttribute("sort", sort);
		model.addAttribute("nowPage", page + 1); // 현재 페이지는 1부터 시작하도록 설정
		model.addAttribute("totalPages", totalPages);

		int startPage = Math.max(1, page - 2 + 1); // 페이지 번호는 1부터 시작
		int endPage = Math.min(totalPages, page + 3 + 1); // 페이지 번호는 1부터 시작

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "question/questionList";
	}

	// 문의글 하나 view
	@GetMapping("/question/viewQuestion")
	public String viewQuestion(@RequestParam("question_id") int question_id, Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/member/loginForm";
		}
		Question question = questionService.getQuestion(question_id);

		List<QuestionComment> comments = questionCommentService.getQuestionCommentListForQuestion(question_id);

		QuestionForm questionForm = new QuestionForm();
		questionForm.setQuestion_id(question.getQuestion_id());
		questionForm.setTitle(question.getTitle());
		questionForm.setContent(question.getContent());

		Member member = ms.getMember();

		model.addAttribute("questionForm", questionForm);
		model.addAttribute("question", question);
		model.addAttribute("comments", comments);

		String memNickName = question.getMember().getNickname();
		model.addAttribute("memNickName", memNickName);
		
		model.addAttribute("member", member);
		model.addAttribute("newComment", new QuestionComment());

		return "question/viewQuestion";
	}

	//InMyPage
	@GetMapping("/question/myQuestionList")
	public String listMyQuestions(Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");

		if (ms == null) {
			return "redirect:/member/loginForm";
		}

		int memberId = ms.getMember().getMember_id();
		List<Question> myQuestions = questionService.getQuestionListForMember(memberId);
		
		Member member = ms.getMember();
		String memNickName = member.getNickname();
		model.addAttribute("memNickName", memNickName);
		
		model.addAttribute("myQuestions", myQuestions);
		return "question/myQuestionList";
	}
}
