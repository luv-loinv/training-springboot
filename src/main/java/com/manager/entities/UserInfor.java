package com.manager.entities;

import java.util.Date;

public class UserInfor {
	private String user_full_name;
	private String insurance_number;
	private String place_of_register;
	private Date insurance_start_date;
	private Date insurance_end_date;
	private char user_sex_division;
	private Date birthdate;

	/**
	 * @param user_full_name
	 * @param insurance_number
	 * @param place_of_register
	 * @param insurance_start_date
	 * @param insurance_end_date
	 * @param user_sex_division
	 * @param birthdate
	 */
	public UserInfor(String user_full_name, String insurance_number, String place_of_register,
			Date insurance_start_date, Date insurance_end_date, char user_sex_division, Date birthdate) {
		this.user_full_name = user_full_name;
		this.insurance_number = insurance_number;
		this.place_of_register = place_of_register;
		this.insurance_start_date = insurance_start_date;
		this.insurance_end_date = insurance_end_date;
		this.user_sex_division = user_sex_division;
		this.birthdate = birthdate;
	}

	/**
	 * @return the user_full_name
	 */
	public String getUser_full_name() {
		return user_full_name;
	}

	/**
	 * @param user_full_name
	 *            the user_full_name to set
	 */
	public void setUser_full_name(String user_full_name) {
		this.user_full_name = user_full_name;
	}

	/**
	 * @return the insurance_number
	 */
	public String getInsurance_number() {
		return insurance_number;
	}

	/**
	 * @param insurance_number
	 *            the insurance_number to set
	 */
	public void setInsurance_number(String insurance_number) {
		this.insurance_number = insurance_number;
	}

	/**
	 * @return the place_of_register
	 */
	public String getPlace_of_register() {
		return place_of_register;
	}

	/**
	 * @param place_of_register
	 *            the place_of_register to set
	 */
	public void setPlace_of_register(String place_of_register) {
		this.place_of_register = place_of_register;
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
