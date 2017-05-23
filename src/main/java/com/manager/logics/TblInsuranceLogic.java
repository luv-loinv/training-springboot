package com.manager.logics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.dao.TblInsuranceDao;
import com.manager.entities.TblInsurance;

@Service
public class TblInsuranceLogic {
	@Autowired
	private TblInsuranceDao insurenceDao;

	public boolean checkExistIns(String insNumber) {
		int count = insurenceDao.countByInsuranceNumber(insNumber);
		if (count == 0) {
			return true;
		}
		return false;
	}

	public void saveInsurance(TblInsurance insurance) {
		insurenceDao.save(insurance);
	}
}
