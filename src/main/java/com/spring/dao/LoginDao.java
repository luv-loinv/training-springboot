package com.spring.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
 
@Repository
public class LoginDao extends JdbcDaoSupport {
 
    @Autowired
    public LoginDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
    public boolean isSucsess(String userName, String passWord) throws SQLException {
    	String query = "Select count(1) from tbl_insurance where username = ? and password = ?"; 	
    	
    	List<String> list = this.getJdbcTemplate().queryForList(query, String.class);
    	
    	
		ResultSet resultSet = ((PreparedStatement) list).executeQuery();
		if (resultSet.next())
				return (resultSet.getInt(1) > 0);
		else
				return false;
	}
 
 
}