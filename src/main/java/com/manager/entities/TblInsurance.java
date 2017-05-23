/**
 * Copyright(C) 2016 Luvina Software Company *
 * TblInsurance.java, 30-08-2016, nguyenvietloi
 */
package com.manager.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity mapping bảng tbl_insurance trong db
 *
 * @author nguyenvietloi
 */

@SuppressWarnings("serial")
@Entity
@Table(name="tbl_insurance")
public class TblInsurance implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="insurance_internal_id")
	private long insuranceInternalId;
	
	@Column(name="insurance_number", nullable = false)
	private String insuranceNumber;
	
	@Column(name="insurance_start_date", nullable = false)
	private Date insuranceStartDate;
	
	@Column(name="insurance_end_date", nullable = false)
	private Date insuranceEndDate;
	
	@Column(name="place_of_register", nullable = false)
	private String placeOfRegister;
	
	@OneToOne(mappedBy="tblInsurance", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
	private TblUser tblUser;

	/**
	 * Default Constructor
	 */
	public TblInsurance() {
	}

	/**
	 * Constructor chứa tham số
	 * 
	 * @param insuranceNumber Mã số thẻ bảo hiểm
	 * @param insuranceStartDate Kỳ hạn sử dụng_bắt đầu
	 * @param insuranceEndDate Kỳ hạn sử dụng_kết thúc
	 * @param placeOfRegister Nơi đăng ký KCB
	 */
	public TblInsurance(String insuranceNumber, Date insuranceStartDate, Date insuranceEndDate,
			String placeOfRegister) {
		this.insuranceNumber = insuranceNumber;
		this.insuranceStartDate = insuranceStartDate;
		this.insuranceEndDate = insuranceEndDate;
		this.placeOfRegister = placeOfRegister;
	}

	/**
	 * @return the insuranceInternalId
	 */
	public long getInsuranceInternalId() {
		return insuranceInternalId;
	}

	/**
	 * @param insuranceInternalId the insuranceInternalId to set
	 */
	public void setInsuranceInternalId(long insuranceInternalId) {
		this.insuranceInternalId = insuranceInternalId;
	}

	/**
	 * @return the insuranceNumber
	 */
	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	/**
	 * @param insuranceNumber the insuranceNumber to set
	 */
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	/**
	 * @return the insuranceStartDate
	 */
	public Date getInsuranceStartDate() {
		return insuranceStartDate;
	}

	/**
	 * @param insuranceStartDate the insuranceStartDate to set
	 */
	public void setInsuranceStartDate(Date insuranceStartDate) {
		this.insuranceStartDate = insuranceStartDate;
	}

	/**
	 * @return the insuranceEndDate
	 */
	public Date getInsuranceEndDate() {
		return insuranceEndDate;
	}

	/**
	 * @param insuranceEndDate the insuranceEndDate to set
	 */
	public void setInsuranceEndDate(Date insuranceEndDate) {
		this.insuranceEndDate = insuranceEndDate;
	}

	/**
	 * @return the placeOfRegister
	 */
	public String getPlaceOfRegister() {
		return placeOfRegister;
	}

	/**
	 * @param placeOfRegister the placeOfRegister to set
	 */
	public void setPlaceOfRegister(String placeOfRegister) {
		this.placeOfRegister = placeOfRegister;
	}

	/**
	 * @return the tblUser
	 */
	public TblUser getTblUser() {
		return tblUser;
	}

	/**
	 * @param tblUser the tblUser to set
	 */
	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}
		
}
