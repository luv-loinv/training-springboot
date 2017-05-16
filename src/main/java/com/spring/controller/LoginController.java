package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.bean.LoginBean;
import com.spring.entity.TblUser;
import com.spring.logic.LoginLogic;

@Controller
public class LoginController {
	
	private final LoginLogic loginlogic;
	
	@Autowired
	public LoginController(LoginLogic loginlogic) {
		this.loginlogic = loginlogic;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, 
			LoginBean loginBean) {
		ModelAndView model = new ModelAndView("login/login");
		model.addObject("loginBean", loginBean);
		return model;
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean")LoginBean loginBean) {
		
		ModelAndView model= null;
		if(loginBean.getUserName().equals("") && loginBean.getPassWord().equals("")) {
			model = new ModelAndView("login/login");
			request.setAttribute("message", "Hãy nhập Tên đăng nhập và Mật khẩu");
		} else if(loginBean.getUserName().equals("")) {
			model = new ModelAndView("login/login");
			request.setAttribute("message", "Hãy nhập Tên đăng nhập");
		} else if(loginBean.getPassWord().equals("")) {
			model = new ModelAndView("login/login");
			request.setAttribute("message", "Hãy nhập Mật khẩu");
		} else {
			try {
					String md5PassWord = loginlogic.md5(loginBean.getPassWord());
					loginBean.setPassWord(md5PassWord);
					List<TblUser> listTblUser = loginlogic.findByUsernameAndPassword(loginBean.getUserName(), loginBean.getPassWord());
					if(listTblUser.size() == 0) {
						model = new ModelAndView("login/login");
						request.setAttribute("message", "Tên đăng nhập hoặc Mật khẩu không đúng!");
					} else {
						// TODO : gọi đến màn hình MH002
					}
	
			} catch(Exception e) {
					e.printStackTrace();
			}
		}

		return model;
	}
}