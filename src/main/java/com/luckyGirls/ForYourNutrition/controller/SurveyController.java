package com.luckyGirls.ForYourNutrition.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Survey;
import com.luckyGirls.ForYourNutrition.service.SurveyService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @ModelAttribute("surveyForm")
    public SurveyForm formBacking(HttpServletRequest request, HttpSession session) throws Exception {
        MemberSession ms = (MemberSession) session.getAttribute("ms");
        if (ms != null) {
            Member member = ms.getMember();
            Survey survey = surveyService.getSurvey(member.getMember_id());
            if (survey != null) {
                SurveyForm surveyForm = new SurveyForm();
                surveyForm.setSmoking(survey.getSmoking());
                surveyForm.setDrinking(survey.getDrinking());
                surveyForm.setExercising(survey.getExercising());
                surveyForm.setEffect(convertEffectStringToList(survey.getEffect()));
                return surveyForm;
            }
        }
        return new SurveyForm();
    }

    // 문진표 폼
    @GetMapping("survey/surveyForm")
    public String joinForm(Model model, HttpSession session) {
    	MemberSession ms = (MemberSession) session.getAttribute("ms");
        if (ms == null) {
        	return "redirect:/member/loginForm";
        }
        return "survey/surveyForm";
    }

    // 문진표 add, update
    @PostMapping("survey/saveSurvey")
    public String join(HttpServletRequest request, HttpSession session,
                       @ModelAttribute("surveyForm") SurveyForm surveyForm, BindingResult result, Model model) throws Exception {
        try {
            MemberSession ms = (MemberSession) session.getAttribute("ms");
            Member member = ms.getMember();

            Survey survey = new Survey();
            survey.setSurvey_id(member.getMember_id());
            survey.setMember(member);
            survey.setGender(member.getGender());
            LocalDate localDate = member.getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            survey.setBirth_year(localDate.getYear());
            survey.setSmoking(surveyForm.getSmoking());
            survey.setDrinking(surveyForm.getDrinking());
            survey.setExercising(surveyForm.getExercising());
            survey.setEffect(convertEffectListToString(surveyForm.getEffect()));

            surveyService.insertSurvey(survey);
            model.addAttribute("surveyForm", surveyForm);
            return "survey/surveyInfo";
        } catch (NullPointerException ex) {
            model.addAttribute("memberForm", new MemberForm());
            return "member/loginForm";
        }
    }
    
    // 문진표 정보 조회
    @GetMapping("survey/surveyInfo")
    public String showSurveyInfo(HttpSession session, Model model) {
        MemberSession ms = (MemberSession) session.getAttribute("ms");
        if (ms != null) {
            Member member = ms.getMember();
            Survey survey = surveyService.getSurvey(member.getMember_id());
            if (survey != null) {
                SurveyForm surveyForm = new SurveyForm();
                surveyForm.setSmoking(survey.getSmoking());
                surveyForm.setDrinking(survey.getDrinking());
                surveyForm.setExercising(survey.getExercising());
                surveyForm.setEffect(convertEffectStringToList(survey.getEffect()));
                model.addAttribute("surveyForm", surveyForm);
                return "survey/surveyInfo";
            }
        }
        return "redirect:/survey/surveyForm";
    }

    private String convertEffectListToString(List<Integer> effectList) {
        if (effectList == null || effectList.isEmpty()) {
            return "";
        }
        StringBuilder effectString = new StringBuilder();
        for (int effect : effectList) {
            effectString.append(effect).append(",");
        }
        effectString.deleteCharAt(effectString.length() - 1);
        return effectString.toString();
    }

    private List<Integer> convertEffectStringToList(String effectString) {
        if (effectString == null || effectString.isEmpty()) {
            return null;
        }
        List<Integer> effectList = new ArrayList<>();
        for (String effectItem : effectString.split(",")) {
            try {
                effectList.add(Integer.parseInt(effectItem));
            } catch (NumberFormatException e) {
                // Handle invalid effect item format
            }
        }
        return effectList;
    }
}
