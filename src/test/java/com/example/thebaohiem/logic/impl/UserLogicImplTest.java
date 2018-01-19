package com.example.thebaohiem.logic.impl;

import com.example.thebaohiem.Dao.CompanyDao;
import com.example.thebaohiem.Dao.UserDao;
import com.example.thebaohiem.Dao.custom.UserDaoCustom;
import com.example.thebaohiem.logic.Impl.UserLogicImpl;
import com.example.thebaohiem.model.Form.LoginForm;
import com.example.thebaohiem.model.Form.UserInfoForm;
import com.example.thebaohiem.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserLogicImplTest {
    @InjectMocks
    private UserLogicImpl sut;
    @Mock
    private UserDao userDao;
    @Mock
    private UserDaoCustom userDaoCustom;
    @Mock
    private CompanyDao companyDao;


    @Before
    public void setUp() {
        when(userDao.findByUserNameAndPassword(anyString(), anyString())).thenAnswer(new Answer<List<User>>() {

            @Override
            public List<User> answer(InvocationOnMock invocation) throws Throwable {
                List<User> listAccount = new ArrayList<>();
                listAccount.add(new User());
                return listAccount;
            }
        });
    }

    @Test
    public void CheckExistUserTest() {
        boolean rsCheck = sut.checkExistUser("hachu", "hachu");
        assertTrue(rsCheck == true);
    }

    @Test
    public void insertUpdateUserInfoNotExistCompanyTest() {
        //setup
        when(userDaoCustom.insertUserInfo(anyObject(), anyObject(), anyObject())).thenReturn(true);
        LoginForm loginForm = new LoginForm("hachu","hachu");
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567898");
        userInfoForm.setFullName("Le Thi Ha");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("2018/09/08");
        userInfoForm.setInsuranceEndDate("2018/10/10");
        //userInfoForm.setCompanyInternalID(1);
        userInfoForm.setCompanyName("Luvina");
        userInfoForm.setCompanyAddress("Ha Noi");
        // exercise
        boolean check = sut.insertUserInfo(userInfoForm,loginForm);
        // verify
        verify(userDaoCustom, times(1)).insertUserInfo(anyObject(),anyObject(), anyObject());
        assertTrue(check);
    }
    @Test
    public void insertUpdateUserInfoTest() {
        //setup
        when(userDaoCustom.insertUserInfo(anyObject(), anyObject(), anyObject())).thenReturn(true);
        LoginForm loginForm = new LoginForm("hachu","hachu");
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567898");
        userInfoForm.setFullName("Le Thi Ha");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("2018/09/08");
        userInfoForm.setInsuranceEndDate("2018/10/10");
        userInfoForm.setCompanyInternalID(1);
        // exercise
        boolean check = sut.insertUserInfo(userInfoForm,loginForm);
        // verify
        verify(userDaoCustom, times(1)).insertUserInfo(anyObject(),anyObject(), anyObject());
        assertTrue(check);
    }
}
