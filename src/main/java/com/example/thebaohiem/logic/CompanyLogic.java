package com.example.thebaohiem.logic;

import com.example.thebaohiem.model.Company;

public interface CompanyLogic {
     Company findByCompanyInternalId(int companyInternalId);
    boolean checkExistTel(String tel);
    boolean checkExistEmail(String email);
}
