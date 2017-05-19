package com.manager.logics;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.dao.TblCompanyDao;
import com.manager.dao.TblInsuranceDao;
import com.manager.dao.TblUserDao;
import com.manager.entities.TblCompany;
//import com.manager.dao.TblUserDao;
import com.manager.entities.TblInsurance;
import com.manager.entities.TblUser;
import com.manager.entities.UserInfor;
import com.manager.utils.Common;

@Service
public class UserInforLogic {
	@Autowired
	private TblUserDao userDao;

	@Autowired
	private TblInsuranceDao insuranceDao;

	@Autowired
	private TblCompanyDao companyDao;

	/**
	 * 
	 * @param fullName
	 * @param insurancenumber
	 * @param place
	 * @return
	 */
	public List<UserInfor> getListUserInfor(String fullName, String insurancenumber, String place) {

		List<UserInfor> listUserInfor = new ArrayList<UserInfor>();
		// truong hop moi vao va 3 dieu kien tim kiem rong
		if ("".equals(fullName) && "".equals(insurancenumber) && "".equals(place)) {
			List<TblUser> listUser = userDao.findAll();
			int size = listUser.size();
			for (int i = 0; i < size; i++) {
				TblUser user = listUser.get(i);
				int ID = user.getInsurance_internal_id();
				TblInsurance insurance = insuranceDao.findOne(ID);
				listUserInfor.add(new UserInfor(user.getUser_internal_id(), user.getUser_full_name(),
						insurance.getInsurance_number(), insurance.getPlace_of_register(),
						insurance.getInsurance_start_date(), insurance.getInsurance_end_date(),
						user.getUser_sex_division(), user.getBirthdate()));
			}
			// tim kiem khi biet place
		} else if ("".equals(fullName) && "".equals(insurancenumber)) {
			List<TblInsurance> listInsurance = insuranceDao.findByPlaceOfRegisterContaining(place);
			int sizeIns = listInsurance.size();
			if (sizeIns != 0) {
				List<TblUser> listUser = userDao.findAll();
				int size = listUser.size();
				for (int i = 0; i < size; i++) {
					TblUser user = listUser.get(i);
					for (int j = 0; j < sizeIns; j++) {
						TblInsurance insurance = listInsurance.get(j);
						if (user.getInsurance_internal_id() == insurance.getInsurance_internal_id()) {
							listUserInfor.add(new UserInfor(user.getUser_internal_id(), user.getUser_full_name(),
									insurance.getInsurance_number(), insurance.getPlace_of_register(),
									insurance.getInsurance_start_date(), insurance.getInsurance_end_date(),
									user.getUser_sex_division(), user.getBirthdate()));
						}
					}
				}
			}
			// truong hop khi biet ten
		} else if ("".equals(insurancenumber) && "".equals(place)) {
			List<TblUser> listUser = userDao.findByUserFullNameContaining(fullName);
			int size = listUser.size();
			for (int i = 0; i < size; i++) {
				TblUser user = listUser.get(i);
				int ID = user.getInsurance_internal_id();
				TblInsurance insurance = insuranceDao.findOne(ID);
				if (user.getInsurance_internal_id() == insurance.getInsurance_internal_id()) {
					listUserInfor.add(new UserInfor(user.getUser_internal_id(), user.getUser_full_name(),
							insurance.getInsurance_number(), insurance.getPlace_of_register(),
							insurance.getInsurance_start_date(), insurance.getInsurance_end_date(),
							user.getUser_sex_division(), user.getBirthdate()));
				}
			}
		} else if ("".equals(fullName) && "".equals(place)) {
			List<TblInsurance> listIns = insuranceDao.findByInsuranceNumberContaining(insurancenumber);
			if (listIns != null) {
				List<TblUser> listUser = userDao.findAll();
				int size = listUser.size();
				int sizeIns = listIns.size();
				for (int i = 0; i < size; i++) {
					TblUser user = listUser.get(i);
					for (int j = 0; j < sizeIns; j++) {
						TblInsurance insurance = listIns.get(j);
						if (user.getInsurance_internal_id() == insurance.getInsurance_internal_id()) {
							listUserInfor.add(new UserInfor(user.getUser_internal_id(), user.getUser_full_name(),
									insurance.getInsurance_number(), insurance.getPlace_of_register(),
									insurance.getInsurance_start_date(), insurance.getInsurance_end_date(),
									user.getUser_sex_division(), user.getBirthdate()));
						}
					}
				}
			}
		} else if ("".equals(fullName)) {
			List<TblInsurance> listIns = insuranceDao
					.findByInsuranceNumberContainingAndPlaceOfRegisterContaining(insurancenumber, place);
			if (listIns.size() != 0) {
				List<TblUser> listUser = userDao.findAll();
				int size = listUser.size();
				int sizeIns = listIns.size();
				for (int i = 0; i < size; i++) {
					TblUser user = listUser.get(i);
					for (int j = 0; j < sizeIns; j++) {
						TblInsurance insurance = listIns.get(j);
						if (user.getInsurance_internal_id() == insurance.getInsurance_internal_id()) {
							listUserInfor.add(new UserInfor(user.getUser_internal_id(), user.getUser_full_name(),
									insurance.getInsurance_number(), insurance.getPlace_of_register(),
									insurance.getInsurance_start_date(), insurance.getInsurance_end_date(),
									user.getUser_sex_division(), user.getBirthdate()));
						}
					}

				}
			}
		} else if ("".equals(insurancenumber)) {
			List<TblInsurance> listInsurance = insuranceDao.findByPlaceOfRegisterContaining(place);
			if (listInsurance.size() != 0) {
				List<TblUser> listUser = userDao.findByUserFullNameContaining(fullName);
				int size = listUser.size();
				int sizeIns = listInsurance.size();
				for (int i = 0; i < size; i++) {
					TblUser user = listUser.get(i);
					for (int j = 0; j < sizeIns; j++) {
						TblInsurance insurance = listInsurance.get(j);
						if (user.getInsurance_internal_id() == insurance.getInsurance_internal_id()) {
							listUserInfor.add(new UserInfor(user.getUser_internal_id(), user.getUser_full_name(),
									insurance.getInsurance_number(), insurance.getPlace_of_register(),
									insurance.getInsurance_start_date(), insurance.getInsurance_end_date(),
									user.getUser_sex_division(), user.getBirthdate()));
						}
					}
				}
			}
			// truong hop dieu kien ten+noi dk
		} else if ("".equals(place)) {
			List<TblInsurance> listIns = insuranceDao.findByInsuranceNumberContaining(insurancenumber);
			if (listIns != null) {
				List<TblUser> listUser = userDao.findByUserFullNameContaining(fullName);
				int size = listUser.size();
				int sizeIns = listIns.size();
				for (int i = 0; i < size; i++) {
					TblUser user = listUser.get(i);
					for (int j = 0; j < sizeIns; j++) {
						TblInsurance insurance = listIns.get(j);
						if (user.getInsurance_internal_id() == insurance.getInsurance_internal_id()) {
							listUserInfor.add(new UserInfor(user.getUser_internal_id(), user.getUser_full_name(),
									insurance.getInsurance_number(), insurance.getPlace_of_register(),
									insurance.getInsurance_start_date(), insurance.getInsurance_end_date(),
									user.getUser_sex_division(), user.getBirthdate()));
						}
					}
				}
			}
		} else {
			// truong hop ca 3 deu co gia tri
			List<TblInsurance> listIns = insuranceDao
					.findByInsuranceNumberContainingAndPlaceOfRegisterContaining(insurancenumber, place);
			if (listIns != null) {
				List<TblUser> listUser = userDao.findByUserFullNameContaining(fullName);
				int size = listUser.size();
				int sizeIns = listIns.size();
				for (int i = 0; i < size; i++) {
					TblUser user = listUser.get(i);
					for (int j = 0; j < sizeIns; j++) {
						TblInsurance insurance = listIns.get(j);
						if (user.getInsurance_internal_id() == insurance.getInsurance_internal_id()) {
							listUserInfor.add(new UserInfor(user.getUser_internal_id(), user.getUser_full_name(),
									insurance.getInsurance_number(), insurance.getPlace_of_register(),
									insurance.getInsurance_start_date(), insurance.getInsurance_end_date(),
									user.getUser_sex_division(), user.getBirthdate()));
						}
					}
				}
			}
		}
		return listUserInfor;
	}

	@Transactional
	public void insertUser(TblCompany company, TblInsurance insurance, TblUser user) {
		companyDao.save(company);
		insuranceDao.save(insurance);
		user.setCompany_internal_id(companyDao.findByCompanyName(company.getCompany_name()).getCompany_internal_id());
		user.setInsurance_internal_id(
				insuranceDao.findByInsuranceNumber(insurance.getInsurance_number()).getInsurance_internal_id());
		userDao.save(user);
	}

	@Transactional
	public void insertUser(int companyID, TblInsurance insurance, TblUser user) {
		insuranceDao.save(insurance);
		user.setCompany_internal_id(companyID);
		user.setInsurance_internal_id(
				insuranceDao.findByInsuranceNumber(insurance.getInsurance_number()).getInsurance_internal_id());
		userDao.save(user);
	}

	public UserInfor findUserByID(int userID) {
		TblUser user = userDao.findOne(userID);
		TblCompany company = companyDao.findOne(user.getCompany_internal_id());
		TblInsurance insurance = insuranceDao.findOne(user.getInsurance_internal_id());
		String birthdate = Common.convertDateToString(user.getBirthdate());
		String startDate = Common.convertDateToString(insurance.getInsurance_start_date());
		String endDate = Common.convertDateToString(insurance.getInsurance_end_date());
		return new UserInfor(user.getUser_full_name(), insurance.getInsurance_number(),
				insurance.getPlace_of_register(), user.getUser_sex_division(), company.getCompany_name(), "", "",
				birthdate, startDate, endDate, "", userID);

	}
}
