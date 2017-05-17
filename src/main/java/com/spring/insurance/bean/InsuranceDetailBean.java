package com.spring.insurance.bean;

public class InsuranceDetailBean {
	private int insurance_internal_id;
	private String insurance_number;
	private String insurance_start_date;
	private String insurance_end_date;
	private String place_of_register;
	public InsuranceDetailBean(int insurance_internal_id, String insurance_number, String insurance_start_date,
			String insurance_end_date, String place_of_register) {
		this.insurance_internal_id = insurance_internal_id;
		this.insurance_number = insurance_number;
		this.insurance_start_date = insurance_start_date;
		this.insurance_end_date = insurance_end_date;
		this.place_of_register = place_of_register;
	}
	public int getInsurance_internal_id() {
		return insurance_internal_id;
	}
	public void setInsurance_internal_id(int insurance_internal_id) {
		this.insurance_internal_id = insurance_internal_id;
	}
	public String getInsurance_number() {
		return insurance_number;
	}
	public void setInsurance_number(String insurance_number) {
		this.insurance_number = insurance_number;
	}
	public String getInsurance_start_date() {
		return insurance_start_date;
	}
	public void setInsurance_start_date(String insurance_start_date) {
		this.insurance_start_date = insurance_start_date;
	}
	public String getInsurance_end_date() {
		return insurance_end_date;
	}
	public void setInsurance_end_date(String insurance_end_date) {
		this.insurance_end_date = insurance_end_date;
	}
	public String getPlace_of_register() {
		return place_of_register;
	}
	public void setPlace_of_register(String place_of_register) {
		this.place_of_register = place_of_register;
	}
	
} 