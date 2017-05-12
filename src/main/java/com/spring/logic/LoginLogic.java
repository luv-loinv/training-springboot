package com.spring.logic;

import java.sql.SQLException;

import com.spring.dao.LoginDao;

public class LoginLogic {
	
	
	private LoginDao logindao;
	
	public LoginDao getLogindao() {
		return logindao;
	}

	public void setLogindao(LoginDao logindao) {
		this.logindao = logindao;
	}

	public boolean isSucsecssLogic(String userName, String passWord) throws SQLException {
		return logindao.isSucsess(userName, passWord);
	}
}