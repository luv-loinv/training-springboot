package com.manager.utils;

import java.util.ArrayList;
import java.util.List;

import com.manager.entities.ObjectSession;
import com.manager.entities.TblUser;
import com.manager.logics.TblCompanyLogic;
import com.manager.logics.TblInsuranceLogic;
import com.manager.logics.TblUserLogic;

/**
 * 
 * @author nguyenthanhtung
 *
 */
public class Validator {
	/**
	 * Phương thức validate tên đăng nhập và password
	 * 
	 * @param username
	 * @param password
	 * @param userLogic
	 * @return
	 */
	public static List<String> validLogin(String username, String password, TblUserLogic userLogic) {
		List<String> listErr = new ArrayList<String>();
		if ("".equals(username)) {
			listErr.add(MessageProperties.getMSS("ERR01"));
		}

		if ("".equals(password)) {
			listErr.add(MessageProperties.getMSS("ERR02"));
		}
		List<TblUser> listUser = userLogic.findByUserNameAndPassword(username, Common.encryptMD5(password));
		if (!"".equals(username) && !"".equals(password) && listUser.size() == 0) {
			listErr.add(MessageProperties.getMSS("ERR03"));
		}

		return listErr;
	}

	/**
	 * Phương thức validate đối tượng userInfor
	 * 
	 * @param userInfor
	 * @param company
	 * @return
	 */
	public static List<String> validateUser(ObjectSession userInfor, String company, TblCompanyLogic compLogic,
			TblInsuranceLogic insLogic) {
		List<String> errorList = new ArrayList<String>();
		// validate ma so the bao hiem
		if (!Common.checkInput(userInfor.getInsNumber())) {
			errorList.add(MessageProperties.getMSS("ERR05"));
		} else if (!Common.checkMaxLength(userInfor.getInsNumber(), 10)) {
			errorList.add(MessageProperties.getMSS("ERR22"));
		} else if (!insLogic.checkExistIns(userInfor.getInsNumber())) {
			errorList.add(MessageProperties.getMSS("ERR12"));
		}

		// validate ho va ten
		if (!Common.checkInput(userInfor.getFullName())) {
			errorList.add(MessageProperties.getMSS("ERR06"));
		} else if (!Common.checkMaxLength(userInfor.getFullName(), 50)) {
			errorList.add(MessageProperties.getMSS("ERR17"));
		}
		// validate ngay sinh
		if (!userInfor.getBirthdate().equals("") && !Common.checkFormatDate(userInfor.getBirthdate())) {
			errorList.add(MessageProperties.getMSS("ERR13"));
		}

		if (company.equals("")) {
			errorList.add(MessageProperties.getMSS("ERR25"));
		} else if (!company.equals("com1")) {
			// validate ten cong ty
			if (!Common.checkInput(userInfor.getCompanyName())) {
				errorList.add(MessageProperties.getMSS("ERR07"));
			} else if (!Common.checkMaxLength(userInfor.getCompanyName(), 50)) {
				errorList.add(MessageProperties.getMSS("ERR18"));
			} else if (!compLogic.checkExistComp(userInfor.getCompanyName())) {
				errorList.add(MessageProperties.getMSS("ERR24"));
			}

			// validate dia chi
			if (!Common.checkInput(userInfor.getAddress())) {
				errorList.add(MessageProperties.getMSS("ERR08"));
			} else if (!Common.checkMaxLength(userInfor.getAddress(), 100)) {
				errorList.add(MessageProperties.getMSS("ERR19"));
			}
			// validate email
			if (!userInfor.getEmail().equals("") && !Common.checkMailFormat(userInfor.getEmail())) {
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
		if (!Common.checkInput(userInfor.getPlaceReg())) {
			errorList.add(MessageProperties.getMSS("ERR09"));
		} else if (!Common.checkMaxLength(userInfor.getPlaceReg(), 50)) {
			errorList.add(MessageProperties.getMSS("ERR23"));
		}
		// validate ngay bat dau
		if (!Common.checkInput(userInfor.getStartDate())) {
			errorList.add(MessageProperties.getMSS("ERR10"));
		} else if (!Common.checkFormatDate(userInfor.getStartDate())) {
			errorList.add(MessageProperties.getMSS("ERR14"));
		}

		// validate ngay ket thuc
		if (!Common.checkInput(userInfor.getEndDate())) {
			errorList.add(MessageProperties.getMSS("ERR11"));
		} else if (!Common.checkFormatDate(userInfor.getEndDate())) {
			errorList.add(MessageProperties.getMSS("ERR15"));
		}

		return errorList;
	}
}
