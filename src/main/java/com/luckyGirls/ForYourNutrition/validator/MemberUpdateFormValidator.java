package com.luckyGirls.ForYourNutrition.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.luckyGirls.ForYourNutrition.controller.MemberForm;
import com.luckyGirls.ForYourNutrition.domain.Member;

@Component
public class MemberUpdateFormValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return MemberForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberForm regReq = (MemberForm) target;

		// 필수 입력 항목
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.nickname", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.birth", "required");
		
		Member member = regReq.getMember();
		
		String emailRegax = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";
		if (!member.getEmail().equals("") && !member.getEmail().matches(emailRegax)) {
			errors.rejectValue("member.email", "typeMismatch");
		}
		
		if (member.getPassword() != null && member.getPassword().length() > 0) {
			if (!member.getPassword().equals(regReq.getRepeatedPassword())) {
				errors.rejectValue("repeatedPassword", "invalidPassword");
			}
		}

		String phone = regReq.getMember().getPhone_number();
		if (!phone.equals("") && !phone.matches("^[0][1]\\d{1}-\\d{3,4}-\\d{4}$")) {
			errors.rejectValue("member.phone_number", "typeMismatch");
		}

	}
}
