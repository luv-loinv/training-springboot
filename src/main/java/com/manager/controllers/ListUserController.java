/**
 * 
 */
package com.manager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.manager.entities.TblCompany;
import com.manager.entities.TblUser;
import com.manager.logics.TblCompanyLogic;
import com.manager.logics.TblUserLogic;

/**
 * @author komkom
 *
 */
@Controller("/listUser")
public class ListUserController {
	@Autowired
	private HttpSession session;

	@Autowired
	private TblCompanyLogic companyLogic;

	@Autowired
	private TblUserLogic userLogic;

	@RequestMapping(value = { "/listUser" }, method = RequestMethod.GET)
	public String listPage(Model model, @RequestParam("type") String type, @RequestParam("company") String companyForm,
			@RequestParam("username") String usernameForm, @RequestParam("insNumber") String insNumberForm,
			@RequestParam("placeNumber") String placeRegForm, @RequestParam("sortBy") String sortByForm) {
		model.addAttribute("listCompany", getListCompany());

		// trường hợp vào MH02
		// các giá trị được set mặc định
		String company = (String) session.getAttribute("company");

		String username = (String) session.getAttribute("username");
		if (username == null) {
			username = "";
		}
		String insurance_number = (String) session.getAttribute("insNumber");
		if (insurance_number == null) {
			insurance_number = "";
		}

		String place_of_register = (String) session.getAttribute("placeReg");
		if (place_of_register == null) {
			place_of_register = "";
		}

		String sortType = "username";
		String sortBy = (String) session.getAttribute("sortBy");
		if (sortBy == null) {
			sortBy = "asc";
		}

		int currentPage = 1;

		// trường hợp type = search
		if ("search".equals(type)) {
			company = companyForm;
			username = usernameForm;
			insurance_number = insNumberForm;
			place_of_register = placeRegForm;
			// trường hợp type = sort
		} else if ("sort".equals(type)) {
			if ("asc".equals(sortByForm)) {
				sortBy = "desc";
			} else if ("desc".equals(sortByForm)) {
				sortBy = "asc";
			}
			// trường hợp type = paging
		} else if ("paging".equals(type)) {
			// xử lý phân trang
		}

		// đưa tất cả các giá trị đã set lên session
		session.setAttribute("username", username);
		session.setAttribute("company", company);
		session.setAttribute("insNumber", insurance_number);
		session.setAttribute("placeReg", place_of_register);
		session.setAttribute("sortBy", sortBy);
		// session.setAttribute("sortType", sortType);
		session.setAttribute("currentPage", currentPage);

		// truong hop dau
		// gia tri mac dinh username = ""
		// ma so the = ""
		// noi dang ky = ""
		// mac dinh sort tang dan theo ten trong DB
		// truong hop search
		// giu nguyen username, ma so, noi dang ky
		// truong hop sort
		// gia tri username giu nguyen lay tren session
		// gia tri the bao hiem tren session
		// gia tri noi dang ky tren session
		// dieu kien sort tang giam tuy theo click
		// sortType = username
		// truong hop paging
		// cac dieu kien khac van giu nguyen
		return "MH02";
	}

	public List<TblCompany> getListCompany() {
		return companyLogic.getListCompany();
	}

	public List<TblUser> getListUser() {
		return userLogic.findAll();
	}
}
