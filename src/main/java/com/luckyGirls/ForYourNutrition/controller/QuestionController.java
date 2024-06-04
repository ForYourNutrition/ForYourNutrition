package com.luckyGirls.ForYourNutrition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//@SessionAttributes("memberSession")
@RequestMapping("/question")
public class QuestionController {
	@Autowired
  private QuestionService questionService;

  @GetMapping("/list")
  public String listQuestions(Model model/*, @ModelAttribute("memberSession") Member member*/) {
      //List<Question> questions = questionService.getQuestionListForMember(member.getMember_id());
      model.addAttribute("questions", questionService.questionList());
      return "questionList";
  }
  
  @GetMapping("/detail/{question_id}")
  public String detailQuestion (@PathVariable("question_id") int question_id, Model model) {
  	model.addAttribute("question", questionService.detailQuestion(question_id));
  	return "viewQuestion";
  }

  @GetMapping("/create")
  public String createQuestionGet() {
  	return "questionForm";
  }
  
  @PostMapping("/create")
  public String createQuestionPost(Question question) {
  	questionService.createQuestion(question);
  	return "redirect:/question/list";
  }
  
  @GetMapping("/update/{question_id}")
  public String updateQuestionGet(@PathVariable("question_id") int question_id, Model model) {
      model.addAttribute("question");
      return "updatequestionForm";
  }

  @PostMapping("/update")
  public String updateQuestionPost(@ModelAttribute("question") Question question) {
      questionService.updateQuestion(question);
      return "redirect:/question/list";
  }

  @GetMapping("/delete/{question_id}")
  public String deleteQuestion(@PathVariable("question_id") int question_id) {
      //Question question = questionService.getQuestion(question_id);
      questionService.deleteQuestion(question_id);
      return "redirect:/question/list";
  }	
}
