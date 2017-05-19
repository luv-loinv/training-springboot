package com.manager.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.manager.entities.UserInfor;
import com.manager.logics.UserInforLogic;

@Controller("/detailUser")
public class DetailUserController {

	@Autowired
	UserInforLogic userInforLogic;

	@RequestMapping(value = "/detailUser", method = RequestMethod.GET)
	public String showDetailUser(Model model, @RequestParam Map<String, String> map) {
		try {
			int userID = Integer.parseInt(map.get("userID"));
			UserInfor userInfor = userInforLogic.findUserByID(userID);
			model.addAttribute("userInfor", userInfor);
			return "MH03";
		} catch (Exception e) {
			return "SystemError";
		}
	}
}
