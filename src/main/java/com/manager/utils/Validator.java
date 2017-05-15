package com.manager.utils;

import java.util.ArrayList;
import java.util.List;

import com.manager.entities.TblUser;
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
		if (!"".equals(username) && !"".equals(password) && listUser.size() == 0) {
			listErr.add(MessageProperties.getMSS("ERR03"));
		}

		return listErr;
	}
}
