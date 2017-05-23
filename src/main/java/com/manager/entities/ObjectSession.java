package com.manager.entities;

import java.util.List;

public class ObjectSession {
	// companyID
	// listPaging
	// totalPage
	// currentPage
	// endPoint
	// listUser
	// sortBy
	// fullName
	// insNumber
	// placeReg
	// offset
	private int companyID;
	private List<Integer> listpaging;
	private int totalPage;
	private int currentPage;
	private int endPoint;
	private List<TblUser> listUser;
	private String sortBy;
	private String fullName;
	private String insNumber;
	private String placeReg;
	private int offset;
	private String startDate;
	private String endDate;
	private String birthdate;
	private String address;
	private String email;
	private String telephone;
	private String companyName;
	private String sexDivision;

	/**
	 * 
	 */
	public ObjectSession() {
		super();
	}

	/**
	 * @param companyID
	 * @param fullName
	 * @param insNumber
	 * @param placeReg
	 * @param startDate
	 * @param endDate
	 * @param birthdate
	 * @param address
	 * @param email
	 * @param telephone
	 */
	public ObjectSession(int companyID, String fullName, String insNumber, String placeReg, String startDate,
			String endDate, String birthdate, String address, String email, String telephone, String companyName,
			String sexDivision) {
		super();
		this.companyID = companyID;
		this.fullName = fullName;
		this.insNumber = insNumber;
		this.placeReg = placeReg;
		this.startDate = startDate;
		this.endDate = endDate;
		this.birthdate = birthdate;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
		this.companyName = companyName;
		this.sexDivision = sexDivision;
	}

	/**
	 * @return the companyID
	 */
	public int getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID
	 *            the companyID to set
	 */
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage
	 *            the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the endPoint
	 */
	public int getEndPoint() {
		return endPoint;
	}

	/**
	 * @param endPoint
	 *            the endPoint to set
	 */
	public void setEndPoint(int endPoint) {
		this.endPoint = endPoint;
	}

	/**
	 * @return the listUser
	 */
	public List<TblUser> getListUser() {
		return listUser;
	}

	/**
	 * @param listUser
	 *            the listUser to set
	 */
	public void setListUser(List<TblUser> listUser) {
		this.listUser = listUser;
	}

	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * @param sortBy
	 *            the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the insNumber
	 */
	public String getInsNumber() {
		return insNumber;
	}

	/**
	 * @param insNumber
	 *            the insNumber to set
	 */
	public void setInsNumber(String insNumber) {
		this.insNumber = insNumber;
	}

	/**
	 * @return the placeReg
	 */
	public String getPlaceReg() {
		return placeReg;
	}

	/**
	 * @param placeReg
	 *            the placeReg to set
	 */
	public void setPlaceReg(String placeReg) {
		this.placeReg = placeReg;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the listpaging
	 */
	public List<Integer> getListpaging() {
		return listpaging;
	}

	/**
	 * @param listpaging
	 *            the listpaging to set
	 */
	public void setListpaging(List<Integer> listpaging) {
		this.listpaging = listpaging;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the birthdate
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the sexDivision
	 */
	public String getSexDivision() {
		return sexDivision;
	}

	/**
	 * @param sexDivision
	 *            the sexDivision to set
	 */
	public void setSexDivision(String sexDivision) {
		this.sexDivision = sexDivision;
	}

}
