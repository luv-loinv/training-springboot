package com.example.thebaohiem.logic.Impl;

import com.example.thebaohiem.Dao.CompanyDao;
import com.example.thebaohiem.Dao.custom.UserDaoCustom;
import com.example.thebaohiem.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CompanyLogicImpl {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private UserDaoCustom userDaoCustom;

    /**
     * check Email exist in tbl_company.
     * @param email : email
     * @param companyId : companyId
     * @return true:  existed in database , false : not exist in database.
     */
    public boolean checkExistEmail(String email, int companyId) {
        boolean check = false;
        Company company = companyDao.findByEmail(email);;
        if (company != null) {
            if (companyId != company.getCompanyInternalId()) {
                check = true;
            }
        }
        return check;
    }

    /**
     * check exist tel in tbl_company
     * @param tel : tel
     * @param companyId : companyInternalId
     * @return : true : existed ,false : not exist
     */
    public boolean checkExistTel(String tel, int companyId) {
        boolean check = false;
        Company company = companyDao.findByTelephone(tel);
        if (company != null) {
            if (companyId != company.getCompanyInternalId()) {
                check = true;
            }
        }
        return check;
    }
    public List<Company> getListCompanyList()
    {
        return userDaoCustom.getListCompany();
    }
    public boolean checkExistCompany(int companyId)
    {
        return companyDao.findAll().size()>0;
    }
}
