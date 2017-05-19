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

	public boolean findByCompanyName(String companyName) {
		try {
			companyDao.findByCompanyName(companyName);
			return false;
		} catch (NullPointerException e) {
			return true;
		}
	}
}
