package com.example.thebaohiem.Dao;

import com.example.thebaohiem.Dao.custom.UserDaoCustom;
import com.example.thebaohiem.model.Company;
import com.example.thebaohiem.model.Insurance;
import com.example.thebaohiem.model.User;
import com.example.thebaohiem.model.UserInfo;
import com.example.thebaohiem.utils.Common;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class UserDaoCustomImplTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private InsuranceDao insuranceDao;
    @Autowired
    private UserDaoCustomImpl userDaoCustomImpl;
    Company company;
    @Autowired
    private TestEntityManager entityManager;

    @Before
    public void setUp() {
        company = new Company("Luvina", "Ha Noi");
        companyDao.save(company);
    }

    /**
     *
     */
    @Test
    public void testInsertOrUpdateUserInfo() {
        //setup
        Company company = new Company("Luvina", "Ha Noi");
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "Le Thi Ha ", "01", company, insurance);

        //exercise
        boolean actual = userDaoCustomImpl.insertUserInfo(user, insurance, company);
        //verify
        assertTrue(actual);
        assertEquals(company.getCompanyName(), companyDao.findByCompanyInternalId(1).getCompanyName());
        assertEquals(insurance.getInsuranceNumber(), insuranceDao.findByInsuranceNumber(insurance.getInsuranceNumber()).getInsuranceNumber());
        assertEquals(user.getUserName(), userDao.findByUserInternalId(1).getUserName());
    }

    @Test
    public void testInsertOrUpdateUserInfoExistCompany() {
        //setup
        Company company = new Company();
        company.setCompanyInternalId(1);
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "Le Thi Ha ", "01", company, insurance);

        //exercise
        boolean actual = userDaoCustomImpl.insertUserInfo(user, insurance, company);
        //verify
        assertTrue(actual);
        assertEquals(insurance.getInsuranceNumber(), insuranceDao.findByInsuranceNumber(insurance.getInsuranceNumber()).getInsuranceNumber());
        assertEquals(user.getUserName(), userDao.findByUserInternalId(1).getUserName());
    }

    @Test
    public void testGetListUserInfo() {
        //setup
        Company company = new Company();
        company.setCompanyInternalId(1);
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "Le Thi Ha ", "01", company, insurance);
        userDaoCustomImpl.insertUserInfo(user, insurance, company);
        //exercise
        List<UserInfo> userInfoList = userDaoCustomImpl.getListUserInfo(1, user.getUserFullName(), insurance.getInsuranceNumber(), insurance.getPlaceOfRegister(), 0, 1, "ASC");
        //verify
        assertEquals(userInfoList.size(), 1);
        assertEquals(userInfoList.get(0).getInsuranceNumber(), insurance.getInsuranceNumber());
        assertEquals(userInfoList.get(0).getUserFullName(), user.getUserFullName());
        assertEquals(userInfoList.get(0).getInsuranceStartDate(), insurance.getInsuranceStartDate());
        assertEquals(userInfoList.get(0).getInsuranceEndDate(), insurance.getInsuranceEndDate());
        assertEquals(userInfoList.get(0).getPlaceOfRegister(), insurance.getPlaceOfRegister());
        assertEquals(userInfoList.get(0).getBirthdate(), user.getBirthdate());
    }

    @Test
    public void testGetListUserInfoNotExistInsuranceNumber() {
        //setup
        Company company = new Company();
        company.setCompanyInternalId(1);
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "Le Thi Ha ", "01", company, insurance);
        userDaoCustomImpl.insertUserInfo(user, insurance, company);
        //exercise
        List<UserInfo> userInfoList = userDaoCustomImpl.getListUserInfo(1, user.getUserFullName(), "123124", insurance.getPlaceOfRegister(), 0, 1, "ASC");
        //verify
        assertEquals(userInfoList.size(), 0);

    }

    @Test
    public void testGetListUserInfoEmptyInsuranceNumber() {
        //setup
        Company company = new Company();
        company.setCompanyInternalId(1);
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "Le Thi Ha ", "01", company, insurance);
        userDaoCustomImpl.insertUserInfo(user, insurance, company);
        //exercise
        List<UserInfo> userInfoList = userDaoCustomImpl.getListUserInfo(1, user.getUserFullName(), "", insurance.getPlaceOfRegister(), 0, 1, "ASC");
        //verify
        assertEquals(userInfoList.size(), 1);
        assertEquals(userInfoList.get(0).getInsuranceNumber(), insurance.getInsuranceNumber());
        assertEquals(userInfoList.get(0).getUserFullName(), user.getUserFullName());
        assertEquals(userInfoList.get(0).getInsuranceStartDate(), insurance.getInsuranceStartDate());
        assertEquals(userInfoList.get(0).getInsuranceEndDate(), insurance.getInsuranceEndDate());
        assertEquals(userInfoList.get(0).getPlaceOfRegister(), insurance.getPlaceOfRegister());
        assertEquals(userInfoList.get(0).getBirthdate(), user.getBirthdate());

    }

    @Test
    public void testGetListUserInfoFullNameContainsSQLCharacters() {
        //setup
        Company company = new Company();
        company.setCompanyInternalId(1);
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "Le Thi Ha ", "01", company, insurance);
        String fullName = "Hang or 1=1  ";
        String place = "Viet Nam ";
        userDaoCustomImpl.insertUserInfo(user, insurance, company);
        //exercise
        List<UserInfo> userInfoList = userDaoCustomImpl.getListUserInfo(1, fullName, "", insurance.getPlaceOfRegister(), 0, 1, "ASC");
        //verify
        assertEquals(userInfoList.size(), 0);
    }
    @Test
    public void testGetListUserInfoFullNameContainsSQLCharacters1() {
        //setup
        Company company = new Company();
        company.setCompanyInternalId(1);
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "Le Thi Hang or 1=1 ", "01", company, insurance);
        String fullName = "Hang or 1=1  ";
        String place = "Viet Nam ";
        userDaoCustomImpl.insertUserInfo(user, insurance, company);
        //exercise
        List<UserInfo> userInfoList = userDaoCustomImpl.getListUserInfo(1, fullName, "", insurance.getPlaceOfRegister(), 0, 1, "ASC");
        //verify
        assertEquals(userInfoList.size(), 1);
    }


    @Test
    public void testGetListUserInfoFullNameContainsWildcardCharacters() {
        //setup
        Company company = new Company();
        company.setCompanyInternalId(1);
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "Le Thi Ha ", "01", company, insurance);
        String fullName = "%";
        fullName = Common.replaceWildcard(fullName);
        userDaoCustomImpl.insertUserInfo(user, insurance, company);
        //exercise
        List<UserInfo> userInfoList = userDaoCustomImpl.getListUserInfo(1, fullName, "", insurance.getPlaceOfRegister(), 0, 1, "ASC");
        //verify
        assertEquals(userInfoList.size(), 0);
    }
    @Test
    public void testGetListUserInfoFullNameContainsWildcardCharactersHasInDatabse() {
        //setup
        Company company = new Company();
        company.setCompanyInternalId(1);
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "Le% Thi Ha ", "01", company, insurance);
        String fullName = "%";
        fullName = Common.replaceWildcard(fullName);
        userDaoCustomImpl.insertUserInfo(user, insurance, company);
        //exercise
        List<UserInfo> userInfoList = userDaoCustomImpl.getListUserInfo(1, fullName, "", insurance.getPlaceOfRegister(), 0, 1, "ASC");
        //verify
        assertEquals(userInfoList.size(), 1);
    }

    @Test
    public void testGetListUserInfoFullNameContainsWildcardCharacters_() {
        //setup
        Company company = new Company();
        company.setCompanyInternalId(1);
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "Le Thi Ha ", "01", company, insurance);
        String fullName = "_";
        fullName = Common.replaceWildcard(fullName);
        userDaoCustomImpl.insertUserInfo(user, insurance, company);
        //exercise
        List<UserInfo> userInfoList = userDaoCustomImpl.getListUserInfo(1, fullName, "", insurance.getPlaceOfRegister(), 0, 1, "ASC");
        //verify
        assertEquals(userInfoList.size(), 0);
    }
    @Test
    public void testGetListUserInfoFullNameContainsWildcardCharacters_HasInDatabase() {
        //setup
        Company company = new Company();
        company.setCompanyInternalId(1);
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "L_e Thi Ha ", "01", company, insurance);
        String fullName = "_";
        fullName = Common.replaceWildcard(fullName);
        userDaoCustomImpl.insertUserInfo(user, insurance, company);
        //exercise
        List<UserInfo> userInfoList = userDaoCustomImpl.getListUserInfo(1, fullName, "", insurance.getPlaceOfRegister(), 0, 1, "ASC");
        //verify
        assertEquals(userInfoList.size(), 1);
    }
    @Test
    public void testGetListUserInfoFullNameContainsSpecialCharacter() {
        //setup
        Company company = new Company();
        company.setCompanyInternalId(1);
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "Le Thi Ha's ", "01", company, insurance);
        String fullName = "'";
        fullName = Common.replaceWildcard(fullName);
        userDaoCustomImpl.insertUserInfo(user, insurance, company);
        //exercise
        List<UserInfo> userInfoList = userDaoCustomImpl.getListUserInfo(1, fullName, "", insurance.getPlaceOfRegister(), 0, 1, "ASC");
        //verify
        assertEquals(userInfoList.size(), 1);
    }
}
