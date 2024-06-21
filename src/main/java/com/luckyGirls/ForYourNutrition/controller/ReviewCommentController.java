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

import com.luckyGirls.ForYourNutrition.domain.Review;
import com.luckyGirls.ForYourNutrition.domain.ReviewComment;
import com.luckyGirls.ForYourNutrition.service.ReviewCommentService;
import com.luckyGirls.ForYourNutrition.service.ReviewService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewCommentController {

	@Autowired
	ReviewCommentService reviewCommentService;

	@Autowired
	ReviewService reviewService;

	// 댓글 작성
	@PostMapping("/reviewComment/addComment")
	public String addComment(@ModelAttribute("newComment") ReviewComment comment, @RequestParam int review_id,
			HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms != null) {

			// 현재 시간 받아오기
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String formattedNow = now.format(formatter);

			Review review = reviewService.getReview(review_id);
			comment.setReview(review);
			comment.setMember(ms.getMember());
			comment.setRcdate(formattedNow);

			reviewCommentService.insertReviewComment(comment);
		}
		return "redirect:/review/viewReview?review_id=" + review_id;
	}

	@PostMapping("/reviewComment/deleteComment")
	public String deleteComment(HttpServletRequest request, HttpSession session, @RequestParam("rc_id") int rc_id,
			@RequestParam("review_id") int review_id, Model model) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/member/loginForm";
		}

		ReviewComment comment = reviewCommentService.getReviewComment(rc_id);
		reviewCommentService.deleteReviewComment(comment);

		return "redirect:/review/viewReview?review_id=" + review_id;
	}

	// 댓글 리스트
	@GetMapping("/reviewComment/commentList")
	public String listComments(@RequestParam("review_id") int review_id, Model model) {
		List<ReviewComment> comments = reviewCommentService.getReviewCommentListForReview(review_id);
		model.addAttribute("comments", comments);

		return "review/commentList";
	}
}
