package com.example.thebaohiem.logic.Impl;

import com.example.thebaohiem.Dao.UserDao;
import com.example.thebaohiem.Dao.UserDaoCustomImpl;
import com.example.thebaohiem.Dao.custom.UserDaoCustom;
import com.example.thebaohiem.model.Company;
import com.example.thebaohiem.model.Form.LoginForm;
import com.example.thebaohiem.model.Form.UserInfoForm;
import com.example.thebaohiem.model.Insurance;
import com.example.thebaohiem.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLogicImpl {
    @Autowired
    UserDao userDao;
    @Autowired
    UserDaoCustom userDaoCustom;

    public boolean checkExistUser(String userName, String password) {
        return userDao.findByUserNameAndPassword(userName, password) != null ? true : false;
    }

    public boolean insertUserInfo(UserInfoForm userInfoForm, LoginForm loginForm) {
        Company company = new Company();
        company.setCompanyInternalId(userInfoForm.getCompanyInternalID());
        company.setCompanyName(userInfoForm.getCompanyName());
        company.setAddress(userInfoForm.getCompanyAddress());
        company.setEmail(userInfoForm.getEmail());
        company.setTelephone(userInfoForm.getTelephone());
        Insurance insurance = new Insurance();
        insurance.setInsuranceInternalId(userInfoForm.getInsuranceInternalId());
        insurance.setInsuranceNumber(userInfoForm.getInsuranceNumber());
        insurance.setInsuranceStartDate(userInfoForm.getInsuranceStartDate());
        insurance.setInsuranceEndDate(userInfoForm.getInsuranceEndDate());
        User user = new User();
        user.setUserInternalId(userInfoForm.getUserInternalID());
        user.setUserName(loginForm.getUserName());
        user.setPassword(loginForm.getPassword());
        user.setUserFullName(userInfoForm.getFullName());
        user.setUserSexDivision(userInfoForm.getSex());
        user.setBirthdate(userInfoForm.getBirthday());
        user.setCompany(company);
        user.setInsurance(insurance);
        // UserDaoCustomImpl userDaoCustom = new UserDaoCustomImpl();
        return userDaoCustom.insertUserInfo(user, insurance, company);

    }

}