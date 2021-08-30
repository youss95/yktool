package com.ksy.syserver.validator;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ksy.syserver.domain.User;

@Component
public class UserValidator implements Validator{

@Override
	public boolean supports(Class<?> aClass) {
		
		return User.class.equals(aClass);
	}

@Override
	public void validate(Object object, Errors errors) {
		
		User user = (User)object;
		if(user.getPassword().length() < 4) {
			errors.rejectValue("password","Length","비밀번호 4자 이상");
		}
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Match","비밀번호가 같아야 합니다.");
		}
	}
	
}
