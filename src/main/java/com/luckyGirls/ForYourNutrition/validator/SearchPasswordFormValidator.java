package com.luckyGirls.ForYourNutrition.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.luckyGirls.ForYourNutrition.controller.SearchPasswordForm;

public class SearchPasswordFormValidator {
	public boolean supports(Class<?> clazz) {
		return SearchPasswordForm.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
	
		SearchPasswordForm regReq = (SearchPasswordForm) target;
		
		String emailRegax = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";
		if (!regReq.getEmail().equals("") && !regReq.getEmail().matches(emailRegax)) {
			errors.rejectValue("email", "typeMismatch");
		}
	}
}
