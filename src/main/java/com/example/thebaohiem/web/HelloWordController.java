package com.example.thebaohiem.web;

import com.example.thebaohiem.logic.Impl.CompanyLogicImpl;
import com.example.thebaohiem.logic.Impl.UserLogicImpl;
import com.example.thebaohiem.model.Company;
import com.example.thebaohiem.model.Form.LoginForm;
import com.example.thebaohiem.model.Form.SearchForm;
import com.example.thebaohiem.model.Form.UserInfoForm;
import com.example.thebaohiem.model.UserInfo;
import com.example.thebaohiem.utils.Common;
import com.example.thebaohiem.validates.LoginValidator;
import com.example.thebaohiem.validates.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
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
import java.util.Optional;

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
                return "redirect:/addCompany";
            }
            return "MH01";
        }
    }

    @RequestMapping(value = {"/addCompany"}, method = RequestMethod.GET)
    public String userInfo(ModelMap modelMap) {
        UserInfoForm userInfoForm = new UserInfoForm();
        modelMap.addAttribute("userInfoForm", userInfoForm);
        return "MH04";
    }

    @RequestMapping(value = {"addCompany/{userIdString}"}, method = RequestMethod.GET)
    public String showUserInfo(ModelMap modelMap, @PathVariable String userIdString) {
        try {
            int userId = Integer.parseInt(userIdString);
            UserInfoForm userInfoForm = userLogicImpl.getUserInfoByUserId(userId);
            if (userInfoForm == null) {
                return "redirect:/login";
            } else {
                modelMap.addAttribute("userInfoForm", userInfoForm);
                return "MH04";
            }
        } catch (Exception e) {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = {"/addCompany"}, method = RequestMethod.POST)
    public String addUserInfo(@ModelAttribute("userInfoForm") UserInfoForm userInfoForm, HttpSession session, ModelMap modelMap) {
        RegisterValidator registerValidator = new RegisterValidator();
        List<String> errList = registerValidator.validate(userInfoForm);
        if (errList.size() > 0) {
            modelMap.addAttribute("userInfoForm", userInfoForm);
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

    @RequestMapping(value = {"/viewDetail/{userIdStr}"}, method = RequestMethod.GET)
    public String viewUser(ModelMap modelMap, @PathVariable String userIdStr) {
        try {
            int userId = Integer.parseInt(userIdStr);
            UserInfoForm userInfo = userLogicImpl.getUserInfoByUserId(userId);
            if (userInfo != null) {
                modelMap.addAttribute("userInfo", userInfo);
                return "MH03";
            }
            return "redirect:/MH02";
        }
        catch (Exception e)
        {
           return  "redirect:/Error";
        }

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

    public HelloWordController() {
    }

    @RequestMapping(value = {"/searchListUser", "/searchListUser/{action}"}, method = RequestMethod.GET)
    public String viewSearchUser(ModelMap modelMap, SearchForm formSearch, HttpSession session, @PathVariable Optional<String> action) {
        List<Company> companyList = companyLogic.getListCompanyList();
        if (companyList.size() > 0) {
            if (action.isPresent()) {
                String actionValue = action.get();
                switch (actionValue) {
                    case "search":
                        formSearch.setCurrentPage("1");
                        formSearch.setSortType("DESC");
                        break;
                    case "sort":
                        formSearch.setSortType(Common.getSortType(formSearch.getSortType()));
                        break;
                    case "paging":
                        //  formSearch.setCurrentPage(Common.getCurrentPage(formSearch.getCurrentPage()));
                    case "back":
                        Object formSearchSesion = session.getAttribute("formSearch");
                        if (formSearchSesion != null) {
                            formSearch = (SearchForm) formSearchSesion;
                        } else
                            formSearch = new SearchForm();
                        break;
                }
            }
            int currentPage;
            int companyId = Common.getInt(formSearch.getCompanyId());
            formSearch.setCompanyId(""+companyId);
            int totalRecord = userLogicImpl.getTotalPage(formSearch);
            if (totalRecord > 0) {
                int limit = 2;
                int totalPage = Common.getTotalPage(totalRecord, limit);
                currentPage = Common.getCurrentPage(formSearch.getCurrentPage(), totalPage);
                formSearch.setCurrentPage(""+currentPage);
                List<UserInfo> userInfoList = userLogicImpl.getListUserInfo(formSearch);
                List<Integer> pagingList = Common.getListPaging(totalPage, currentPage, 5);
                modelMap.addAttribute("pagingList", pagingList);
                modelMap.addAttribute("userInfoList", userInfoList);
                modelMap.addAttribute("currentPage", currentPage);
                modelMap.addAttribute("totalPage", totalPage);
            } else {
                String mess = "không tìm thấy bản ghi";
                modelMap.addAttribute("mess", mess);
            }
            modelMap.addAttribute("formSearch", formSearch);
            modelMap.addAttribute("companyList", companyList);
            session.setAttribute("searchForm", formSearch);

            return "MH02";
        }
        return "redirect:/Error";
    }
}

