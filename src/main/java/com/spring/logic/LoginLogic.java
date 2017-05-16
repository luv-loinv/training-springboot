package com.spring.logic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.LoginDao;
import com.spring.entity.TblUser;

@Service
public class LoginLogic {
	
	@Autowired
	private LoginDao logindao;
	
	public LoginLogic(LoginDao logindao) {
		this.logindao = logindao;
	}
	
	public List<TblUser> findByUsernameAndPassword(String username, String password) {
		return logindao.findByUsernameAndPassword(username, password);
	}
	
	public String md5(String str){
		String result = "";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(str.getBytes());
			BigInteger bigInteger = new BigInteger(1,digest.digest());
			result = bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	
//public interface LoginLogicJPA extends JpaRepository<T, Serializable> {}
}