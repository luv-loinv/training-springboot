package com.spring.user.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.spring.company.entity.TblCompany;
import com.spring.company.service.CompanyService;
import com.spring.insurance.entity.TblInsurance;
import com.spring.insurance.service.InsuranceService;
import com.spring.user.entity.TblUser;
import com.spring.user.form.UserForm;
import com.spring.user.service.UserService;

@Controller
public class UserController {
	
	private MessageSource messageSource;

	@Autowired
	private UserService _userService;

	@Autowired
	HttpSession session;

	@Autowired
	private CompanyService _companyService;

	@Autowired
	private InsuranceService _insuranceService;

	private final Gson gson = new Gson();

    @RequestMapping("user/detail/{id}")
    public String detail(@PathVariable("id") Long id, Map<String, Object> model) {
    	try {
    		TblUser userDetail = _userService.getUserRepo().findOne(id);
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
    public String saveUser(HttpServletRequest req, HttpServletResponse response,
    	@Valid @ModelAttribute("userForm") UserForm userForm, Errors errors) throws IOException, ParseException {

    	if (errors.hasErrors()) {
    		session.setAttribute("messageError", errors);
    		return "/user/add";
    	}
    	
    	TblCompany company = _companyService.saveCompany(userForm);

    	TblInsurance insurance = _insuranceService.saveInsurance(userForm);
    	
    	_userService.saveUser(userForm, company, insurance);

		Cookie cookie = new Cookie("messageSuccess", "Đăng ký thành công");
		response.addCookie(cookie);
    	response.sendRedirect("/user/add");
    	return "";
    }

    @RequestMapping(value = "user/add", method = RequestMethod.GET)
    public String add(Map<String, Object> model) {
    	
    	return "user/add";
    }

    @Transactional
    @RequestMapping("user/delete/{id}")
    public void deleteUser(HttpServletRequest req, HttpServletResponse response, @PathVariable("id") Long id) throws Exception, IOException {
    	TblUser user = _userService.getUserRepo().findOne(id);
    	_userService.getUserRepo().delete(user);
    	System.out.println(user.getTblInsurance().getInsuranceInternalId());
    	//_insuranceRepository.delete(user.getTblInsurance().getInsuranceInternalId());
    	
    	response.sendRedirect("/search");
    }
}