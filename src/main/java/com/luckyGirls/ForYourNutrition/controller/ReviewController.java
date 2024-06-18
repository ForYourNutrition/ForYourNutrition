package com.luckyGirls.ForYourNutrition.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Review;
import com.luckyGirls.ForYourNutrition.domain.ReviewComment;
import com.luckyGirls.ForYourNutrition.service.ItemService;
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
	
	@ModelAttribute("reviewForm")
	public ReviewForm formBacking(HttpServletRequest request, HttpSession session) throws Exception {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
		Review r = new Review();
		if(ms != null) {
			Review review = reviewService.getReview(r.getReview_id());
			
			if(review != null) {
				ReviewForm reviewForm = new ReviewForm();
				reviewForm.setTitle(review.getTitle());
				reviewForm.setContent(review.getContent());
				//reviewForm.setImg(review.getImg());
				reviewForm.setRating(review.getRating());
				reviewForm.setItem_id(review.getItem().getItem_id()); // item_id 추가
				return reviewForm;
			}
		}
		return new ReviewForm();
	}
	
	//리뷰 폼 띄우기
	@GetMapping("/review/createReview")
	public String createForm(@RequestParam("item_id") int item_id, Model model, HttpSession session) {
		MemberSession ms = (MemberSession) session.getAttribute("ms");
        if (ms == null) {
        	return "redirect:/member/loginForm";
        }
        Member member = ms.getMember();		
        String memberName = member.getName();
		
		System.out.println(memberName);
		model.addAttribute("memberName", memberName);
		
		model.addAttribute("item_id", item_id);
		
		return "review/reviewForm";
	}
	
	//리뷰 작성
	@PostMapping("/review/saveReview")
	public String saveReview(HttpServletRequest request, HttpSession session,
	        @ModelAttribute("reviewForm") ReviewForm reviewForm, BindingResult result, Model model/*,
	        @RequestParam("img") MultipartFile imgFile*/) throws Exception {

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

	        // 파일 업로드 처리
	        /*if (!imgFile.isEmpty()) {
	            String fileName = imgFile.getOriginalFilename();
	            String uploadDir = "src/main/resources/static/images"; // 파일을 저장할 디렉터리 경로 설정

	            // 디렉터리가 존재하지 않으면 생성
	            Path uploadPath = Paths.get(uploadDir);
	            if (!Files.exists(uploadPath)) {
	                Files.createDirectories(uploadPath);
	            }

	            Path filePath = uploadPath.resolve(fileName);
	            imgFile.transferTo(filePath.toFile());

	            // 저장된 파일 경로를 리뷰에 설정
	            System.out.println("사진 경로 : ");
	            reviewForm.setImg("/images/" + fileName); // reviewForm.img에 정적 리소스 경로 설정
	            review.setImg(reviewForm.getImg()); // review 객체에도 저장
	            System.out.println("사진 경로 : " + review.getImg().toString());
	        }*/
   
	        review.setRating(reviewForm.getRating());
	        review.setRdate(formattedNow);
	        
	        // item_id 값을 reviewForm에서 받아와서 설정
	        //int item_id = Integer.parseInt(request.getParameter("item_id"));
	        
	        int item_id = reviewForm.getItem_id();
	        
	        System.out.println("item item_id : " + item_id);
	        
	        Item item = itemService.getItemById(item_id);
	        review.setItem(item);

	        reviewForm.setReview_id(review.getReview_id());
	        
	        reviewService.insertReview(review);
	        
	        System.out.println("review의 item : " + review.getItem().getName());
	        System.out.println("review_id : " + review.getReview_id());
	        System.out.println("Saving Review: " + review);
	        
	        model.addAttribute("memberName", member.getName());
	        model.addAttribute("reviewForm", reviewForm);
	        model.addAttribute("review", review);
	        
	        return "review/viewReview";
	        
	    } catch (NullPointerException ex) {
	        model.addAttribute("reviewForm", new ReviewForm());
	        return "review/reviewForm";
	    }
	}
	//리뷰 수정 폼을 보여주는 메소드
	@GetMapping("/review/updateReviewForm")
	public String showUpdateReviewForm(@RequestParam("review_id") int review_id, 
										@RequestParam("item_id") int item_id,
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

	    //리뷰 작성자와 로그인 한 member가 동일해야 수정 가능함.
	    if (review != null && review.getMember().getMember_id() == member.getMember_id()) {        
	        ReviewForm reviewForm = new ReviewForm();
	        reviewForm.setReview_id(review.getReview_id());
	        reviewForm.setTitle(review.getTitle());
	        reviewForm.setContent(review.getContent());
	       // reviewForm.setImg(review.getImg());
	        reviewForm.setRating(review.getRating());
	        reviewForm.setItem_id(review.getItem().getItem_id()); // item_id 추가
	        //reviewForm.setItem_id(item_id);
	        
	        model.addAttribute("reviewForm", reviewForm);
	        model.addAttribute("memberName", member.getName());
	        
	        return "review/updateReview";
	    }
	    return "redirect:/review/reviewList";
	}

	//리뷰 수정
	@PostMapping("/review/updateReview")
	public String updateReview(HttpSession session,
	         @ModelAttribute("reviewForm") ReviewForm reviewForm, BindingResult result, Model model/*,
	         @RequestParam("img") MultipartFile imgFile*/) {

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
	        
	        // review_id를 사용하여 review의 item을 설정
	        /*Item item = review.getItem(); // review에서 item 가져오기
	        review.setItem(item);*/
	        Item item = itemService.getItemById(reviewForm.getItem_id());
            review.setItem(item);
	        
            // 파일 업로드 처리
            /*if (!imgFile.isEmpty()) {
                String fileName = imgFile.getOriginalFilename();
                String uploadDir = "src/main/resources/static/images"; // 파일을 저장할 디렉터리 경로 설정

                // 디렉터리가 존재하지 않으면 생성
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                imgFile.transferTo(filePath.toFile());

                // 저장된 파일 경로를 리뷰에 설정
                review.setImg("/images/" + fileName);
                //System.out.println("사진 경로 : " + review.getImg().toString());
            } else {
                review.setImg(reviewForm.getImg());
            }*/	       
	        
	      

	        reviewService.updateReview(review);

	        model.addAttribute("memberName", member.getName());
	        model.addAttribute("review", review);

	        return "review/viewReview";
	    } catch (Exception ex) {
	        model.addAttribute("errorMessage", ex.getMessage());
	        return "error";
	    }
	}


	//리뷰 삭제
	@PostMapping("/review/deleteReview")
	public String deleteReview(@ModelAttribute("reviewForm") ReviewForm reviewForm, HttpSession session, Model model) {
	    try {
	        MemberSession ms = (MemberSession) session.getAttribute("ms");
	        if (ms == null) {
	            return "redirect:/member/loginForm";
	        }
	        Member member = ms.getMember();
	        int review_id = reviewForm.getReview_id();
	        
	        // 질문 객체를 가져옵니다
	        Review r = reviewService.getReview(review_id);
	        
	        if (r != null && r.getMember().getMember_id() == member.getMember_id()) {
	        	reviewService.deleteReview(r);
	            //return "redirect:/review/reviewList";
	            return "redirect:/main";
	        }
	        
	        return "redirect:/review/reviewList"; // 질문 목록 페이지로 리다이렉트
	    } catch (Exception ex) {
	        model.addAttribute("errorMessage", ex.getMessage());
	        return "error";
	    }
	}	
	//리뷰 하나 view
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
		 //reviewForm.setImg(review.getImg());
		 reviewForm.setRating(review.getRating());
		 reviewForm.setItem_id(review.getItem().getItem_id()); // item_id 추가
		 
		 Member member = ms.getMember();
		
		 model.addAttribute("reviewForm", reviewForm);
		 model.addAttribute("review", review);
		 model.addAttribute("comments", comments);
		 model.addAttribute("memberName", review.getMember().getName());
		 model.addAttribute("member", member);
		 model.addAttribute("newComment", new ReviewComment());
		
		return "review/viewReview";
	}	
	//리뷰 list view
	@GetMapping("/review/reviewList")
	public String listReviews(@RequestParam("item_id") int item_id, Model model) {
	    Item item = itemService.getItemById(item_id);
	    
	    // 아이템이 null인 경우 로그 추가
	    if (item == null) {
	        System.out.println("Item not found for ID: " + item_id);
	    } else {
	        System.out.println("Item: " + item);
	    }
	    
	    List<Review> reviews = reviewService.getReviewListForItem(item_id);

	    // 리뷰가 없는 경우 로그 추가
	    if (reviews.isEmpty()) {
	        System.out.println("No reviews found for item ID: " + item_id);
	    } else {
	        System.out.println("Reviews: " + reviews);
	    }

	    model.addAttribute("item", item);
	    model.addAttribute("reviews", reviews);

	    return "review/reviewList";
	}

}
