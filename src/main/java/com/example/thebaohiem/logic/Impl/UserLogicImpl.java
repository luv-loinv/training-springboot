package com.example.thebaohiem.logic.Impl;

import com.example.thebaohiem.Dao.CompanyDao;
import com.example.thebaohiem.Dao.InsuranceDao;
import com.example.thebaohiem.Dao.UserDao;
import com.example.thebaohiem.Dao.custom.UserDaoCustom;
import com.example.thebaohiem.model.Company;
import com.example.thebaohiem.model.Form.LoginForm;
import com.example.thebaohiem.model.Form.UserInfoForm;
import com.example.thebaohiem.model.Insurance;
import com.example.thebaohiem.model.User;
import com.example.thebaohiem.model.UserInfo;
import com.example.thebaohiem.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserLogicImpl {
    @Autowired
    UserDao userDao;
    @Autowired
    UserDaoCustom userDaoCustom;
    @Autowired
    InsuranceDao insuranceDao;
    @Autowired
    CompanyDao companyDao;


    public boolean checkExistUser(String userName, String password) {
        return userDao.findByUserNameAndPassword(userName, password).size() > 0 ? true : false;
    }

    public boolean insertUserInfo(UserInfoForm userInfoForm, LoginForm loginForm) {
        Company company = new Company();
        if (userInfoForm.getCompanyInternalID() == 0) {
            company.setCompanyInternalId(userInfoForm.getCompanyInternalID());
            company.setCompanyName(userInfoForm.getCompanyName());
            company.setAddress(userInfoForm.getCompanyAddress());
            if (Common.checkEmpty(userInfoForm.getEmail()) == false) {
                company.setEmail(userInfoForm.getEmail());
            }
            if (Common.checkEmpty(userInfoForm.getTelephone()) == false) {
                company.setTelephone(userInfoForm.getTelephone());
            }

        }
        Insurance insurance = new Insurance();
        insurance.setInsuranceInternalId(userInfoForm.getInsuranceInternalId());
        insurance.setInsuranceNumber(userInfoForm.getInsuranceNumber());
        insurance.setInsuranceStartDate(Common.convertDateHQL(userInfoForm.getInsuranceStartDate()));
        insurance.setInsuranceEndDate(Common.convertDateHQL(userInfoForm.getInsuranceEndDate()));
        insurance.setPlaceOfRegister(userInfoForm.getPlaceOfRegister());
        User user = new User();
        user.setUserInternalId(userInfoForm.getUserInternalID());
        user.setUserName(loginForm.getUserName());
        user.setPassword(loginForm.getPassword());
        user.setUserFullName(userInfoForm.getFullName());
        user.setUserSexDivision(userInfoForm.getSex());
        String birthday = userInfoForm.getBirthday();
        user.setBirthdate(Common.convertDateHQL(birthday));
        user.setCompany(company);
        user.setInsurance(insurance);
        return userDaoCustom.insertUserInfo(user, insurance, company);

    }

    public UserInfo findByUserInternalId(int userInternalId) {
        User user = userDao.findByUserInternalId(userInternalId);
        if (user != null) {
            UserInfo userInfo = new UserInfo();
            userInfo.setBirthdate(user.getBirthdate());
            userInfo.setInsuranceEndDate(user.getInsurance().getInsuranceEndDate());
            userInfo.setInsuranceStartDate(user.getInsurance().getInsuranceStartDate());
            userInfo.setInsuranceNumber(user.getInsurance().getInsuranceNumber());
            userInfo.setPlaceOfRegister(user.getInsurance().getPlaceOfRegister());
            userInfo.setUserSexDivision(user.getUserSexDivision());
            userInfo.setCompanyInternalId(user.getCompany().getCompanyInternalId());
            userInfo.setCompanyName(user.getCompany().getCompanyName());
            userInfo.setUserFullName(user.getUserFullName());
            userInfo.setUserInternalId(userInternalId);
            userInfo.setInsuranceId(user.getInsurance().getInsuranceInternalId());

            return userInfo;
        }
        return null;
    }

    @Transactional
    public boolean deleteUserInfo(int userId) {
        userDao.delete(userId);
        return true;
    }

    public UserInfoForm getUserInfoByUserId(int userId) {
        User user = userDao.findByUserInternalId(userId);
        if (user != null) {
            UserInfoForm userInfo = new UserInfoForm();
            userInfo.setBirthday(Common.convertDateMysql(user.getBirthdate()));
            userInfo.setInsuranceEndDate(Common.convertDateMysql(user.getInsurance().getInsuranceEndDate()));
            userInfo.setInsuranceStartDate(Common.convertDateMysql(user.getInsurance().getInsuranceStartDate()));
            userInfo.setInsuranceNumber(user.getInsurance().getInsuranceNumber());
            userInfo.setPlaceOfRegister(user.getInsurance().getPlaceOfRegister());
            userInfo.setSex(user.getUserSexDivision());
            userInfo.setCompanyAddress(user.getCompany().getAddress());
            userInfo.setEmail((user.getCompany().getEmail()));
            userInfo.setTelephone(user.getCompany().getTelephone());
            userInfo.setCompanyName(user.getCompany().getCompanyName());
            userInfo.setFullName(user.getUserFullName());
            userInfo.setUserInternalID(userId);
            userInfo.setCompanyInternalID(user.getCompany().getCompanyInternalId());
            userInfo.setInsuranceInternalId(user.getInsurance().getInsuranceInternalId());
            return userInfo;

        }
        return null;
    }
}
