package com.example.thebaohiem.web;

import com.example.thebaohiem.Dao.CompanyDao;
import com.example.thebaohiem.logic.Impl.UserLogicImpl;
import com.example.thebaohiem.model.Company;
import com.example.thebaohiem.model.Form.LoginForm;
import com.example.thebaohiem.model.Form.UserInfoForm;
import com.example.thebaohiem.validates.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@EnableAutoConfiguration
public class HelloWordController {

    @Autowired
    private LoginValidator loginValidator;
    @Autowired
    private UserLogicImpl userLogicImpl;

    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String home(ModelMap modelMap) {
        LoginForm loginForm = new LoginForm();
        modelMap.addAttribute("loginForm", loginForm);
        return "MH01";
    }

    @RequestMapping(value = {"/helloword"}, method = RequestMethod.GET)
    public String helloWord(ModelMap modelMap) {
        return "helloword";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(ModelMap modelMap, @Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult result, HttpSession session) {
        // loginValidator.validate(loginForm , result);
        boolean check = userLogicImpl.checkExistUser(loginForm.getUserName(), loginForm.getPassword());
        if (check == true) {
            session.setAttribute("loginForm", loginForm);
            return "redirect:/addCompany";
        }
        return "MH01";
    }

    @RequestMapping(value = {"/addCompany"}, method = RequestMethod.GET)
    public String userInfo(ModelMap modelMap) {
        UserInfoForm userInfoForm = new UserInfoForm();
        modelMap.addAttribute("userInfoForm", userInfoForm);
        return "MH04";
    }

    @RequestMapping(value = {"/addCompany"}, method = RequestMethod.POST)
    public String addUserInfo(@ModelAttribute("userInfoForm") UserInfoForm userInfoForm, HttpSession session, ModelMap modelMap) {
        LoginForm loginForm = (LoginForm) session.getAttribute("loginForm");
        boolean check = userLogicImpl.insertUserInfo(userInfoForm,loginForm);
        if(check==true)
        {
            return  "redirect:/MH02";
        }
        return "redirect:/helloword";
    }
}

