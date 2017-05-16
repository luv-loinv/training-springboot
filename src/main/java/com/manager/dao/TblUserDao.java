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

	List<TblUser> findByUsernameAndPassword(String username, String password);

	List<TblUser> findByUserFullName(String user_full_name);
}
