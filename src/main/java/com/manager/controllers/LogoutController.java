/**
 * 
 */
package com.manager.controllers;

import org.springframework.web.bind.annotation.RequestMethod;

import com.manager.utils.Constant;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author komkom
 *
 */
@Controller("/logout")
public class LogoutController {
	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		try {
			session.invalidate();
			return Constant.MH01;
		} catch (Exception e) {
			return Constant.SYSTEM_ERROR;
		}
	}
}
