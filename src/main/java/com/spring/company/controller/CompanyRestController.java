package com.spring.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.spring.company.entity.TblCompany;
import com.spring.company.repository.CompanyRepository;

@RestController
public class CompanyRestController {
	@Autowired
	private CompanyRepository _companyRepository;
	private final Gson gson = new Gson();

    @RequestMapping("api/company/getlist")
    public String getList() throws Exception {
    	List<Object[]> listCompany = _companyRepository.findCompanys();
    	
    	return gson.toJson(listCompany);
    }
    
    @RequestMapping("api/company/getdetail/{id}")
    public String getDetailById(@PathVariable("id") Long id) throws Exception {
    	TblCompany companyDetail = _companyRepository.findOne(id);
    	companyDetail.setTblUsers(null);
    	
    	return gson.toJson(companyDetail);
    }
    
}