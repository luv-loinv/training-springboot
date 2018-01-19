package com.example.thebaohiem.web;

import com.example.thebaohiem.Dao.CompanyDao;
import com.example.thebaohiem.logic.Impl.CompanyLogicImpl;
import com.example.thebaohiem.logic.Impl.UserLogicImpl;
import com.example.thebaohiem.model.Company;
import com.example.thebaohiem.model.Form.LoginForm;
import com.example.thebaohiem.model.Form.UserInfoForm;
import com.example.thebaohiem.model.User;
import com.example.thebaohiem.model.UserInfo;
import com.example.thebaohiem.validates.LoginValidator;
import com.example.thebaohiem.validates.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@EnableAutoConfiguration
@PropertySource(value = "classpath:message_error.properties", encoding = "UTF-8")
public class HelloWordController {

    @Autowired
    private LoginValidator loginValidator;
    @Autowired
    private UserLogicImpl userLogicImpl;
    @Autowired
    private CompanyLogicImpl companyLogic;

    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String home(ModelMap modelMap) {
        LoginForm loginForm = new LoginForm();
        modelMap.addAttribute("account", loginForm);
        return "MH01";
    }

    @RequestMapping(value = {"/helloword"}, method = RequestMethod.GET)
    public String helloWord(ModelMap modelMap) {
        return "helloword";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(ModelMap modelMap, @Valid @ModelAttribute("account") LoginForm loginForm, BindingResult result, HttpSession session) {
        // loginValidator.validate(loginForm, result);
        if (result.hasErrors() == true) {
            modelMap.addAttribute("account", loginForm);
            return "MH01";
        } else {
            boolean check = userLogicImpl.checkExistUser(loginForm.getUserName(), loginForm.getPassword());
            if (check == true) {
                session.setAttribute("loginFormSS", loginForm);
                return "redirect:/addCompany/1";
            }
            return "MH01";
        }
    }

    @RequestMapping(value = {"/addCompany", "addCompany/{userIdString}"}, method = RequestMethod.GET)
    public String userInfo(ModelMap modelMap, @PathVariable String userIdString) {
        UserInfoForm userInfoForm = new UserInfoForm();
        try {
            int userId = Integer.parseInt(userIdString);
            if (userId> 0) {
                userInfoForm = userLogicImpl.getUserInfoByUserId(userId);
                if(userInfoForm == null)
                {
                    userInfoForm = new UserInfoForm();
                }
            }
        } finally {
            modelMap.addAttribute("userInfoForm", userInfoForm);
            return "MH04";
        }
    }
    @RequestMapping(value = {"/addCompany"}, method = RequestMethod.POST)
    public String addUserInfo(@ModelAttribute("userInfoForm") UserInfoForm userInfoForm, HttpSession session, ModelMap modelMap) {
        RegisterValidator registerValidator = new RegisterValidator();
        List<String> errList = registerValidator.validate(userInfoForm);
        if (errList.size() > 0) {
            modelMap.addAttribute("userInfo", userInfoForm);
            modelMap.addAttribute("errList", errList);
            return "MH04";
        } else {
            LoginForm loginForm = (LoginForm) session.getAttribute("loginFormSS");
            boolean check = userLogicImpl.insertUserInfo(userInfoForm, loginForm);
            if (check == true) {
                return "redirect:/MH02";
            }
            return "redirect:/helloword";
        }

    }

    @RequestMapping(value = {"/viewDetail"}, method = RequestMethod.GET)
    public String viewUser(ModelMap modelMap) {
        UserInfo userInfo = userLogicImpl.findByUserInternalId(1);
        if (userInfo != null) {
            modelMap.addAttribute("userInfo", userInfo);
            return "MH03";
        }
        return "redirect:/MH02";
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.POST)
    public String delete(HttpServletRequest request) {
        int useId = Integer.parseInt(request.getParameter("userId"));
        boolean check = userLogicImpl.deleteUserInfo(useId);
        if (check == true) {
            return "redirect:/login";
        }
        return "redirect:/viewDetail";
    }
}

