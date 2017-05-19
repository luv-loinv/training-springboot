package com.manager.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.entities.TblInsurance;

@Transactional
public interface TblInsuranceDao extends JpaRepository<TblInsurance, Integer> {

	TblInsurance findByInsuranceNumber(String insuranceNumber);

	List<TblInsurance> findByInsuranceNumberContaining(String insurancenumber);

	List<TblInsurance> findByPlaceOfRegisterContaining(String place);

	List<TblInsurance> findByInsuranceNumberContainingAndPlaceOfRegisterContaining(String insnumber, String place);

	int countByInsuranceNumber(String insurance);
}
