package com.manager.controllers;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.manager.entities.ObjectSession;
import com.manager.entities.TblCompany;
import com.manager.entities.TblInsurance;
import com.manager.entities.TblUser;
import com.manager.logics.TblCompanyLogic;
import com.manager.logics.TblInsuranceLogic;
import com.manager.logics.TblUserLogic;
import com.manager.utils.Common;
import com.manager.utils.Constant;
import com.manager.utils.Validator;

@Controller("/addUser")
public class AddUserController {
	@Autowired
	TblUserLogic userLogic;

	@Autowired
	TblCompanyLogic companyLogic;

	@Autowired
	TblInsuranceLogic insLogic;

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String addUser(Model model) {
		try {
			List<TblCompany> companyList = companyLogic.findAll();
			model.addAttribute("listCompany", companyList);
			ObjectSession userInfor = new ObjectSession(1, "", "", "", "", "", "", "", "", "", "", "1");
			model.addAttribute("userInfor", userInfor);
			return Constant.MH04;
		} catch (Exception e) {
			return Constant.SYSTEM_ERROR;
		}
	}

	/**
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUserSuccess(Model model, @RequestParam Map<String, String> map) {
		try {
			model.addAttribute("listCompany", companyLogic.findAll());

			String insuranceNumber = map.get("insuranceNumber");
			String fullName = map.get("fullName");
			String sexDivision = map.get("gioitinh");
			String birthdate = map.get("birthdate");
			String company = map.get("company");
			if (company == null) {
				company = "";
			}

			String companyName = map.get("companyName");
			String address = map.get("address");
			String email = map.get("email");
			String telephone = map.get("phone");

			String place = map.get("place");
			String startDate = map.get("startDate");
			String endDate = map.get("endDate");

			int companyID = Integer.parseInt(map.get("companySelected"));
			ObjectSession userInfor = new ObjectSession(companyID, fullName, insuranceNumber, place, startDate, endDate,
					birthdate, address, email, telephone, companyName, sexDivision);
			List<String> errorList = Validator.validateUser(userInfor, company, companyLogic, insLogic);
			if (errorList.size() > 0) {
				model.addAttribute("errorList", errorList);
				model.addAttribute("userInfor", userInfor);
				model.addAttribute("companyChose", company);
				return Constant.MH04;
			} else {
				TblInsurance newInsurance = new TblInsurance(insuranceNumber, Common.convertToDate(startDate),
						Common.convertToDate(endDate), place);
				TblUser newUser = new TblUser("", "", Common.formatText(fullName), sexDivision,
						Common.convertToDate(birthdate));

				if (company.equals("com1")) {
					newUser.setTblCompany(companyLogic.findByCompanyID(companyID));
					newUser.setTblInsurance(newInsurance);
					insLogic.saveInsurance(newInsurance);
					userLogic.saveUser(newUser);
				} else {
					TblCompany newCompany = new TblCompany(companyName, address, email, telephone);
					companyLogic.saveCompany(newCompany);
					insLogic.saveInsurance(newInsurance);
					newUser.setTblCompany(newCompany);
					newUser.setTblInsurance(newInsurance);
					userLogic.saveUser(newUser);
				}
			}
			return "redirect:listUser";
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}
	}
}
