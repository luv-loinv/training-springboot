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

	public List<TblUser> findByUserNameAndPassword(String username, String password) {
		return userDao.findByUserNameAndPassword(username, password);
	}

	public TblUser getUserByID(int userID) {
		return userDao.findOne(userID);
	}

	public void saveUser(TblUser user) {
		userDao.save(user);
	}
}
