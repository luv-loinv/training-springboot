/**
 * Copyright(C) 2016 Luvina Software Company
 * ValidateLogin.java, Aug 29, 2016, nguyenvietloi
 */
package com.spring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.insurance.repository.InsuranceRepository;
import com.spring.user.form.UserForm;

/**
 * Xử lý validate thông tin nhập vào từ màn hình
 * 
 * @author giang khac hung
 */
@Component
public class ValidateFormUser implements Validator{
	
	@Autowired
	InsuranceRepository insuranceRepository;

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		return UserForm.class.isAssignableFrom(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		UserForm userForm = (UserForm) target;
		String insuranceNumber = userForm.getInsuranceNumber();
		if (!insuranceNumber.isEmpty() && insuranceRepository.findAllByInsuranceNumber(insuranceNumber).size() > 0) {
			errors.rejectValue("insuranceNumber", null, "Mã số thẻ bảo hiểm đã được đăng ký");
		}	
		
	}
}
