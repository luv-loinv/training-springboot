package com.spring.insurance.service;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.insurance.entity.TblInsurance;
import com.spring.insurance.repository.InsuranceRepository;
import com.spring.user.form.UserForm;

@Service
public class InsuranceService {
	@Autowired
	InsuranceRepository insuranceRepo;

	public InsuranceService(InsuranceRepository insuranceRepo) {
		super();
		this.insuranceRepo = insuranceRepo;
	}
	
	public TblInsurance saveInsurance(UserForm userForm) throws ParseException {
		TblInsurance insurance = new TblInsurance();
		
		insurance.setInsuranceNumber(userForm.getInsuranceNumber());
    	String startDateString = userForm.getInsuranceStartDate();
    	String endDateString = userForm.getInsuranceEndDate();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Date startDate;
    	Date endDate;
    	startDate = df.parse(startDateString);
    	endDate = df.parse(endDateString);
    	insurance.setInsuranceStartDate(startDate);
    	insurance.setInsuranceEndDate(endDate);
    	insurance.setPlaceOfRegister(userForm.getPlaceOfRegister());
    	
    	insuranceRepo.saveAndFlush(insurance);
		return insurance;
	}
}
