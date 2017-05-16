package com.spring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "tbl_insurance")
public class TblInsuance {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="insurance_internal_id")
	@NotNull
    private int insuranceInternalId;
	
	@Column(name="insurance_number")
	@NotNull
    private String insuranceNumber;
	
	@Column(name="insurance_start_date")
	@NotNull
    private Date insuranceStartDate;
	
	@Column(name="insurance_end_date")
	@NotNull
    private Date insuranceEndDate;
	
	@Column(name="place_of_register")
	@NotNull
    private String placeOfRegister;

	public int getInsuranceInternalId() {
		return insuranceInternalId;
	}

	public void setInsuranceInternalId(int insuranceInternalId) {
		this.insuranceInternalId = insuranceInternalId;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public Date getInsuranceStartDate() {
		return insuranceStartDate;
	}

	public void setInsuranceStartDate(Date insuranceStartDate) {
		this.insuranceStartDate = insuranceStartDate;
	}

	public Date getInsuranceEndDate() {
		return insuranceEndDate;
	}

	public void setInsuranceEndDate(Date insuranceEndDate) {
		this.insuranceEndDate = insuranceEndDate;
	}

	public String getPlaceOfRegister() {
		return placeOfRegister;
	}

	public void setPlaceOfRegister(String placeOfRegister) {
		this.placeOfRegister = placeOfRegister;
	}

	
	
}