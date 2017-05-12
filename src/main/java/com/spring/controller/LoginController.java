package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.bean.LoginBean;
import com.spring.logic.LoginLogic;

@Controller
public class LoginController {
	
	private LoginLogic loginlogic;

	public LoginLogic getLoginlogic() {
		return loginlogic;
	}

	public void setLoginlogic(LoginLogic loginlogic) {
		this.loginlogic = loginlogic;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, 
			LoginBean loginBean) {
		ModelAndView model = new ModelAndView("login");
		model.addObject("loginBean", loginBean);
		return model;
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean")LoginBean loginBean) {
		
		ModelAndView model= null;
		try {
			     String JSONResponse = response.toString();
			     String JSONRequest = request.toString();
			     System.out.println(JSONResponse);
			     System.out.println(JSONRequest);
			     
				boolean isSucsess = loginlogic.isSucsecssLogic(loginBean.getUserName(), loginBean.getPassWord());
				System.out.println(isSucsess);
				if(isSucsess) {
						// TODO : gọi đến màn hình MH002
				} else {
						model = new ModelAndView("login");
						request.setAttribute("message", "Tên đăng nhập hoặc Mật khẩu không đúng!");
				}

		} catch(Exception e) {
				e.printStackTrace();
		}

		return model;
	}
}