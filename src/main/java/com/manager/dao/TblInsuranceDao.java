package com.manager.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manager.entities.TblInsurance;

@Repository
@Transactional
public interface TblInsuranceDao extends JpaRepository<TblInsurance, Integer> {
	/**
	 * 
	 * @param insurance
	 * @return
	 */
	int countByInsuranceNumber(String insurance);
}
