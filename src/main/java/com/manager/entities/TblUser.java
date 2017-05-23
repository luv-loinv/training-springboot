/**
 * Copyright(C) 2016 Luvina Software Company
 * TblUser.java, Aug 29, 2016, nguyenvietloi
 */
package com.manager.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity mapping bảng tbl_user trong db
 * 
 * @author nguyenvietloi
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_user")
public class TblUser implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_internal_id")
	private int userInternalId;

	@Column(name = "username", nullable = false)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "user_full_name", nullable = false)
	private String userFullName;

	@Column(name = "user_sex_division", nullable = false)
	private String userSexDivision;

	@Column(name = "birthdate")
	private Date birthdate;

	@ManyToOne()
	@JoinColumn(name = "company_internal_id")
	private TblCompany tblCompany;

	@OneToOne()
	@JoinColumn(name = "insurance_internal_id")
	private TblInsurance tblInsurance;

	/**
	 * Default Constructor
	 */
	public TblUser() {
	}

	/**
	 * Constructor chứa tham số *
	 * 
	 * @param userName,
	 *            Tên đăng nhập
	 * @param password,
	 *            Mật khẩu đăng nhập
	 * @param userFullName,
	 *            Tên người sử dụng
	 * @param userSexDivision,
	 *            Giới tính
	 * @param birthdate,
	 *            Ngày sinh
	 * @param tblCompany
	 *            đối tượng TblCompany
	 * @param tblInsurance
	 *            đối tượng TblInsurance
	 */
	public TblUser(int userInternalId, String userName, String password, String userFullName, String userSexDivision,
			Date birthdate, TblCompany tblCompany, TblInsurance tblInsurance) {
		this.userInternalId = userInternalId;
		this.userName = userName;
		this.password = password;
		this.userFullName = userFullName;
		this.userSexDivision = userSexDivision;
		this.birthdate = birthdate;
		this.tblCompany = tblCompany;
		this.tblInsurance = tblInsurance;
	}

	/**
	 * @param userName
	 * @param password
	 * @param userFullName
	 * @param userSexDivision
	 * @param birthdate
	 */
	public TblUser(String userName, String password, String userFullName, String userSexDivision, Date birthdate) {
		super();
		this.userName = userName;
		this.password = password;
		this.userFullName = userFullName;
		this.userSexDivision = userSexDivision;
		this.birthdate = birthdate;
	}

	/**
	 * @return the userInternalId
	 */
	public int getUserInternalId() {
		return userInternalId;
	}

	/**
	 * @param userInternalId
	 *            the userInternalId to set
	 */
	public void setUserInternalId(int userInternalId) {
		this.userInternalId = userInternalId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the userFullName
	 */
	public String getUserFullName() {
		return userFullName;
	}

	/**
	 * @param userFullName
	 *            the userFullName to set
	 */
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	/**
	 * @return the userSexDivision
	 */
	public String getUserSexDivision() {
		return userSexDivision;
	}

	/**
	 * @param userSexDivision
	 *            the userSexDivision to set
	 */
	public void setUserSexDivision(String userSexDivision) {
		this.userSexDivision = userSexDivision;
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

	/**
	 * @return the tblCompany
	 */
	public TblCompany getTblCompany() {
		return tblCompany;
	}

	/**
	 * @param tblCompany
	 *            the tblCompany to set
	 */
	public void setTblCompany(TblCompany tblCompany) {
		this.tblCompany = tblCompany;
	}

	/**
	 * @return the tblInsurance
	 */
	public TblInsurance getTblInsurance() {
		return tblInsurance;
	}

	/**
	 * @param tblInsurance
	 *            the tblInsurance to set
	 */
	public void setTblInsurance(TblInsurance tblInsurance) {
		this.tblInsurance = tblInsurance;
	}

}
