/**
 * 
 */
package com.manager.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	private TblCompanyLogic companyLogic;

	@Autowired
	private UserInforLogic userInforLogic;

	/**
	 * 
	 * @param model
	 * @param mapParam
	 * @return
	 */
	@RequestMapping(value = { "/listUser" }, method = RequestMethod.GET)
	public String loginPage(Model model, @RequestParam Map<String, String> mapParam) {
		model.addAttribute("listCompany", companyLogic.findAll());
		List<UserInfor> listUserInf = userInforLogic.getListUserInfor("", "", "");
		List<UserInfor> listUF = new ArrayList<UserInfor>();
		Collections.sort(listUserInf, new Comparator<UserInfor>() {
			@Override
			public int compare(UserInfor o1, UserInfor o2) {
				return o1.getUser_full_name().compareTo(o2.getUser_full_name());
			}
		});
		for (int i = 0; i <= Constant.LIMIT - 1; i++) {
			UserInfor userInf = listUserInf.get(i);
			listUF.add(userInf);
		}

		session.setAttribute("sortBy", "asc");
		session.setAttribute("listUserInfor", listUF);
		int totalUser = listUserInf.size();
		int limit = Common.getLimit();
		int totalPage = Common.getTotalPage(totalUser, limit);
		session.setAttribute("totalPage", totalPage);
		session.setAttribute("listUF", listUserInf);
		session.setAttribute("currentPage", 1);
		session.setAttribute("endPoint", Common.endPoint);
		session.setAttribute("listpaging", Common.getListPaging(listUserInf.size(), Constant.LIMIT, 1));
		return "MH02";
	}

	/**
	 * 
	 * @param model
	 * @param fullnameForm
	 * @param insNumberForm
	 * @param placeRegForm
	 * @param map
	 * @return
	 */
	@RequestMapping(value = { "/listUserSearch" }, method = RequestMethod.GET)
	public String listPage(Model model, @RequestParam(value = "fullNameForm") String fullnameForm,
			@RequestParam(value = "insNumberForm") String insNumberForm,
			@RequestParam(value = "placeRegForm") String placeRegForm, @RequestParam Map<String, String> map) {

		model.addAttribute("listCompany", companyLogic.findAll());
		String companyIDSearch = map.get("companyForm");
		session.setAttribute("companyIDSearch", companyIDSearch);
		int currentPage = 1;
		session.setAttribute("fullName", fullnameForm);
		session.setAttribute("insNumber", insNumberForm);
		session.setAttribute("placeReg", placeRegForm);
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("endPoint", Common.endPoint);
		session.setAttribute("sortBy", "asc");
		List<UserInfor> listUserInf = userInforLogic.getListUserInfor(fullnameForm, insNumberForm, placeRegForm);
		Collections.sort(listUserInf, new Comparator<UserInfor>() {
			@Override
			public int compare(UserInfor o1, UserInfor o2) {
				return o1.getUser_full_name().compareTo(o2.getUser_full_name());
			}
		});
		int size = listUserInf.size();
		int totalPage = Common.getTotalPage(size, Constant.LIMIT);
		session.setAttribute("totalPage", totalPage);
		session.setAttribute("sizePg", size);
		session.setAttribute("listUF", listUserInf);
		List<UserInfor> listUF = new ArrayList<UserInfor>();
		for (int i = 0; i <= Constant.LIMIT - 1; i++) {
			try {
				UserInfor userInf = listUserInf.get(i);
				listUF.add(userInf);
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
		session.setAttribute("listUserInfor", listUF);
		session.setAttribute("listpaging", Common.getListPaging(size, Constant.LIMIT, currentPage));
		model.addAttribute("error", MessageProperties.getMSS("ERR04"));
		return "MH02";
	}

	/**
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listUserSort", method = RequestMethod.GET)
	public String sort(Model model, @RequestParam Map<String, String> map) {
		model.addAttribute("listCompany", companyLogic.findAll());
		session.setAttribute("currentPage", map.get("currentPage"));
		session.setAttribute("currentPage", 1);
		List<UserInfor> listUserInfor = (List<UserInfor>) session.getAttribute("listUF");
		// String sortBy = map.get("sortBy");
		String sortBy = (String) session.getAttribute("sortBy");
		if ("asc".equals(sortBy)) {
			sortBy = "desc";
			Collections.sort(listUserInfor, new Comparator<UserInfor>() {
				@Override
				public int compare(UserInfor o1, UserInfor o2) {
					return o2.getUser_full_name().compareTo(o1.getUser_full_name());
				}
			});
		} else if ("desc".equals(sortBy)) {
			sortBy = "asc";
			Collections.sort(listUserInfor, new Comparator<UserInfor>() {
				@Override
				public int compare(UserInfor o1, UserInfor o2) {
					return o1.getUser_full_name().compareTo(o2.getUser_full_name());
				}
			});
		}
		List<UserInfor> listUF = new ArrayList<UserInfor>();
		for (int i = 0; i <= Constant.LIMIT - 1; i++) {
			try {
				UserInfor userInf = listUserInfor.get(i);
				listUF.add(userInf);
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
		int currentPage = (int) session.getAttribute("currentPage");
		int size = listUserInfor.size();
		int totalPage = Common.getTotalPage(size, Constant.LIMIT);
		session.setAttribute("totalPage", totalPage);
		session.setAttribute("listUserInfor", listUF);
		session.setAttribute("companyIDSearch", 1);
		session.setAttribute("listpaging", Common.getListPaging(size, Constant.LIMIT, currentPage));
		session.setAttribute("sortBy", sortBy);
		return "MH02";
	}

	/**
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listUserPaging", method = RequestMethod.GET)
	public String getPaging(Model model, @RequestParam Map<String, String> map) {
		model.addAttribute("listCompany", companyLogic.findAll());
		List<UserInfor> listUserInfor = (List<UserInfor>) session.getAttribute("listUF");

		List<UserInfor> listUF = new ArrayList<UserInfor>();
		int totalUser = listUserInfor.size();
		int limit = Common.getLimit();
		int crrPage = Integer.parseInt(map.get("page"));
		int totalPage = Common.getTotalPage(totalUser, limit);
		session.setAttribute("totalPage", totalPage);
		int offset = Common.getOffset(crrPage, limit);
		session.setAttribute("offset", offset);
		List<Integer> listPaging = Common.getListPaging(totalUser, limit, crrPage);
		session.setAttribute("listpaging", listPaging);
		session.setAttribute("endPoint", Common.endPoint);
		session.setAttribute("currentPage", crrPage);

		for (int i = offset; i <= offset + Constant.LIMIT - 1; i++) {
			try {
				UserInfor userInf = listUserInfor.get(i);
				listUF.add(userInf);
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
		session.setAttribute("listUserInfor", listUF);
		return "MH02";
	}
}
