package com.spring.company.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.company.entity.TblCompany;

@Repository
public interface CompanyRepository extends JpaRepository<TblCompany,Long> {
   public static final String FIND_COMPANYS = "SELECT company_internal_id, company_name FROM tbl_company";
   public static final String COUNT_FIND_COMPANYS = "SELECT count(*) FROM tbl_company";
   
   @Query(
	   value = FIND_COMPANYS,
	   countName = COUNT_FIND_COMPANYS,
	   nativeQuery = true
   )
   public List<Object[]> findCompanys();
} 