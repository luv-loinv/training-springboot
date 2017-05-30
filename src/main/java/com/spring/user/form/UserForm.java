package com.spring.user.form;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.assertj.core.util.CheckReturnValue;
import org.hibernate.annotations.Check;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.spring.validator.ValidExists;

public class UserForm {
	@NotNull(message = "{NotEmpty.formSave.insuranceNumber}")
	@Pattern(regexp="^\\d{10}$", message="{NotDigit.userForm.insuranceNumber}")
	@ValidExists(tableNames = "tbl_insurance", columnNames = "insurance_number", message = "{Existed.userForm.insuranceNumber}")
	private String insuranceNumber;

	@NotNull(message="{userFullName.notempty}")
	@Size(max=50, message="{MaxLength.userForm.fullName}")
	private String userFullName;

	private String userSexDivision;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	private String birthdate;

	private String isCompany;

	private String companyId;

	@NotBlank(message="{NotEmpty.userForm.companyName}")
	@Size(max=50, message="{MaxLength.userForm.companyName}")
	private String companyName;

	@NotBlank(message="{NotEmpty.userForm.companyAddress}")
	@Size(max=100, message="{MaxLength.userForm.companyAddress}")
	private String address;

	@NotBlank(message="{NotFormat.userForm.companyEmail}")
	@Size(max=50, message="{MaxLength.userForm.companyEmail}")
	private String email;

	@Pattern(regexp="^\\d+$", message="{NotFormat.userForm.companyTel}")
	private String telephone;

	@NotNull(message="{NotEmpty.userForm.placeRegister}")
	@Size(max=50, message="{MaxLength.userForm.placeRegister}")
	private String placeOfRegister;

	private String insuranceStartDate;

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