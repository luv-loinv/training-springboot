package com.example.thebaohiem.Dao.custom;

import com.example.thebaohiem.model.Company;
import com.example.thebaohiem.model.Insurance;
import com.example.thebaohiem.model.User;
import com.example.thebaohiem.model.UserInfo;

import java.util.List;

public interface UserDaoCustom {

    boolean insertUserInfo(User user, Insurance insurance, Company company);
    List<UserInfo> getListUserInfo(int internalCompanyId, String userName , String insuranceNumber , String placeOfRegister, int offset , int limit , String sortType);
    int getTotalUser(int internalCompanyId, String userName , String insuranceNumber , String placeOfRegister);
    List<Company> getListCompany();


}