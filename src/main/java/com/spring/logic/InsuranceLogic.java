package com.spring.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.InsuranceDao;
import com.spring.entity.TblCompany;

@Service
public class InsuranceLogic {
	
	@Autowired
	private InsuranceDao insuranceDao;
	
	public InsuranceLogic(InsuranceDao insuranceDao) {
		this.insuranceDao = insuranceDao;
	}
	
	public List<TblCompany> findAll() {
		return insuranceDao.findAll();
	}
}