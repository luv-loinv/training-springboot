package com.spring.company.service;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.company.entity.TblCompany;
import com.spring.company.repository.CompanyRepository;
import com.spring.user.form.UserForm;

@Service
public class CompanyService {
	@Autowired
	CompanyRepository companyRepo;

	public CompanyService(CompanyRepository companyRepo) {
		super();
		this.companyRepo = companyRepo;
	}
	
	public TblCompany saveCompany(UserForm userForm) throws ParseException {
		TblCompany company = new TblCompany();
    	if (userForm.getIsCompany().equals("false")) {
        	company.setCompanyName(userForm.getCompanyName());
        	company.setAddress(userForm.getAddress());
        	company.setEmail(userForm.getEmail());
        	company.setTelephone(userForm.getTelephone());
        	companyRepo.saveAndFlush(company);
    	} else {
    		company = companyRepo.findOne(Long.parseLong(userForm.getCompanyId()));
    	}

		return company;
	}
}
