package com.spring.insurance.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.insurance.entity.TblInsurance;

@Repository
public interface InsuranceRepository extends JpaRepository<TblInsurance,Long> {
} 