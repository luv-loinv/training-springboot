package com.spring.user.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
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

	@Autowired
	private CompanyRepository _companyRepository;

	@Autowired
	private InsuranceRepository _insuranceRepository;

	private final Gson gson = new Gson();

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
    
    
    @RequestMapping(
    		value = "user/save",
    		method = RequestMethod.POST,
    		consumes="application/json",
    		headers = "content-type=application/x-www-form-urlencoded"
	)
    @Transactional
    @Transient
    @ResponseBody
    public void saveUser(HttpServletRequest req, HttpServletResponse response) throws IOException, ParseException {
    	TblUser user = new TblUser();
    	TblCompany company = new TblCompany();
    	TblInsurance insurance = new TblInsurance();
    	if (Boolean.getBoolean(req.getParameter("is_company")) == false) {
        	company.setCompanyName(req.getParameter("company_name"));
        	company.setAddress(req.getParameter("address"));
        	company.setEmail(req.getParameter("email"));
        	company.setTelephone(req.getParameter("telephone"));
        	_companyRepository.save(company);
        	_companyRepository.flush();
    	}

    	insurance.setInsuranceNumber(req.getParameter("insurance_number"));
    	String startDateString = req.getParameter("insurance_start_date");
    	String endDateString = req.getParameter("insurance_end_date");
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Date startDate;
    	Date endDate;
    	startDate = df.parse(startDateString);
    	endDate = df.parse(endDateString);
    	insurance.setInsuranceStartDate(startDate);
    	insurance.setInsuranceEndDate(endDate);
    	insurance.setPlaceOfRegister(req.getParameter("place_of_register"));
    	
    	_insuranceRepository.save(insurance);
    	_insuranceRepository.flush();
    	
    	user.setUserFullName(req.getParameter("user_full_name"));
    	user.setUserName(req.getParameter("user_full_name"));
    	user.setPassword("123456123");
    	user.setUserSexDivision(req.getParameter("user_sex_division"));
    	String birthdateString = req.getParameter("birthdate");
    	Date birthdate;
    	birthdate = df.parse(birthdateString);
    	user.setBirthdate(birthdate);
    	
    	_userRepository.save(user);
    	_userRepository.flush();

    	RedirectAttributes attributes = null;
    	attributes.addFlashAttribute("message", "Đăng ký thành công");
    	
    	response.sendRedirect("/user/add");
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