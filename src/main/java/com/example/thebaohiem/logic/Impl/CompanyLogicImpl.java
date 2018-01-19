package com.example.thebaohiem.logic.Impl;

import com.example.thebaohiem.Dao.CompanyDao;
import com.example.thebaohiem.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CompanyLogicImpl {

    @Autowired
    private CompanyDao companyDao;

    public Company findByCompany(int companyInternalId) {
        return companyDao.findOne(companyInternalId);
    }

    public boolean checkExistEmail(String email, int companyId) {
        boolean check = false;
        Company company = companyDao.findByEmail(email);
        if (company != null) {
            if (companyId != company.getCompanyInternalId()) {
                check = true;
            }
        }
        return check;
    }

    /**
     * check exist tel
     * @param tel tel
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
    public Company getCompany(String email)
    {
        return  companyDao.findByEmail(email);
    }
}
