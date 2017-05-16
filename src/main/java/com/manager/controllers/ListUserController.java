/**
 * 
 */
package com.manager.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.manager.entities.UserInfor;
import com.manager.logics.TblCompanyLogic;
import com.manager.logics.TblInsuranceLogic;
import com.manager.logics.TblUserLogic;
import com.manager.logics.UserInforLogic;
import com.manager.utils.Common;
import com.manager.utils.Constant;
import com.manager.utils.MessageProperties;

/**
 * @author komkom
 *
 */
@Controller("/listUser")
public class ListUserController {
	@Autowired
	private HttpSession session;

	@Autowired
	private TblUserLogic userLogic;

	@Autowired
	private TblCompanyLogic companyLogic;

	@Autowired
	private TblInsuranceLogic insuranceLogic;

	@Autowired
	private UserInforLogic userInforLogic;

	@RequestMapping(value = { "/listUser" }, method = RequestMethod.GET)
	public String loginPage(Model model, @RequestParam Map<String, String> mapParam) {
		model.addAttribute("listCompany", companyLogic.findAll());
		List<UserInfor> listUserInf = userInforLogic.getListUserInfor("", "", "", "", "", Constant.OFFSET,
				Constant.LIMIT);
		model.addAttribute("listUserInfor", listUserInf);
		model.addAttribute("listpaging", Common.getListPaging(listUserInf.size(), Constant.LIMIT, 1));
		return "MH02";
	}

	@RequestMapping(value = { "/listUserSearch", "/listUserPaging" }, method = RequestMethod.POST)
	public String listPage(Model model, @RequestParam(value = "companyForm") int companyForm,
			@RequestParam(value = "fullNameForm") String fullnameForm,
			@RequestParam(value = "insNumberForm") String insNumberForm,
			@RequestParam(value = "placeRegForm") String placeRegForm,
			@RequestParam(defaultValue = "", value = "sortBy") String sortByForm,
			@RequestParam Map<String, String> mapParam) {

		model.addAttribute("listCompany", companyLogic.findAll());
		// trường hợp vào MH02
		// các giá trị được set mặc định
		// int companyID = 1;
		// if (session.getAttribute("company") != null) {
		// companyID = (int) session.getAttribute("company");
		// }

		String fullName = (String) session.getAttribute("fullName");
		if (fullName == null) {
			fullName = "";
		}
		String insurancenumber = (String) session.getAttribute("insNumber");
		if (insurancenumber == null) {
			insurancenumber = "";
		}

		String place = (String) session.getAttribute("placeReg");
		if (place == null) {
			place = "";
		}

		String sortType = "username";
		String sortBy = (String) session.getAttribute("sortBy");
		if (sortBy == null) {
			sortBy = "asc";
		}

		int currentPage = 1;
		String type = mapParam.get("type");
		// trường hợp type = search
		if ("search".equals(type)) {
			// companyID = companyForm;
			fullName = fullnameForm;
			insurancenumber = insNumberForm;
			place = placeRegForm;
			// trường hợp type = sort
		} else if ("sort".equals(type)) {
			if ("asc".equals(sortByForm)) {
				sortBy = "desc";
			} else if ("desc".equals(sortByForm)) {
				sortBy = "asc";
			}
		}

		
		// đưa tất cả các giá trị đã set lên session
		session.setAttribute("fullName", fullName);
		// session.setAttribute("company", companyID);
		session.setAttribute("insNumber", insurancenumber);
		session.setAttribute("placeReg", place);
		session.setAttribute("sortBy", sortBy);
		// session.setAttribute("sortType", sortType);
		session.setAttribute("currentPage", currentPage);

		// paging bị lỗi ???
		// => viết lại paging
		
		List<UserInfor> listUserInf = userInforLogic.getListUserInfor(fullName, insurancenumber, place, sortBy,
				sortType, Constant.OFFSET, Constant.LIMIT);
		model.addAttribute("listUserInfor", listUserInf);
		Common.getListPaging(listUserInf.size(), Constant.LIMIT, currentPage);
		model.addAttribute("error", MessageProperties.getMSS("ERR04"));
		return "MH02";
	}

}
