package com.manager.logics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.dao.TblInsuranceDao;
import com.manager.entities.TblInsurance;

@Service
public class TblInsuranceLogic {
	@Autowired
	private TblInsuranceDao insurenceDao;

	public List<TblInsurance> findAll() {
		return insurenceDao.findAll();
	}

	public List<TblInsurance> findByInsuranceNumber(String insurancenumber) {
		return insurenceDao.findByInsuranceNumberContaining(insurancenumber);
	}

	public List<TblInsurance> findByPlaceOfRegisterLike(String place) {
		return insurenceDao.findByPlaceOfRegisterContaining(place);
	}

	public List<TblInsurance> findByInsuranceNumberAndPlaceOfRegisterLike(String insnumber, String place) {
		return insurenceDao.findByInsuranceNumberContainingAndPlaceOfRegisterContaining(insnumber, place);
	}

	public boolean checkExistIns(String insuranceNumber) {
		try {
			insurenceDao.findByInsuranceNumber(insuranceNumber);
			return true;
		} catch (NullPointerException e) {
			return false;
		}
	}
}
