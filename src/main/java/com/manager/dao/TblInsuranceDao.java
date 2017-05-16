package com.manager.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.entities.TblInsurance;

@Transactional
public interface TblInsuranceDao extends JpaRepository<TblInsurance, Integer> {

	List<TblInsurance> findByInsuranceNumber(String insurancenumber);

	List<TblInsurance> findByPlaceOfRegister(String place);

	List<TblInsurance> findByInsuranceNumberAndPlaceOfRegister(String insnumber, String place);
}
