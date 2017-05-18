package com.manager.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manager.entities.TblCompany;

@Repository
@Transactional
public interface TblCompanyDao extends JpaRepository<TblCompany, Integer> {

	TblCompany findByCompanyName(String companyName);
}
