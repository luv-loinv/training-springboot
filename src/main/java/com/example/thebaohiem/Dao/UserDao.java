package com.example.thebaohiem.Dao;

import com.example.thebaohiem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * connect to tbl_user in database
 * @author lethiha
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer>  {

     /**
      * find User by userName and PassWord
      * @param userName: userName
      * @param password: password
      * @return : List<User></User>
      */
     List<User> findByUserNameAndPassword(String userName , String password);

     /**
      * find User by userInternalId
      * @param userInternalId :userInternalId
      * @return : User
      */
     User findByUserInternalId(int userInternalId);
}
