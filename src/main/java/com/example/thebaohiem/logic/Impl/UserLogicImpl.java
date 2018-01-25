package com.example.thebaohiem.logic.Impl;

import com.example.thebaohiem.Dao.CompanyDao;
import com.example.thebaohiem.Dao.InsuranceDao;
import com.example.thebaohiem.Dao.UserDao;
import com.example.thebaohiem.Dao.custom.UserDaoCustom;
import com.example.thebaohiem.model.Company;
import com.example.thebaohiem.model.Form.LoginForm;
import com.example.thebaohiem.model.Form.SearchForm;
import com.example.thebaohiem.model.Form.UserInfoForm;
import com.example.thebaohiem.model.Insurance;
import com.example.thebaohiem.model.User;
import com.example.thebaohiem.model.UserInfo;
import com.example.thebaohiem.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * check User existed in tbl_user with userName and password
     *
     * @param userName : userName
     * @param password : password
     * @return : true :existed , false ; not exist
     */
    public boolean checkExistUser(String userName, String password) {
        return userDao.findByUserNameAndPassword(userName, password).size() > 0;
    }

    /**
     * @param userInfoForm
     * @param loginForm
     * @return
     */
    public boolean insertUserInfo(UserInfoForm userInfoForm, LoginForm loginForm) {
        Company company = new Company();
        if (userInfoForm.getCompanyInternalID() == 0) {
            company.setCompanyInternalId(userInfoForm.getCompanyInternalID());
            company.setCompanyName(userInfoForm.getCompanyName());
            company.setAddress(userInfoForm.getCompanyAddress());
            if (Common.isEmpty(userInfoForm.getEmail()) == false) {
                company.setEmail(userInfoForm.getEmail());
            }
            if (Common.isEmpty(userInfoForm.getTelephone()) == false) {
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

    /**
     * @param userId
     * @return
     */
    @Transactional
    public boolean deleteUserInfo(int userId) {
        userDao.delete(userId);
        return true;
    }

    /**
     * @param userId
     * @return
     */
    public UserInfoForm getUserInfoByUserId(int userId) {
        return Common.getUserInfoFormFromUser(userDao.findByUserInternalId(userId));
    }

    public List<UserInfo> getListUserInfo(SearchForm searchForm) {
        String insuranceNumber = searchForm.getInsuranceNumber();
        int companyId = Integer.parseInt(searchForm.getCompanyId());
        int limit = 5;
        String fullName = Common.replaceWildcard(searchForm.getFullName());
        String placeOfRegister = Common.replaceWildcard(searchForm.getPlaceOfRegister());
        int currentPage = Integer.parseInt(searchForm.getCurrentPage());
        String sortType = searchForm.getSortType();
        int offset = Common.getOffset(currentPage, limit);
        List<UserInfo> userInfoList = userDaoCustom.getListUserInfo(companyId, fullName, insuranceNumber, placeOfRegister, offset, limit, sortType);
        List<UserInfo> userInfoList1 = new ArrayList<>();
        for (UserInfo userInfo : userInfoList
                ) {
            userInfo.setBirthdate(Common.convertDateMysql(userInfo.getBirthdate()));
            userInfo.setInsuranceEndDate(Common.convertDateMysql(userInfo.getInsuranceEndDate()));
            userInfo.setInsuranceStartDate(Common.convertDateMysql(userInfo.getInsuranceStartDate()));
            userInfo.setUserSexDivision(Common.getSex(userInfo.getUserSexDivision()));
            userInfoList1.add(userInfo);
        }
        return userInfoList1;
    }

    public int getTotalPage(SearchForm searchForm) {
        String regex = "";
        int count = 0;
        String insuranceNumber = searchForm.getInsuranceNumber();
        if (Common.isEmpty(insuranceNumber) || Common.checkFormat(insuranceNumber, regex)) {
            int companyId = Integer.parseInt(searchForm.getCompanyId());
            String fullName = searchForm.getFullName();
            if (fullName.length() < 51) {
                fullName = Common.replaceWildcard(fullName);
                String placeOfRegister = Common.replaceWildcard(searchForm.getPlaceOfRegister());
                if (placeOfRegister.length() < 51) {
                    placeOfRegister = Common.replaceWildcard(placeOfRegister);
                    count = userDaoCustom.getTotalUser(companyId, fullName, insuranceNumber, placeOfRegister);
                }
            }
        }
        return count;
    }
}
