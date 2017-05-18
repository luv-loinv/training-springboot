package com.manager.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/detailUser")
public class DetailUserController {

	@RequestMapping("/detailUser")
	public String showDetailUser(Model model, @RequestParam Map<String, String> map) {
		System.out.println("ID = " + map.get("userID"));
		return "MH04";

	}
}
