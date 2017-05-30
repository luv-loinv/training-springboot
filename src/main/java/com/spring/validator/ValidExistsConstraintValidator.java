package com.spring.validator;

import java.lang.annotation.ElementType;

import javax.lang.model.element.Element;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidExistsConstraintValidator implements ConstraintValidator<ValidExists, String> {
	
	@Override
	public void initialize(ValidExists String) { }

	@Override
	public boolean isValid(String value, ConstraintValidatorContext cxt) {
		// lam sao lay duoc column name , table name
		
		
		//code check trong DB
		return true;
	}

}
