package com.spring.insurance.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.spring.insurance.entity.TblInsurance;
import com.spring.insurance.repository.InsuranceRepository;
import com.spring.user.entity.TblUser;
import com.spring.user.repository.UserRepository;

@Controller
public class InsuranceController {
	@Autowired
	private InsuranceRepository _insuranceRepository;

    @RequestMapping("insurance/detail/{id}")
    public String detail(@PathVariable("id") Long id, Map<String, Object> model) {
    	TblInsurance insuranceDetail = _insuranceRepository.findOne(id);
    	Gson gson = new Gson();
		System.out.println(gson.toJson(insuranceDetail.getInsuranceNumber()));
    	//model.put("insuranceDetail", insuranceDetail);
    	return "insurance/detail";
    	
    }
}