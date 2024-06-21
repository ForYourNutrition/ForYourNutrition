package com.luckyGirls.ForYourNutrition.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Review;
import com.luckyGirls.ForYourNutrition.domain.ReviewComment;
import com.luckyGirls.ForYourNutrition.service.ItemService;
import com.luckyGirls.ForYourNutrition.service.MemberService;
import com.luckyGirls.ForYourNutrition.service.ReviewCommentService;
import com.luckyGirls.ForYourNutrition.service.ReviewService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewCommentService reviewCommentService;

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private MemberService memberService;

	@ModelAttribute("reviewForm")
	public ReviewForm formBacking(HttpServletRequest request, HttpSession session) throws Exception {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		Review r = new Review();
		if (ms != null) {
			Review review = reviewService.getReview(r.getReview_id());

			if (review != null) {
				ReviewForm reviewForm = new ReviewForm();
				reviewForm.setTitle(review.getTitle());
				reviewForm.setContent(review.getContent());
				reviewForm.setRating(review.getRating());
				reviewForm.setItem_id(review.getItem().getItem_id());
				return reviewForm;
			}
		}
		return new ReviewForm();
	}

	// 리뷰 폼 띄우기
	@GetMapping("/review/createReview")
	public String createForm(@RequestParam("item_id") int item_id, Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/member/loginForm";
		}
		Member member = ms.getMember();
		
		String memNickName = member.getNickname();
		model.addAttribute("memNickName", memNickName);

		model.addAttribute("item_id", item_id);

		return "review/reviewForm";
	}

	// 리뷰 작성
	@PostMapping("/review/saveReview")
	public String saveReview(HttpServletRequest request, HttpSession session,
			@ModelAttribute("reviewForm") ReviewForm reviewForm, BindingResult result, Model model) throws Exception {

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

			Review review = new Review();
			review.setMember(member);
			review.setTitle(reviewForm.getTitle());
			review.setContent(reviewForm.getContent());
			review.setRating(reviewForm.getRating());
			review.setRdate(formattedNow);

			int item_id = reviewForm.getItem_id();

			Item item = itemService.getItemById(item_id);
			review.setItem(item);

			reviewForm.setReview_id(review.getReview_id());

			reviewService.insertReview(review);

			// 포인트 10점 추가
			reviewService.addPoint(member, 10);

			System.out.println("review의 item : " + review.getItem().getName());
			System.out.println("review_id : " + review.getReview_id());
			System.out.println("Saving Review: " + review);

			String memNickName = review.getMember().getNickname();
			
			model.addAttribute("memNickName", memNickName);
			
			model.addAttribute("reviewForm", reviewForm);
			model.addAttribute("review", review);

			return "review/viewReview";

		} catch (NullPointerException ex) {
			model.addAttribute("reviewForm", new ReviewForm());
			return "review/reviewForm";
		}
	}

	// 리뷰 수정 폼을 보여주는 메소드
	@GetMapping("/review/updateReviewForm")
	public String showUpdateReviewForm(@RequestParam("review_id") int review_id, @RequestParam("item_id") int item_id,
			Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/member/loginForm"; // 로그인 페이지로 리다이렉트
		}

		Member member = ms.getMember();
		Review review = reviewService.getReview(review_id);

		if (review == null) {
			return "redirect:/review/reviewList"; // 리뷰가 없으면 리뷰 목록 페이지로 리다이렉트
		}

		Item item = itemService.getItemById(item_id); // 기존의 아이템 가져오기
		review.setItem(item);

		// 리뷰 작성자와 로그인 한 member가 동일해야 수정 가능함.
		if (review != null && review.getMember().getMember_id() == member.getMember_id()) {
			ReviewForm reviewForm = new ReviewForm();
			reviewForm.setReview_id(review.getReview_id());
			reviewForm.setTitle(review.getTitle());
			reviewForm.setContent(review.getContent());
			reviewForm.setRating(review.getRating());
			reviewForm.setItem_id(review.getItem().getItem_id());

			String memNickName = review.getMember().getNickname();
			
			model.addAttribute("memNickName", memNickName);
			
			model.addAttribute("reviewForm", reviewForm);
			return "review/updateReview";
		}
		return "redirect:/review/reviewList";
	}

	// 리뷰 수정
	@PostMapping("/review/updateReview")
	public String updateReview(HttpSession session, @ModelAttribute("reviewForm") ReviewForm reviewForm,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "review/updateReview";
		}

		try {
			MemberSession ms = (MemberSession) session.getAttribute("ms");
			if (ms == null) {
				return "redirect:/member/loginForm"; // 로그인 페이지로 리다이렉트
			}

			Member member = ms.getMember();
			Review review = reviewService.getReview(reviewForm.getReview_id());

			if (review == null || review.getMember().getMember_id() != member.getMember_id()) {
				throw new Exception("Unauthorized access");
			}

			review.setTitle(reviewForm.getTitle());
			review.setContent(reviewForm.getContent());
			review.setRating(reviewForm.getRating());

			Item item = itemService.getItemById(reviewForm.getItem_id());
			review.setItem(item);

			reviewService.updateReview(review);

			String memNickName = review.getMember().getNickname();
			
			model.addAttribute("memNickName", memNickName);			
			model.addAttribute("review", review);

			return "review/viewReview";
		} catch (Exception ex) {
			model.addAttribute("errorMessage", ex.getMessage());
			return "error";
		}
	}

	// 리뷰 삭제
	@PostMapping("/review/deleteReview")
	public String deleteReview(@ModelAttribute("reviewForm") ReviewForm reviewForm, HttpSession session, Model model) {
		try {
			MemberSession ms = (MemberSession) session.getAttribute("ms");
			if (ms == null) {
				return "redirect:/member/loginForm";
			}
			Member member = ms.getMember();
			int review_id = reviewForm.getReview_id();
			int item_id = reviewForm.getItem_id();

			// Review 객체 가져오기
			Review r = reviewService.getReview(review_id);

			if (r != null && r.getMember().getMember_id() == member.getMember_id()) {
				reviewService.deleteReview(r);
				
				// 포인트 차감 로직 추가
                int currentPoints = member.getPoint();
                int newPoints = Math.max(currentPoints - 10, 0);
                member.setPoint(newPoints);
                memberService.updateMember(member);
				
				// item_id 값을 포함한 item 페이지로 리다이렉트
				return "redirect:/item/viewItem?item_id=" + item_id;
			}
			// 권한이 없거나 리뷰를 찾지 못한 경우 리뷰 목록 페이지로 리다이렉트
			return "redirect:/review/reviewList";
		} catch (Exception ex) {
			model.addAttribute("errorMessage", ex.getMessage());
			return "error";
		}
	}

	// 리뷰 하나 view
	@GetMapping("/review/viewReview")
	public String viewReview(@RequestParam("review_id") int review_id, Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		if (ms == null) {
			return "redirect:/member/loginForm";
		}
		Review review = reviewService.getReview(review_id);

		List<ReviewComment> comments = reviewCommentService.getReviewCommentListForReview(review_id);

		ReviewForm reviewForm = new ReviewForm();
		reviewForm.setReview_id(review.getReview_id());
		reviewForm.setTitle(review.getTitle());
		reviewForm.setContent(review.getContent());
		reviewForm.setRating(review.getRating());
		reviewForm.setItem_id(review.getItem().getItem_id()); // item_id 추가

		Member member = ms.getMember();

		model.addAttribute("reviewForm", reviewForm);
		model.addAttribute("review", review);
		model.addAttribute("comments", comments);
		
		String memNickName = review.getMember().getNickname();
		
		
		model.addAttribute("memNickName", memNickName);
		model.addAttribute("member", member);
		model.addAttribute("newComment", new ReviewComment());

		return "review/viewReview";
	}

	// 리뷰 list view
	@GetMapping("/review/reviewList")
    public String listReviews(@RequestParam("item_id") int item_id,
                              @RequestParam(value = "page", defaultValue = "1") int page,
                              Model model, HttpSession session) {
        Item item = itemService.getItemById(item_id);

        int pageSize = 10; // 한 페이지에 표시할 리뷰 수
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Review> reviewPage = reviewService.getReviewListForItem(item_id, pageable);

        int totalPages = reviewPage.getTotalPages();
        int startPage = Math.max(1, page - 2);
        int endPage = Math.min(totalPages, page + 2);

        model.addAttribute("item", item);
        model.addAttribute("reviews", reviewPage.getContent());
        model.addAttribute("nowPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "review/reviewList";
	}

	//InMyPage
	@GetMapping("/review/myReviewList")
	public String listMyReviews(Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");

		if (ms == null) {
			return "redirect:/member/loginForm";
		}

		int memberId = ms.getMember().getMember_id();
		List<Review> myReviews = reviewService.getReviewListForMember(memberId);
		
		Member member = ms.getMember();
		String memNickName = member.getNickname();
		
		model.addAttribute("memNickName", memNickName);
		model.addAttribute("myReviews", myReviews);
		return "review/myReviewList";
	}
}
