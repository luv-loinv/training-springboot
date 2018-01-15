package com.example.thebaohiem.logic.Impl;

import com.example.thebaohiem.Dao.CompanyDao;
import com.example.thebaohiem.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CompanyLogicImpl {

    @Autowired
    private CompanyDao companyDao;

    public Company findByCompany(int companyInternalId)
    {
        return companyDao.findOne(companyInternalId);
    }
}
