package com.spring.user.service;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.company.entity.TblCompany;
import com.spring.insurance.entity.TblInsurance;
import com.spring.user.entity.TblUser;
import com.spring.user.form.UserForm;
import com.spring.user.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	public TblUser saveUser(UserForm userForm, TblCompany company, TblInsurance insurance) throws ParseException {
		TblUser user = new TblUser();
		user.setUserFullName(userForm.getUserFullName());
    	user.setUserName(userForm.getUserFullName());
    	user.setPassword("");
    	user.setUserSexDivision(userForm.getUserSexDivision());
    	String birthdateString = userForm.getBirthdate();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	user.setBirthdate(df.parse(birthdateString));
    	user.setTblCompany(company);
    	user.setTblInsurance(insurance);
    	
    	userRepo.saveAndFlush(user);
		return user;
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
}
