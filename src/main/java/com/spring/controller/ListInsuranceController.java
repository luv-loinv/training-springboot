package com.spring.controller;

import java.util.List;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.bean.CompanyBean;
import com.spring.bean.SearchBean;
import com.spring.entity.TblCompany;
import com.spring.logic.InsuranceLogic;


@Controller
public class ListInsuranceController {
	
	private final InsuranceLogic insuranceLogic;
	
	@Autowired
	public ListInsuranceController(InsuranceLogic insuranceLogic) {
		this.insuranceLogic = insuranceLogic;
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public ModelAndView displayList(HttpServletRequest request, HttpServletResponse response, 
			SearchBean searchBean , CompanyBean companyBean  ) {
		ModelAndView model = new ModelAndView("listInsurance/listIsurance");
		model.addObject("searchBean", searchBean);
		//model.addObject("companyBean", companyBean);
		initModelList(model);
		return model;
		
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public ModelAndView executeList(HttpServletRequest request, HttpServletResponse response, SearchBean searchBean) {
		ModelAndView model = new ModelAndView("listInsurance/listIsurance");
		model = new ModelAndView("listInsurance/listIsurance");
		model.addObject("searchBean", searchBean);
		return model;
	}
	
    private void initModelList(ModelAndView model) {
    	List<TblCompany> listCompany = insuranceLogic.findAll();
    	
    	/*System.out.println(listCompany);
        Map< String, String > company = new HashMap<String, String>();
        company.put("1", "red");
        company.put("2", "green");*/
        model.addObject("company", listCompany);

    }
}