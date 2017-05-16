package com.manager.entities;

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
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "company_internal_id")
	private int company_internal_id;

	@NotNull
	@Column(name = "company_name")
	private String company_name;

	@NotNull
	@Column(name = "adress")
	private String adress;

	@Column(name = "email")
	private String email;

	@Column(name = "telephone")
	private String telephone;

	// private List<TblUser> listUser;

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
		return company_name;
	}

	/**
	 * @param company_name
	 *            the company_name to set
	 */
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * @param adress
	 *            the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
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
