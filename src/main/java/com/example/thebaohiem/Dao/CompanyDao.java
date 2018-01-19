package com.example.thebaohiem.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.thebaohiem.model.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao extends JpaRepository<Company ,Integer> {
    Company findByEmail(String email);
    Company findByTelephone(String telephone);
    Company findByCompanyInternalId(int companyInternalId);
}
