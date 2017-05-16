package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "tbl_user")
public class TblUser {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_internal_id")
	@NotNull
    private int userInternalId;
	
	@Column(name="company_internal_id")
	@NotNull
    private int companyInternalId;
	
	@Column(name="insurance_internal_id")
	@NotNull
    private int insuranceInternalId;
	
	@Column(name="username")
	@NotNull
    private String username;
	
	@Column(name="password")
	@NotNull
    private String password; 
	
	@Column(name="user_full_name")
    private String userFullName; 
	
	@Column(name="user_sex_division")
    private String userSexDivision; 
	
	@Column(name="birthdate")
    private String birthdate;

	public int getUserInternalId() {
		return userInternalId;
	}

	public void setUserInternalId(int userInternalId) {
		this.userInternalId = userInternalId;
	}

	public int getCompanyInternalId() {
		return companyInternalId;
	}

	public void setCompanyInternalId(int companyInternalId) {
		this.companyInternalId = companyInternalId;
	}

	public int getInsuranceInternalId() {
		return insuranceInternalId;
	}

	public void setInsuranceInternalId(int insuranceInternalId) {
		this.insuranceInternalId = insuranceInternalId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserSexDivision() {
		return userSexDivision;
	}

	public void setUserSexDivision(String userSexDivision) {
		this.userSexDivision = userSexDivision;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	} 
	
	
	
	
}