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

	public static List<String> validateUser(UserInfor userInfor, String company) {
		List<String> errorList = new ArrayList<String>();
		// validate ma so the bao hiem
		if (!Common.checkInput(userInfor.getInsurance_number())) {
			errorList.add(MessageProperties.getMSS("ERR05"));
		} else if (Common.checkExistIns(userInfor.getInsurance_number())) {
			errorList.add(MessageProperties.getMSS("ERR12"));
		} else if (!Common.checkMaxLength(userInfor.getInsurance_number(), 10)) {
			errorList.add(MessageProperties.getMSS("ERR22"));
		}
		// validate ho va ten
		if (!Common.checkInput(userInfor.getUser_full_name())) {
			errorList.add(MessageProperties.getMSS("ERR06"));
		} else if (!Common.checkMaxLength(userInfor.getUser_full_name(), 50)) {
			errorList.add(MessageProperties.getMSS("ERR17"));
		}
		// validate ngay sinh
		if (!Common.checkFormatDate(userInfor.getBirthdateInput())) {
			errorList.add(MessageProperties.getMSS("ERR13"));
		}

		if (!company.equals("com1")) {
			// validate ten cong ty
			if (!Common.checkInput(userInfor.getCompanyName())) {
				errorList.add(MessageProperties.getMSS("ERR07"));
			} else if (!Common.checkMaxLength(userInfor.getCompanyName(), 50)) {
				errorList.add(MessageProperties.getMSS("ERR18"));
			}

			// validate dia chi
			if (!Common.checkInput(userInfor.getAdress())) {
				errorList.add(MessageProperties.getMSS("ERR08"));
			} else if (!Common.checkMaxLength(userInfor.getAdress(), 100)) {
				errorList.add(MessageProperties.getMSS("ERR19"));
			}
			// validate email
			if (!Common.checkMailFormat(userInfor.getEmail())) {
				errorList.add(MessageProperties.getMSS("ERR16"));
			} else if (!Common.checkMaxLength(userInfor.getEmail(), 50)) {
				errorList.add(MessageProperties.getMSS("ERR20"));
			}

			// validate so dien thoai
			if (!Common.checkMaxLength(userInfor.getTelephone(), 15)) {
				errorList.add(MessageProperties.getMSS("ERR21"));
			}
		}

		// validate noi dang ky KCB
		if (!Common.checkInput(userInfor.getPlace_of_register())) {
			errorList.add(MessageProperties.getMSS("ERR09"));
		} else if (!Common.checkMaxLength(userInfor.getPlace_of_register(), 50)) {
			errorList.add(MessageProperties.getMSS("ERR23"));
		}
		// validate ngay bat dau
		if (!Common.checkInput(userInfor.getInsurance_number())) {
			errorList.add(MessageProperties.getMSS("ERR10"));
		} else if (!Common.checkFormatDate(userInfor.getStartDateInput())) {
			errorList.add(MessageProperties.getMSS("ERR14"));
		}

		// validate ngay ket thuc
		if (!Common.checkInput(userInfor.getInsurance_number())) {
			errorList.add(MessageProperties.getMSS("ERR11"));
		} else if (!Common.checkFormatDate(userInfor.getEndDateInput())) {
			errorList.add(MessageProperties.getMSS("ERR15"));
		}

		return errorList;
	}
}
