package com.luckyGirls.ForYourNutrition.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.luckyGirls.ForYourNutrition.controller.SearchIdForm;

public class SearchIdFormValidator {
	public boolean supports(Class<?> clazz) {
		return SearchIdForm.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
	
	}
}
