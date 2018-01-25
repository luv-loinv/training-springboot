package com.example.thebaohiem.Dao;

import com.example.thebaohiem.model.Company;
import com.example.thebaohiem.model.Insurance;
import com.example.thebaohiem.model.User;
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
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class UserDaoTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserDao userDao;
    @Autowired
    private InsuranceDao insuranceDao;
    @Autowired
    private UserDaoCustomImpl userDaoCustomImpl;

    @Autowired
    private CompanyDao companyDao;
    @Test
    public void testFindByUserByInternalId() {
        //setup
        Company company = new Company("Luvina", "Ha Noi");
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "Le Thi Ha ", "01", company, insurance);
        entityManager.persist(company);
        entityManager.persist(insurance);
        entityManager.persist(user);
        entityManager.flush();
        //exercise
        User userFound = userDao.findByUserInternalId(1);
        //verify

        assertNotNull(userFound);
        assertEquals(user, userFound);
    }

    @Test
    public void testDeleteUser() {
        //setup
        Company company = new Company("Luvina", "Ha Noi");
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "Le Thi Ha ", "01", company, insurance);
        userDaoCustomImpl.insertUserInfo(user,insurance,company);
        //exercise
        userDao.delete(1);
        //verify
        assertNull(userDao.findByUserInternalId(1));
        assertNull(insuranceDao.findByInsuranceNumber(insurance.getInsuranceNumber()));
        assertNotNull(companyDao.findByCompanyInternalId(1));
    }
    @Test
    public void testFindByUserNameAndPassword() {
        //setup
        Company company = new Company("Luvina", "Ha Noi");
        Insurance insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
        User user = new User("ha chu", "chu ha", "Le Thi Ha ", "01", company, insurance);
        entityManager.persist(company);
        entityManager.persist(insurance);
        entityManager.persist(user);
        entityManager.flush();
        //exercise
        List<User> userList= userDao.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        //verify
        assertEquals(userList.size(),1);
        assertEquals(userList.get(0).toString(),user.toString());
    }
}

