package com.manager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author nguyenthanhtung
 *
 */
@Entity
@Table(name = "tbl_company")
public class TblCompany {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_internal_id")
	private int company_internal_id;

	@NotNull
	@Column(name = "company_name")
	private String companyName;

	@NotNull
	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "telephone")
	private String telephone;

	// private List<TblUser> listUser;
	/**
	 * 
	 */
	public TblCompany() {
		super();
	}

	/**
	 * @param company_name
	 * @param adress
	 * @param email
	 * @param telephone
	 */
	public TblCompany(String companyName, String address, String email, String telephone) {
		this.companyName = companyName;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
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
	 * @return the company_name
	 */
	public String getCompany_name() {
		return companyName;
	}

	/**
	 * @param company_name
	 *            the company_name to set
	 */
	public void setCompany_name(String company_name) {
		this.companyName = company_name;
	}

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return address;
	}

	/**
	 * @param adress
	 *            the address to set
	 */
	public void setAdress(String adress) {
		this.address = adress;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
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
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	// /**
	// * @return the listUser
	// */
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	// public List<TblUser> getListUser() {
	// return listUser;
	// }
	//
	// /**
	// * @param listUser
	// * the listUser to set
	// */
	// public void setListUser(List<TblUser> listUser) {
	// this.listUser = listUser;
	// }

}
