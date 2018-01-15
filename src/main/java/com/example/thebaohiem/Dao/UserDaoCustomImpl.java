package com.example.thebaohiem.Dao;

import com.example.thebaohiem.Dao.custom.UserDaoCustom;
import com.example.thebaohiem.model.Company;
import com.example.thebaohiem.model.Insurance;
import com.example.thebaohiem.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class UserDaoCustomImpl implements UserDaoCustom {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private InsuranceDao insuranceDao;

    @Transactional(rollbackOn = Exception.class)
    public boolean insertUserInfo(User user, Insurance insurance, Company company) {
        try {
            companyDao.save(company);
            insuranceDao.save(insurance);
//        user.setCompanyInternalId(company.getCompanyInternalId());
//        user.setInsuranceInternalId(insurance.getInsuranceInternalId());
            userDao.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
