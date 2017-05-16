package com.spring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.TblUser;

@Transactional
@Repository
public interface LoginDao extends JpaRepository<TblUser, Integer> {

	List<TblUser> findByUsernameAndPassword(String username, String password);
}
