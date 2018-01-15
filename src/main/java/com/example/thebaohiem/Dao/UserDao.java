package com.example.thebaohiem.Dao;

import com.example.thebaohiem.Dao.custom.UserDaoCustom;
import com.example.thebaohiem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer>  {

     User findByUserNameAndPassword(String userName , String password);
}
