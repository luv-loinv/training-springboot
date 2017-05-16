package com.manager.logics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.dao.TblInsuranceDao;
import com.manager.dao.TblUserDao;
//import com.manager.dao.TblUserDao;
import com.manager.entities.TblInsurance;
import com.manager.entities.TblUser;
import com.manager.entities.UserInfor;

@Service
public class UserInforLogic {
	@Autowired
	private TblUserDao userDao;

	@Autowired
	private TblInsuranceDao insuranceDao;

	public List<UserInfor> getListUserInfor(String fullName, String insurancenumber, String place, String sortBy,
			String sortType, int offSet, int limit) {
		// viết lại paging, sử dụng pageable
		
		List<UserInfor> listUserInfor = new ArrayList<UserInfor>();
		// truong hop moi vao va 3 dieu kien tim kiem rong
		if ("".equals(fullName) && "".equals(insurancenumber) && "".equals(place)) {
			List<TblUser> listUser = userDao.findAll();
			int size = listUser.size();
			for (int i = 0; i < size; i++) {
				TblUser user = listUser.get(i);
				int ID = user.getInsurance_internal_id();
				TblInsurance insurance = insuranceDao.findOne(ID);
				listUserInfor.add(new UserInfor(user.getUser_full_name(), insurance.getInsurance_number(),
						insurance.getPlace_of_register(), insurance.getInsurance_start_date(),
						insurance.getInsurance_end_date(), user.getUser_sex_division(), user.getBirthdate()));
			}
			// tim kiem khi biet place
		} else if ("".equals(fullName) && "".equals(insurancenumber)) {
			List<TblInsurance> listInsurance = insuranceDao.findByPlaceOfRegister(place);
			if (listInsurance.size() != 0) {
				List<TblUser> listUser = userDao.findAll();
				int size = listUser.size();
				for (int i = 0; i < size; i++) {
					TblUser user = listUser.get(i);
					TblInsurance insurance = listInsurance.get(0);
					if (user.getInsurance_internal_id() == insurance.getInsurance_internal_id()) {
						listUserInfor.add(new UserInfor(user.getUser_full_name(), insurance.getInsurance_number(),
								insurance.getPlace_of_register(), insurance.getInsurance_start_date(),
								insurance.getInsurance_end_date(), user.getUser_sex_division(), user.getBirthdate()));
					}
				}
			}
			// truong hop khi biet ten
		} else if ("".equals(insurancenumber) && "".equals(place)) {
			List<TblUser> listUser = userDao.findByUserFullName(fullName);
			int size = listUser.size();
			for (int i = 0; i < size; i++) {
				TblUser user = listUser.get(i);
				int ID = user.getInsurance_internal_id();
				TblInsurance insurance = insuranceDao.findOne(ID);
				if (user.getInsurance_internal_id() == insurance.getInsurance_internal_id()) {
					listUserInfor.add(new UserInfor(user.getUser_full_name(), insurance.getInsurance_number(),
							insurance.getPlace_of_register(), insurance.getInsurance_start_date(),
							insurance.getInsurance_end_date(), user.getUser_sex_division(), user.getBirthdate()));
				}
			}
		} else if ("".equals(fullName) && "".equals(place)) {
			List<TblInsurance> listIns = insuranceDao.findByInsuranceNumber(insurancenumber);
			if (listIns.size() != 0) {
				List<TblUser> listUser = userDao.findAll();
				int size = listUser.size();
				for (int i = 0; i < size; i++) {
					TblUser user = listUser.get(i);
					TblInsurance insurance = listIns.get(0);
					if (user.getInsurance_internal_id() == insurance.getInsurance_internal_id()) {
						listUserInfor.add(new UserInfor(user.getUser_full_name(), insurance.getInsurance_number(),
								insurance.getPlace_of_register(), insurance.getInsurance_start_date(),
								insurance.getInsurance_end_date(), user.getUser_sex_division(), user.getBirthdate()));
					}
				}
			}
		} else if ("".equals(fullName)) {
			List<TblInsurance> listIns = insuranceDao.findByInsuranceNumberAndPlaceOfRegister(insurancenumber, place);
			if (listIns.size() != 0) {
				List<TblUser> listUser = userDao.findAll();
				int size = listUser.size();
				for (int i = 0; i < size; i++) {
					TblUser user = listUser.get(i);
					TblInsurance insurance = listIns.get(0);
					if (user.getInsurance_internal_id() == insurance.getInsurance_internal_id()) {
						listUserInfor.add(new UserInfor(user.getUser_full_name(), insurance.getInsurance_number(),
								insurance.getPlace_of_register(), insurance.getInsurance_start_date(),
								insurance.getInsurance_end_date(), user.getUser_sex_division(), user.getBirthdate()));
					}
				}
			}
		} else if ("".equals(insurancenumber)) {
			List<TblInsurance> listInsurance = insuranceDao.findByPlaceOfRegister(place);
			if (listInsurance.size() != 0) {
				List<TblUser> listUser = userDao.findByUserFullName(fullName);
				int size = listUser.size();
				for (int i = 0; i < size; i++) {
					TblUser user = listUser.get(i);
					TblInsurance insurance = listInsurance.get(0);
					if (user.getInsurance_internal_id() == insurance.getInsurance_internal_id()) {
						listUserInfor.add(new UserInfor(user.getUser_full_name(), insurance.getInsurance_number(),
								insurance.getPlace_of_register(), insurance.getInsurance_start_date(),
								insurance.getInsurance_end_date(), user.getUser_sex_division(), user.getBirthdate()));
					}
				}
			}
			// truong hop dieu kien ten+noi dk
		} else if ("".equals(place)) {
			List<TblInsurance> listIns = insuranceDao.findByInsuranceNumber(insurancenumber);
			if (listIns.size() > 0) {
				List<TblUser> listUser = userDao.findByUserFullName(fullName);
				int size = listUser.size();
				for (int i = 0; i < size; i++) {
					TblUser user = listUser.get(i);
					TblInsurance insurance = listIns.get(0);
					if (user.getInsurance_internal_id() == insurance.getInsurance_internal_id()) {
						listUserInfor.add(new UserInfor(user.getUser_full_name(), insurance.getInsurance_number(),
								insurance.getPlace_of_register(), insurance.getInsurance_start_date(),
								insurance.getInsurance_end_date(), user.getUser_sex_division(), user.getBirthdate()));
					}
				}
			}
		} else {
			// truong hop ca 3 deu co gia tri
			List<TblInsurance> listIns = insuranceDao.findByInsuranceNumber(insurancenumber);
			if (listIns.size() != 0) {
				List<TblUser> listUser = userDao.findByUserFullName(fullName);
				int size = listUser.size();
				for (int i = 0; i < size; i++) {
					TblUser user = listUser.get(i);
					TblInsurance insurance = listIns.get(0);
					if (user.getInsurance_internal_id() == insurance.getInsurance_internal_id()) {
						listUserInfor.add(new UserInfor(user.getUser_full_name(), insurance.getInsurance_number(),
								insurance.getPlace_of_register(), insurance.getInsurance_start_date(),
								insurance.getInsurance_end_date(), user.getUser_sex_division(), user.getBirthdate()));
					}
				}
			}
		}
		return listUserInfor;
	}

}
