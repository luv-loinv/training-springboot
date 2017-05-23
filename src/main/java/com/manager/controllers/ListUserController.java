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

import com.manager.dao.TblUserDao;
import com.manager.entities.ObjectSession;
import com.manager.entities.TblUser;
import com.manager.logics.TblCompanyLogic;
import com.manager.logics.TblUserLogic;
import com.manager.repository.TblUserRepositoryImpl;
import com.manager.utils.Common;
import com.manager.utils.Constant;
import com.manager.utils.MessageProperties;

@Controller("/listUser")
public class ListUserController {
	@Autowired
	HttpSession session;

	@Autowired
	TblUserLogic userLogic;

	@Autowired
	TblUserDao userDao;

	@Autowired
	TblCompanyLogic companyLogic;

	@Autowired
	TblUserRepositoryImpl userRepoImpl;

	ObjectSession objSS = new ObjectSession();
	ObjectSession objGetForm = new ObjectSession();

	@RequestMapping(value = "/listUser", method = RequestMethod.GET)
	public String listUser(Model model, @RequestParam Map<String, String> map) {
		model.addAttribute("listCompany", companyLogic.findAll());
		int companyIDSearch = 1;
		System.out.println(map.get("type"));

		String sortBy = "asc";
		int crrPage = 1;

		int totalUser = userRepoImpl.getTotalUser(companyIDSearch, "", "", "");
		int totalPage = Common.getTotalPage(totalUser, Constant.LIMIT);
		int offset = 0;
		List<TblUser> listUser = userRepoImpl.getListUser(companyIDSearch, "", "", "", sortBy, offset, Constant.LIMIT);
		objSS.setFullName("");
		objSS.setInsNumber("");
		objSS.setPlaceReg("");
		objSS.setCompanyID(companyIDSearch);
		objSS.setListpaging(Common.getListPaging(totalUser, Constant.LIMIT, crrPage));
		objSS.setTotalPage(totalPage);
		objSS.setCurrentPage(crrPage);
		objSS.setEndPoint(Common.endPoint);
		objSS.setListUser(listUser);
		objSS.setSortBy(sortBy);
		session.setAttribute("objSS", objSS);
		return Constant.MH02;
	}

	@RequestMapping(value = "/listUserSearch", method = RequestMethod.GET)
	public String searchUser(@RequestParam Map<String, String> map, Model model) {
		model.addAttribute("listCompany", companyLogic.findAll());
		int companyIDSearch = Integer.parseInt(map.get("companyForm"));
		int crrtPage = 1;
		String sortBy = "asc";
		String fullName = map.get("fullNameForm");
		objSS.setFullName(fullName);
		fullName = Common.escapeSQLInjection(fullName);
		String insNumber = map.get("insNumberForm");
		String placeReg = map.get("placeRegForm");
		objSS.setPlaceReg(placeReg);
		placeReg = Common.escapeSQLInjection(placeReg);
		int totalUser = userRepoImpl.getTotalUser(companyIDSearch, fullName, insNumber, placeReg);
		List<TblUser> listUser = null;
		if (totalUser == 0) {
			model.addAttribute("error", MessageProperties.getMSS("ERR04"));
		} else {
			int totalPage = Common.getTotalPage(totalUser, Constant.LIMIT);
			int offset = Common.getOffset(crrtPage, Constant.LIMIT);
			listUser = userRepoImpl.getListUser(companyIDSearch, fullName, insNumber, placeReg, sortBy, offset,
					Constant.LIMIT);
			objSS.setListpaging(Common.getListPaging(totalUser, Constant.LIMIT, crrtPage));
			objSS.setTotalPage(totalPage);

		}

		objSS.setListUser(listUser);
		objSS.setInsNumber(insNumber);
		objSS.setCompanyID(companyIDSearch);
		objSS.setSortBy(sortBy);
		objSS.setCurrentPage(crrtPage);
		session.setAttribute("objSS", objSS);
		return Constant.MH02;
	}

	@RequestMapping(value = "/listUserSort", method = RequestMethod.GET)
	public String sortUser(@RequestParam Map<String, String> map, Model model) {
		model.addAttribute("listCompany", companyLogic.findAll());
		objGetForm = (ObjectSession) session.getAttribute("objSS");
		String sortBy = objGetForm.getSortBy();
		if ("asc".equals(sortBy)) {
			sortBy = "desc";
		} else if ("desc".equals(sortBy)) {
			sortBy = "asc";
		}
		objGetForm = (ObjectSession) session.getAttribute("objSS");
		String fullName = objGetForm.getFullName();
		String insNumber = objGetForm.getInsNumber();
		String placeReg = objGetForm.getPlaceReg();
		int companyIDSearch = objGetForm.getCompanyID();
		int totalUser = userRepoImpl.getTotalUser(companyIDSearch, fullName, insNumber, placeReg);
		int totalPage = Common.getTotalPage(totalUser, Constant.LIMIT);
		List<TblUser> listUser = userRepoImpl.getListUser(companyIDSearch, fullName, insNumber, placeReg, sortBy, 0,
				Constant.LIMIT);
		objSS.setListUser(listUser);
		objSS.setCurrentPage(1);
		objSS.setTotalPage(totalPage);
		objSS.setCompanyID(companyIDSearch);
		objSS.setListpaging(Common.getListPaging(totalUser, Constant.LIMIT, 1));
		objSS.setSortBy(sortBy);
		session.setAttribute("objSS", objSS);
		return Constant.MH02;
	}

	@RequestMapping(value = "/listUserPaging", method = RequestMethod.GET)
	public String pagingUser(@RequestParam Map<String, String> map, Model model) {
		model.addAttribute("listCompany", companyLogic.findAll());
		int crrPage = Integer.parseInt(map.get("page"));
		objGetForm = (ObjectSession) session.getAttribute("objSS");
		String sortBy = objGetForm.getSortBy();
		String fullName = objGetForm.getFullName();
		String insNumber = objGetForm.getInsNumber();
		String placeReg = objGetForm.getPlaceReg();
		int companyIDSearch = objGetForm.getCompanyID();
		int totalUser = userRepoImpl.getTotalUser(companyIDSearch, fullName, insNumber, placeReg);
		int totalPage = Common.getTotalPage(totalUser, Constant.LIMIT);
		int offset = Common.getOffset(crrPage, Constant.LIMIT);
		List<TblUser> listUser = userRepoImpl.getListUser(companyIDSearch, fullName, insNumber, placeReg, sortBy,
				offset, Constant.LIMIT);

		objSS.setCompanyID(companyIDSearch);
		objSS.setListpaging(Common.getListPaging(totalUser, Constant.LIMIT, crrPage));
		objSS.setListUser(listUser);
		objSS.setOffset(offset);
		objSS.setEndPoint(Common.endPoint);
		objSS.setCurrentPage(crrPage);
		objSS.setTotalPage(totalPage);
		objSS.setCompanyID(companyIDSearch);
		objSS.setFullName(fullName);
		objSS.setInsNumber(insNumber);
		objSS.setPlaceReg(placeReg);
		session.setAttribute("objSS", objSS);
		return Constant.MH02;
	}

}
