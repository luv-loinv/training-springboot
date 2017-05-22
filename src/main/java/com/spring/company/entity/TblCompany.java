/**
 * Copyright(C) 2016 Luvina Software Company *
 * TblCompany.java, 30-08-2016, nguyenvietloi
 */
package com.spring.company.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.spring.user.entity.TblUser;

/**
 * Entity mapping bảng tbl_company trong db
 *
 * @author nguyenvietloi
 */

@SuppressWarnings("serial")
@Entity
@Table(name="tbl_company")
public class TblCompany implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="company_internal_id")
	private Long companyInternalId;
	
	@Column(name="company_name", nullable = false)
	private String companyName;
	
	@NotNull
	private String address;
	
	private String email;
	
	private String telephone;
	
	@OneToMany(mappedBy = "tblCompany", orphanRemoval= true,
			cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TblUser> tblUsers;

	/**
	 * Default Constructor
	 */
	public TblCompany() {
	}

	/**
	 * Constructor chứa tham số
	 * 
	 * @param companyName Tên công ty
	 * @param address Địa chỉ
	 * @param email Địa chỉ email
	 * @param telephone Số điện thoại
	 */
	public TblCompany(String companyName, String address, String email, String telephone) {
		this.companyName = companyName;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
	}

	/**
	 * @return the companyInternalId
	 */
	public Long getCompanyInternalId() {
		return companyInternalId;
	}

	/**
	 * @param companyInternalId the companyInternalId to set
	 */
	public void setCompanyInternalId(Long companyInternalId) {
		this.companyInternalId = companyInternalId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	
	/**
	 * @return the tblUsers
	 */
	public Set<TblUser> getTblUsers() {
		return tblUsers;
	}

	/**
	 * @param tblUsers the tblUsers to set
	 */
	public void setTblUsers(Set<TblUser> tblUsers) {
		this.tblUsers = tblUsers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.companyName+","+ this.address +","+  this.email +","+ this.telephone;
	}
}
