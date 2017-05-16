package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "tbl_company")
public class TblCompany {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="company_internal_id")
	@NotNull
    private int companyInternalId;
	
	@Column(name="company_name")
	@NotNull
    private String companyName;
	
	@Column(name="address")
	@NotNull
    private String address;
	
	@Column(name="email")
	@NotNull
    private String email;
	
	@Column(name="telephone")
	@NotNull
    private String telephone;

	public int getCompanyInternalId() {
		return companyInternalId;
	}

	public void setCompanyInternalId(int companyInternalId) {
		this.companyInternalId = companyInternalId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	} 
	
	
	
}