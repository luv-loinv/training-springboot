package com.manager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.manager.logics.TblUserLogic;
import com.manager.utils.Constant;
import com.manager.utils.Validator;

@Controller("/login")
public class LoginController {
	@Autowired
	private HttpSession session;

	@Autowired
	private TblUserLogic userLogic;

	@RequestMapping(value = { "/login", "/" }, method = RequestMethod.GET)
	public String loginPage() {
		return "MH01";
	}

	@RequestMapping(value = { "/login", "/" }, method = RequestMethod.POST)
	public String loginPage(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) {
		try {
			List<String> errorList = Validator.validLogin(username, password, userLogic);
			if (errorList.size() == 0) {
				session.setAttribute("loginName", username);
				return "redirect:listUser";
			}
			model.addAttribute("loginName", username);
			model.addAttribute("errorList", errorList);
			return Constant.MH01;
		} catch (Exception e) {
			return Constant.SYSTEM_ERROR;
		}
	}
}
