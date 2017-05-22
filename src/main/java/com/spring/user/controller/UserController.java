package com.spring.user.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.company.entity.TblCompany;
import com.spring.company.repository.CompanyRepository;
import com.spring.insurance.entity.TblInsurance;
import com.spring.insurance.repository.InsuranceRepository;
import com.spring.user.entity.TblUser;
import com.spring.user.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository _userRepository;
	private CompanyRepository _companyRepository;
	private InsuranceRepository _insuranceRepository;

    @RequestMapping("user/detail/{id}")
    public String detail(@PathVariable("id") Long id, Map<String, Object> model) {
    	try {
    		TblUser userDetail = _userRepository.findOne(id);
    		model.put("insurace", userDetail.getTblInsurance());
    		model.put("company", userDetail.getTblCompany());
    		model.put("user", userDetail);
    	} catch (Exception e) {}

    	return "user/detail";
    }
    
    
    @RequestMapping("user/addcompany")
    public String addCompany(Map<String, Object> model) throws ParseException {
    	TblUser user = new TblUser();
    	TblCompany company = new TblCompany();
    	TblInsurance insurance = new TblInsurance();
    	
    	company.setCompanyName("Luvina soft");
    	company.setAddress("106 hoang quoc viet");
    	company.setEmail("luvina@gmail.com");
    	company.setTelephone("03661456");
    	
    	//_companyRepository.save(company);
    	
    	insurance.setInsuranceNumber("2121321");
    	String startDateString = "2007-03-12";
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Date startDate;
    	Date endDate;
    	startDate = df.parse(startDateString);
    	endDate = df.parse(startDateString);
    	
    	insurance.setInsuranceStartDate(startDate);
    	insurance.setInsuranceEndDate(endDate);
    	insurance.setPlaceOfRegister("khong cos");
    	
    	//_insuranceRepository.save(insurance);

    	user.setUserFullName("giang khac hung");
    	user.setUserName("test");
    	user.setPassword("115145614");
    	user.setUserSexDivision("1");
    	Date birthdate;
    	birthdate = df.parse(startDateString);
    	user.setBirthdate(birthdate);
    	user.setTblCompany(company);
    	user.setTblInsurance(insurance);
    	
    	_userRepository.save(user);
    	
    	return "user/detail";
    }
    

    @RequestMapping(value = "user/add", method = RequestMethod.GET)
    public String add(Map<String, Object> model) {
    	try {
    		List<TblCompany> listCompany = _companyRepository.findAll();
    		
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    	return "user/add";
    }
}