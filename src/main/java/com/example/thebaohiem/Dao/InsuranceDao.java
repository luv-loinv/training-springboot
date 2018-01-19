package com.example.thebaohiem.Dao;

import com.example.thebaohiem.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceDao extends JpaRepository<Insurance , Integer> {
    Insurance findByInsuranceNumber(String insuranceNumber);
}
