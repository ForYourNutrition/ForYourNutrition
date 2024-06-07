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

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Question;
import com.luckyGirls.ForYourNutrition.domain.QuestionComment;
import com.luckyGirls.ForYourNutrition.domain.Survey;
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
            //Member member = ms.getMember();
            Question question = questionService.getQuestion(q.getQuestion_id());
            
            if (question != null) {
            	QuestionForm questionForm = new QuestionForm();
            	//questionForm.setQuestion_id(question.getQuestion_id());
            	questionForm.setTitle(question.getTitle());
            	questionForm.setContent(question.getContent());
                return questionForm;
            }
        }
        return new QuestionForm();
    }

	

	//문의글 폼 띄우기
	@GetMapping("question/createQuestion")
	public String createForm(Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
	        return "redirect:/login"; // 세션이 만료되었거나 없는 경우 로그인 페이지로 리다이렉트
	    }
		Member member = ms.getMember();		
		String memberName = member.getName();
		
		System.out.println(memberName);
		model.addAttribute("memberName", memberName);
		//model.addAttribute("questionForm", new QuestionForm());
		return "question/questionForm";
	}
	
	//문의글 작성
	@PostMapping("/question/saveQuestion")
	public String saveQuestion(HttpServletRequest request, HttpSession session,
			@ModelAttribute("questionForm") QuestionForm questionForm, BindingResult result, Model model) throws Exception {
		
		try {
			MemberSession ms = (MemberSession) session.getAttribute("ms");
 
	        Member member = ms.getMember();	
			String memberName = member.getName();

	        // 현재 시간 받아오기
	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedNow = now.format(formatter);
	        
	        System.out.println(formattedNow);
	        
	        Question question = new Question();
	        question.setMember(member);

	        question.setTitle(questionForm.getTitle());
	        System.out.println("1 + " + question.getTitle());
	        
	        question.setContent(questionForm.getContent());
	        System.out.println("2 + " + question.getContent());
	        
	        question.setQdate(formattedNow);
	        System.out.println("3 + " + question.getQdate());

	        questionService.insertQuestion(question);
	        System.out.println("4 Question ID after insert: " + question.getQuestion_id());
	        questionForm.setQuestion_id(question.getQuestion_id());
	        System.out.println("5 + " + questionForm.getQuestion_id());
	        
	        
	        model.addAttribute("memberName", memberName);
	        model.addAttribute("questionForm", questionForm);
	        model.addAttribute("question", question);
	        return "question/viewQuestion";
		} catch (NullPointerException ex) {
            model.addAttribute("questionForm", new QuestionForm());
            return "question/questionForm";
        }
	}
	
	// 수정 폼을 보여주는 메소드
	@GetMapping("/question/updateQuestionForm")
	public String showUpdateQuestionForm(@RequestParam("question_id") int question_id, Model model,  HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		 
        Member member = ms.getMember();	
        System.out.println("11");
	    Question question = questionService.getQuestion(question_id);
	    System.out.println("22 + " + question.getQuestion_id());
	    
		question.setMember(member);
		
	    QuestionForm questionForm = new QuestionForm();
	    questionForm.setQuestion_id(question.getQuestion_id());
	    questionForm.setTitle(question.getTitle());
	    questionForm.setContent(question.getContent());

	    model.addAttribute("questionForm", questionForm);
	    model.addAttribute("question", question);
	    
	    return "question/updateQuestion";
	}
	
	//문의글 수정
	@PostMapping("/question/updateQuestion")
	public String updateQuestion(HttpServletRequest request, HttpSession session,
	        @ModelAttribute("questionForm") QuestionForm questionForm, BindingResult result, Model model) throws Exception {
	    
	    try {
	    	System.out.println("1111 생존신고");
	        MemberSession ms = (MemberSession) session.getAttribute("ms");
	        Member member = ms.getMember();    
	        
	        Question question = questionService.getQuestion(questionForm.getQuestion_id());
	        if (question == null) {
	            throw new Exception("Question not found");
	        }
	        
	        System.out.println("7 + " + question.getQuestion_id());

	        // 기존 질문 업데이트
	        question.setTitle(questionForm.getTitle());
	        question.setContent(questionForm.getContent());
        
	        System.out.println("8 이건 되겠지" + question.getQuestion_id());
	        
	        questionService.updateQuestion(question);
	        
	        model.addAttribute("memberName", member.getName());
	        model.addAttribute("question", question);
	        
	        return "question/viewQuestion";
	    } catch (NullPointerException ex) {
	        model.addAttribute("questionForm", new QuestionForm());
	        return "question/questionForm";
	    } catch (Exception ex) {
	        model.addAttribute("errorMessage", ex.getMessage());
	        return "error";
	    }
	}
	
	//문의글 삭제
	@PostMapping("/question/deleteQuestion")
	public String deleteQuestion(@ModelAttribute("questionForm") QuestionForm questionForm, HttpSession session, Model model) {
	    try {
	        MemberSession ms = (MemberSession) session.getAttribute("ms");
	        if (ms == null) {
	            return "redirect:/login"; // 세션이 만료되었거나 없는 경우 로그인 페이지로 리다이렉트
	        }

	        int questionId = questionForm.getQuestion_id();
	        System.out.println("50 + " + questionId);
	        questionService.deleteQuestion(questionId);
	        
	        System.out.println("51 DONE");
	        
	        //return "redirect:/main.do"; //일단 main으로
	        return "redirect:/question/questionList"; // 질문 목록 페이지로 리다이렉트
	    } catch (Exception ex) {
	        model.addAttribute("errorMessage", ex.getMessage());
	        return "error";
	    }
	}

	// 모든 질문을 보여주는 메소드
    @GetMapping("/question/questionList")
    public String listQuestions(Model model, HttpSession session) {
    	//Question question = new Question();
    	List<Question> questions = questionService.getAllQuestionList();
    	System.out.println("66");
    	model.addAttribute("questions", questions);
    	return "question/questionList";
    }
    
	//게시글 하나 view
	@GetMapping("/question/viewQuestion")
	public String viewQuestion(@RequestParam("question_id") int question_id, Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
        if (ms == null) {
            return "redirect:/login"; // 세션이 만료되었거나 없는 경우 로그인 페이지로 리다이렉트
        }
        System.out.println("70");
		Question question = questionService.getQuestion(question_id);
		
		System.out.println("71 + " + question.getQuestion_id());
		
		
		List<QuestionComment> comments = questionCommentService.getQuestionCommentListForQuestion(question_id);
		
		QuestionForm questionForm = new QuestionForm();
		questionForm.setQuestion_id(question.getQuestion_id());
		questionForm.setTitle(question.getTitle());
		questionForm.setContent(question.getContent());
		
		 model.addAttribute("questionForm", questionForm);
		 model.addAttribute("question", question);
		 model.addAttribute("comments", comments);
		 model.addAttribute("newComment", new QuestionComment());
		 return "question/viewQuestion";
	}
	
}
