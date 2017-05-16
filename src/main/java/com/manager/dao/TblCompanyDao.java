package com.manager.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.entities.TblCompany;

@Transactional
public interface TblCompanyDao extends JpaRepository<TblCompany, Integer> {

}
