package com.manager.logics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.dao.TblCompanyDao;
import com.manager.entities.TblCompany;

@Service
public class TblCompanyLogic {
	@Autowired
	private TblCompanyDao companyDao;

	public List<TblCompany> findAll() {
		return companyDao.findAll();
	}

	public boolean checkExistComp(String companyName) {
		int count = companyDao.countByCompanyName(companyName);
		if (count == 0) {
			return true;
		}
		return false;
	}

	public void saveCompany(TblCompany company) {
		companyDao.save(company);
	}

	public TblCompany findByCompanyID(int compID) {
		return companyDao.findOne(compID);
	}
}
