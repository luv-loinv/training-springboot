package com.manager.logics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.dao.TblUserDao;
import com.manager.entities.TblUser;

/**
 * @author komkom
 *
 */
@Service
public class TblUserLogic {
	@Autowired(required = true)
	private TblUserDao userDao;

	public List<TblUser> findByUsernameAndPassword(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}

	public List<TblUser> findAll() {
		return userDao.findAll();
	}
	
}
