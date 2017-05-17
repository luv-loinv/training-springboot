package com.manager.utils;

import java.util.ArrayList;
import java.util.List;

import com.manager.entities.TblUser;
import com.manager.entities.UserInfor;
import com.manager.logics.TblUserLogic;

public class Validator {
	// @Autowired(required = true)
	// private static TblUserLogic userLogic;

	public static List<String> validLogin(String username, String password, TblUserLogic userLogic) {
		List<String> listErr = new ArrayList<String>();
		if ("".equals(username)) {
			listErr.add(MessageProperties.getMSS("ERR01"));
			System.out.println(MessageProperties.getMSS("ERR01"));
		}

		if ("".equals(password)) {
			listErr.add(MessageProperties.getMSS("ERR02"));
		}
		List<TblUser> listUser = userLogic.findByUsernameAndPassword(username, password);
		System.out.println(Common.encryptMD5(password));
		if (!"".equals(username) && !"".equals(password) && listUser.size() == 0) {
			listErr.add(MessageProperties.getMSS("ERR03"));
		}

		return listErr;
	}

	public static List<String> validateUser(UserInfor userInfor) {
		List<String> errorList = new ArrayList<String>();
		// validate ma so the bao hiem
		if (!Common.checkInput(userInfor.getInsurance_number())) {
			errorList.add(MessageProperties.getMSS("ERR05"));
		}
		// validate ho va ten
		if (!Common.checkInput(userInfor.getUser_full_name())) {
			errorList.add(MessageProperties.getMSS("ERR06"));
		}
		// validate ngay sinh
		// if(!Common.checkFormatDate(dateInput))
		// validate ten cong ty
		if (!Common.checkInput(userInfor.getCompanyName())) {
			errorList.add(MessageProperties.getMSS("ERR07"));

		}
		// validate dia chi
		if (!Common.checkInput(userInfor.getAdress())) {
			errorList.add(MessageProperties.getMSS("ERR08"));
		}
		// validate email

		// validate noi dang ky KCB
		if (!Common.checkInput(userInfor.getPlace_of_register())) {
			errorList.add("ERR09");
		}
		// validate ngay bat dau
		
		// validate ngay ket thuc
		return errorList;
	}
}
