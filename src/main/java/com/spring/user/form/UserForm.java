package com.spring.user.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

public class UserForm {
	@NotNull
	@Pattern(regexp="^\\d{10}$", message="Lỗi nhập sai định dạng tại hạng mục mã số thẻ bảo hiểm")
	private String insuranceNumber;
	@NotNull
	private String userFullName;
	@NotNull
	private String userSexDivision;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private String birthdate;
	private String isCompany;
	private String companyId;
	@NotNull
	private String companyName;
	@NotNull
	private String address;
	private String email;
	private String telephone;
	@NotNull
	private String placeOfRegister;
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private String insuranceStartDate;
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private String insuranceEndDate;

	public String getInsuranceNumber() {
		return insuranceNumber;
	}
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public String getUserSexDivision() {
		return userSexDivision;
	}
	public void setUserSexDivision(String userSexDivision) {
		this.userSexDivision = userSexDivision;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getIsCompany() {
		return isCompany;
	}
	public void setIsCompany(String isCompany) {
		this.isCompany = isCompany;
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
	public String getPlaceOfRegister() {
		return placeOfRegister;
	}
	public void setPlaceOfRegister(String placeOfRegister) {
		this.placeOfRegister = placeOfRegister;
	}
	public String getInsuranceStartDate() {
		return insuranceStartDate;
	}
	public void setInsuranceStartDate(String insuranceStartDate) {
		this.insuranceStartDate = insuranceStartDate;
	}
	public String getInsuranceEndDate() {
		return insuranceEndDate;
	}
	public void setInsuranceEndDate(String insuranceEndDate) {
		this.insuranceEndDate = insuranceEndDate;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
}