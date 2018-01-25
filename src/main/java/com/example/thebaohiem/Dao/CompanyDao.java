package com.example.thebaohiem.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.thebaohiem.model.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * connected to tbl_company in database
 * @author lethiha
 */
@Repository
public interface CompanyDao extends JpaRepository<Company ,Integer> {
    /**
     * search Company with email
     * @param email : email
     * @return : Company
     */
    Company findByEmail(String email);

    /**
     * search Company with telephone
     * @param telephone : telephone
     * @return : Company
     */
    Company findByTelephone(String telephone);

    /**
     * find Company with companyInternalId
     * @param companyInternalId : companyInternalId
     * @return Company
     */
    Company findByCompanyInternalId(int companyInternalId);
    List<Company> findAll();
}
