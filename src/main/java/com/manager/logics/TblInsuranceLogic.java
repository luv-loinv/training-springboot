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
		return insurenceDao.findByInsuranceNumber(insurancenumber);
	}

	public List<TblInsurance> findByPlaceOfRegister(String place) {
		return insurenceDao.findByPlaceOfRegister(place);
	}

	public List<TblInsurance> findByInsuranceNumberAndPlaceOfRegister(String insnumber, String place) {
		return insurenceDao.findByInsuranceNumberAndPlaceOfRegister(insnumber, place);
	}
}
