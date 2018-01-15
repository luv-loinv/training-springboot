package com.example.thebaohiem.Dao.custom;

import com.example.thebaohiem.model.Company;
import com.example.thebaohiem.model.Insurance;
import com.example.thebaohiem.model.User;

public interface UserDaoCustom {

boolean insertUserInfo(User user , Insurance insurance , Company company);
}
