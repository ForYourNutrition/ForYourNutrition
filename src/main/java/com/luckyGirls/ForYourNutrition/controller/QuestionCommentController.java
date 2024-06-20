package com.luckyGirls.ForYourNutrition.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class QuestionCommentController {
	@Autowired
	QuestionCommentService questionCommentService;

	@Autowired
	QuestionService questionService;

	// 댓글 작성
	@PostMapping("/questionComment/addComment")
	public String addComment(@ModelAttribute("newComment") QuestionComment comment, @RequestParam int question_id,
			HttpSession session) {

		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms != null) {

			// 현재 시간 받아오기
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String formattedNow = now.format(formatter);

			Question question = questionService.getQuestion(question_id);
			comment.setQuestion(question);
			comment.setMember(ms.getMember());
			comment.setQcdate(formattedNow);
			questionCommentService.addCommentToQuestion(comment.getQuestion().getQuestion_id(), comment.getContent(),
					comment.getMember(), comment.getQcdate());
		}
		return "redirect:/question/viewQuestion?question_id=" + question_id;
	}

	@PostMapping("/questionComment/deleteComment")
	public String deleteComment(HttpServletRequest request, HttpSession session, @RequestParam("qc_id") int qc_id,
			@RequestParam("question_id") int question_id, Model model) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/member/loginForm";
		}
		Member member = ms.getMember();
		QuestionComment comment = questionCommentService.getQuestionComment(qc_id);

		if (comment == null) {
			System.out.println("Comment not found for qc_id: " + qc_id);
		} else {
			System.out.println("Found comment: " + comment.getContent());
			System.out.println("Comment's member_id: " + comment.getMember().getMember_id());
			System.out.println("Current member_id: " + member.getMember_id());
		}

		// 댓글 작성자와 로그인 한 member가 동일해야 수정 가능함.
		if (comment != null && comment.getMember().getMember_id() == member.getMember_id()) {
			System.out.println("Attempting to delete comment with qc_id: " + qc_id);
			questionCommentService.deleteQuestionComment(comment);
			System.out.println("Deleted comment with qc_id: " + qc_id);

			QuestionComment deletedComment = questionCommentService.getQuestionComment(qc_id);
			if (deletedComment == null) {
				System.out.println("Comment deletion confirmed.");
			} else {
				System.out.println("Comment deletion failed, comment still exists.");
			}

			return "redirect:/question/viewQuestion?question_id=" + question_id; // 댓글 삭제 후 해당 질문 페이지로 리다이렉트
		}

		return "redirect:/question/viewQuestion?question_id=" + question_id;
	}

	// 댓글 리스트
	@GetMapping("/questionComment/commentList")
	public String listComments(@RequestParam("question_id") int question_id, Model model) {
		List<QuestionComment> comments = questionCommentService.getQuestionCommentListForQuestion(question_id);
		model.addAttribute("comments", comments);

		return "question/viewQuestion";
	}

}
