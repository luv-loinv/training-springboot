package com.manager.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.manager.dao.TblCompanyDao;
import com.manager.dao.TblInsuranceDao;
import com.manager.entities.TblCompany;
import com.manager.entities.TblInsurance;
import com.manager.entities.TblUser;
import com.manager.entities.UserInfor;
import com.manager.logics.TblCompanyLogic;
import com.manager.logics.UserInforLogic;
import com.manager.utils.Common;
import com.manager.utils.MessageProperties;
import com.manager.utils.Validator;

/**
 * 
 * @author nguyenthanhtung
 *
 */
@Controller("/addUser")
public class AddUserController {

	@Autowired
	private TblCompanyLogic companyLogic;

	@Autowired
	private UserInforLogic userInforLogic;

	@Autowired
	private TblInsuranceDao insDao;

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String addUser(Model model) {
		try {
			List<TblCompany> companyList = companyLogic.findAll();
			model.addAttribute("listCompany", companyList);
			companyList.get(0).getCompany_internal_id();
			UserInfor userInfor = new UserInfor("", "", "", "1".charAt(0), companyList.get(0).getCompany_name(), "", "",
					"", "", "", "", 1);
			model.addAttribute("userInfor", userInfor);
			return "MH04";
		} catch (Exception e) {
			return "SystemError";
		}
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUserSuccess(Model model, @RequestParam Map<String, String> map) {
		try {
			model.addAttribute("listCompany", companyLogic.findAll());

			String insuranceNumber = map.get("insuranceNumber");
			String fullName = map.get("fullName");
			char sexDivision = map.get("gioitinh").charAt(0);
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

			UserInfor userInfor = new UserInfor(fullName, insuranceNumber, place, sexDivision, companyName, address,
					telephone, birthdate, startDate, endDate, email, companyID);

			List<String> errorList = new ArrayList<String>();
			if (insDao.countByInsuranceNumber(insuranceNumber) > 0) {
				errorList.add(MessageProperties.getMSS("ERR12"));
			}

			errorList.addAll(Validator.validateUser(userInfor, company));

//			if (companyDao.findByCompanyName(companyName) != null) {
//				errorList.add(MessageProperties.getMSS("ERR24"));
//			}
			if (errorList.size() > 0) {
				model.addAttribute("errorList", errorList);
				model.addAttribute("userInfor", userInfor);
				model.addAttribute("companyChose", company);
				return "MH04";
			} else {

				fullName = Common.toUpcaseWord(Common.formatLatin(Common.removeAccent(Common.removeSpace(fullName))));

				TblUser newUser = new TblUser(fullName, sexDivision, Common.convertToDate(birthdate));
				newUser.setPassword("");
				newUser.setUsername("");
				TblInsurance newInsurance = new TblInsurance(insuranceNumber, Common.convertToDate(startDate),
						Common.convertToDate(endDate), place);

				if (company.equals("com1")) {
					userInforLogic.insertUser(companyID, newInsurance, newUser);
				} else {
					TblCompany newCompany = new TblCompany(companyName, address, email, telephone);
					userInforLogic.insertUser(newCompany, newInsurance, newUser);
				}
			}
			return "MH02";
		} catch (Exception e) {
			e.printStackTrace();
			return "SystemError";
		}
	}
}
