package com.spring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.TblCompany;

@Transactional
@Repository
public interface InsuranceDao extends JpaRepository<TblCompany, Integer> {

	List<TblCompany> findAll();
}
