package com.example.thebaohiem.Dao;

import com.example.thebaohiem.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * connect to tbl_insurance in database
 * @author lethiha
 */
@Repository
public interface InsuranceDao extends JpaRepository<Insurance , Integer> {
    /**
     * find Company by insuranceNumber
     * @param insuranceNumber : insuranceNumber
     * @return :  Insurance
     */
    Insurance findByInsuranceNumber(String insuranceNumber);
}
