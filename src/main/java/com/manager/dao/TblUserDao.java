/**
 * 
 */
package com.manager.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.entities.TblUser;

/**
 * @author komkom
 *
 */
@Transactional
public interface TblUserDao extends JpaRepository<TblUser, Integer> {
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	List<TblUser> findByUsernameAndPassword(String username, String password);

	/**
	 * 
	 * @param user_full_name
	 * @return
	 */
	List<TblUser> findByUserFullName(String user_full_name);
	
	/**
	 * 
	 * @param user_full_name
	 * @return
	 */
	List<TblUser> findByUserFullNameContaining(String user_full_name);

	// List<TblUser> findAllOrderByUserFullNameAsc();
	//
	// List<TblUser> findAllOrderByUserFullNameDesc();
	
}
