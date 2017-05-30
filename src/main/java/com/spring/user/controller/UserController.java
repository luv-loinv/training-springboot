package com.spring.user.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.spring.company.entity.TblCompany;
import com.spring.company.service.CompanyService;
import com.spring.insurance.entity.TblInsurance;
import com.spring.insurance.service.InsuranceService;
import com.spring.user.entity.TblUser;
import com.spring.user.form.UserForm;
import com.spring.user.service.UserService;
import com.spring.validator.ValidateFormUser;

@Controller
public class UserController {
	
	private MessageSource messageSource;

	@Autowired
	private UserService userService;

	@Autowired
	private ValidateFormUser validFormUser;

	@Autowired
	HttpSession session;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private InsuranceService insuranceService;

	private final Gson gson = new Gson();

	/**
	 * trang chi tiết user
	 * @param id
	 * @param model
	 * @return
	 */
    @RequestMapping("user/detail/{id}")
    public String detail(@PathVariable("id") Long id, Map<String, Object> model) {
    	try {
    		TblUser userDetail = userService.getUserRepo().findOne(id);
    		model.put("insurace", userDetail.getTblInsurance());
    		model.put("company", userDetail.getTblCompany());
    		model.put("user", userDetail);
    	} catch (Exception e) {}
    	return "user/detail";
    }
    
    /**
     * lưu user
     * @param req
     * @param response
     * @param userForm
     * @param errors
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(
    		value = "user/save",
    		method = RequestMethod.POST,
    		consumes="application/json",
    		headers = "content-type=application/x-www-form-urlencoded"
	)
    @Transactional
    public String saveUser(HttpServletRequest req, HttpServletResponse response, ModelMap model, 
    	@Valid @ModelAttribute("userForm") UserForm userForm, Errors errors) throws IOException, ParseException {

    	validFormUser.validate(userForm, errors);
    	if (errors.hasErrors()) {
    		model.addAttribute("userFormData", gson.toJson(userForm));
    		model.addAttribute("messageError", gson.toJson(errors.getAllErrors()));
    		return "/user/add";
    	}

    	TblCompany company = companyService.saveCompany(userForm);

    	TblInsurance insurance = insuranceService.saveInsurance(userForm);
    	
    	userService.saveUser(userForm, company, insurance);

		session.setAttribute("messageSuccess", "Đăng ký thành công");
    	response.sendRedirect("/user/add");
    	return "";
    }

    /**
     * thêm mới user
     * @param model
     * @return
     */
    @RequestMapping(value = "user/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
    	UserForm userForm = new UserForm();
		model.addAttribute("userFormData", gson.toJson(userForm));
		model.addAttribute("messageError", gson.toJson(null));
    	return "user/add";
    }

    /**
     * xóa 1 user
     * @param req
     * @param response
     * @param id
     * @throws Exception
     * @throws IOException
     */
    @Transactional
    @RequestMapping("user/delete/{id}")
    public void deleteUser(HttpServletRequest req, HttpServletResponse response, @PathVariable("id") Long id) throws Exception, IOException {
    	TblUser user = userService.getUserRepo().findOne(id);
    	userService.getUserRepo().delete(user);
    	System.out.println(user.getTblInsurance().getInsuranceInternalId());
    	//_insuranceRepository.delete(user.getTblInsurance().getInsuranceInternalId());
    	
    	response.sendRedirect("/search");
    }
}