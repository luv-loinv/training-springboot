/**
 * 
 */
package com.manager.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manager.entities.TblUser;

@Repository
@Transactional
public interface TblUserDao extends JpaRepository<TblUser, Integer> {
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	List<TblUser> findByUserNameAndPassword(String username, String password);

}
