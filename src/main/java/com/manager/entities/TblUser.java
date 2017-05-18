package com.manager.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author komkom
 *
 */
@Entity
@Table(name = "tbl_user")
public class TblUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "user_internal_id")
	private int user_internal_id;

	@NotNull
	@Column(name = "company_internal_id")
	private int company_internal_id;

	@NotNull
	@Column(name = "insurance_internal_id")
	private int insurance_internal_id;

	@NotNull
	@Column(name = "username")
	private String username;

	@NotNull
	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "user_full_name")
	private String userFullName;

	@NotNull
	@Column(name = "user_sex_division")
	private char user_sex_division;

	@Column(name = "birthdate")
	private Date birthdate;

	/**
	 * 
	 */
	public TblUser() {
		super();
	}

	/**
	 * @param userFullName
	 * @param user_sex_division
	 * @param birthdate
	 */
	public TblUser(String userFullName, char user_sex_division, Date birthdate) {
		super();
		this.userFullName = userFullName;
		this.user_sex_division = user_sex_division;
		this.birthdate = birthdate;
	}

	/**
	 * @return the user_internal_id
	 */
	public int getUser_internal_id() {
		return user_internal_id;
	}

	/**
	 * @param user_internal_id
	 *            the user_internal_id to set
	 */
	public void setUser_internal_id(int user_internal_id) {
		this.user_internal_id = user_internal_id;
	}

	/**
	 * @return the company_internal_id
	 */
	public int getCompany_internal_id() {
		return company_internal_id;
	}

	/**
	 * @param company_internal_id
	 *            the company_internal_id to set
	 */
	public void setCompany_internal_id(int company_internal_id) {
		this.company_internal_id = company_internal_id;
	}

	/**
	 * @return the insurance_internal_id
	 */
	public int getInsurance_internal_id() {
		return insurance_internal_id;
	}

	/**
	 * @param insurance_internal_id
	 *            the insurance_internal_id to set
	 */
	public void setInsurance_internal_id(int insurance_internal_id) {
		this.insurance_internal_id = insurance_internal_id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the user_full_name
	 */
	public String getUser_full_name() {
		return userFullName;
	}

	/**
	 * @param user_full_name
	 *            the user_full_name to set
	 */
	public void setUser_full_name(String user_full_name) {
		this.userFullName = user_full_name;
	}

	/**
	 * @return the user_sex_division
	 */
	public char getUser_sex_division() {
		return user_sex_division;
	}

	/**
	 * @param user_sex_division
	 *            the user_sex_division to set
	 */
	public void setUser_sex_division(char user_sex_division) {
		this.user_sex_division = user_sex_division;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

}
