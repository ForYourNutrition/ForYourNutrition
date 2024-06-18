package com.luckyGirls.ForYourNutrition.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.luckyGirls.ForYourNutrition.domain.Address;

@Component
public class AddressFormValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Address.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 필수 입력 항목
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "streetAddress", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "detailAddress", "required");
	}
}
