package com.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manager.entities.TblUser;

@Repository
public interface TblUserRepository extends JpaRepository<TblUser, Integer>, TblUserRepositoryCustom {

}
