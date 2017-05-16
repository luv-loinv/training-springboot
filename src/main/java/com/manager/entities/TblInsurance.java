package com.manager.entities;

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
public class TblInsurance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "insurance_internal_id")
	private int insurance_internal_id;

	@NotNull
	@Column(name = "insurance_number")
	private String insuranceNumber;

	@NotNull
	@Column(name = "insurance_start_date")
	private Date insurance_start_date;

	@NotNull
	@Column(name = "insurance_end_date")
	private Date insurance_end_date;

	@NotNull
	@Column(name = "place_of_register")
	private String placeOfRegister;

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
	 * @return the insurance_number
	 */
	public String getInsurance_number() {
		return insuranceNumber;
	}

	/**
	 * @param insurance_number
	 *            the insurance_number to set
	 */
	public void setInsurance_number(String insurance_number) {
		this.insuranceNumber = insurance_number;
	}

	/**
	 * @return the insurance_start_date
	 */
	public Date getInsurance_start_date() {
		return insurance_start_date;
	}

	/**
	 * @param insurance_start_date
	 *            the insurance_start_date to set
	 */
	public void setInsurance_start_date(Date insurance_start_date) {
		this.insurance_start_date = insurance_start_date;
	}

	/**
	 * @return the insurance_end_date
	 */
	public Date getInsurance_end_date() {
		return insurance_end_date;
	}

	/**
	 * @param insurance_end_date
	 *            the insurance_end_date to set
	 */
	public void setInsurance_end_date(Date insurance_end_date) {
		this.insurance_end_date = insurance_end_date;
	}

	/**
	 * @return the place_of_register
	 */
	public String getPlace_of_register() {
		return placeOfRegister;
	}

	/**
	 * @param place_of_register
	 *            the place_of_register to set
	 */
	public void setPlace_of_register(String place_of_register) {
		this.placeOfRegister = place_of_register;
	}

}
