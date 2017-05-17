package com.spring.insurance.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.spring.insurance.bo.InsuranceBO;
import com.spring.insurance.dao.InsuranceDao;

@Controller
public class InsuranceController {
	@Autowired
	private InsuranceDao _insuranceDao;

    @RequestMapping("insurance/detail")
    public String detail(Map<String, Object> model) {
    	ArrayList<InsuranceBO> insuraceDetail = _insuranceDao.getDetailById(11);
		model.put("insuraceDetail", insuraceDetail.get(0));
    	return "insurance/index";
    }
}