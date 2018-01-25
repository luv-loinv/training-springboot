package com.example.thebaohiem.Dao;

import com.example.thebaohiem.model.Company;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * class test CompanyDao
 *
 * @author lethiha
 * @since 22/01/2018
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyDaoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompanyDao companyDao;
    Company company;
    @Before
    public void setUp()
    {
        company = new Company("Luvina", "Ha Noi", "example@com", "12345678");
       // company.setCompanyInternalId(1);
      //  companyDao.save(company);
    }

    /**
     * test function findByEmail.
     * case : email existed in database.
     * In : email = example@com.
     * Out : Company.
     */
    @Test
    public void testFindByEmail() {
        //setup
    //    Company company = new Company("Luvina", "Ha Noi", "example@com", "123456787");
        entityManager.persist(company);
        entityManager.flush();
        //exercise
        Company companyFound = companyDao.findByEmail(company.getEmail());
        //verify
        assertNotNull(companyFound);
        assertEquals(company.getEmail(), company.getEmail());
    }

    /**
     * test function findByEmail.
     * case : email not exist in database.
     * In email= 'example@gmail.com
     * Out Null
     */
    @Test
    public void testFindByEmailNotExist() {
        //setup
      //  Company company = new Company("Luvina", "Ha Noi", "example@com", "123456787");
        entityManager.persist(company);
        entityManager.flush();
        //exercise
        Company companyFound = companyDao.findByEmail("example@gmail.com");
        //verify
        assertNull(companyFound);

    }

    /**
     * test function findByTelephone.
     * case : telephone existed in database.
     * IN : telephone = "12345678"
     * Out : company
     */
    @Test
    public void testFindByTel() {
        //setup
    //    Company company = new Company("Luvina", "Ha Noi", "example@com", "12345678");
        entityManager.persist(company);
        entityManager.flush();
        //exercise
        Company companyFound = companyDao.findByTelephone(company.getTelephone());
        //verify
        assertNotNull(companyFound);
        assertEquals(companyFound.getTelephone(), company.getTelephone());
    }

    /**
     * test function findByTelephone.
     * case : telephone existed in database.
     * IN : telephone = "123431234"
     * Out : company
     */
    @Test
    public void testFindByTelNotExist() {
        //setup
      //  Company company = new Company("Luvina", "Ha Noi", "example@com", "12345678");
        entityManager.persist(company);
        entityManager.flush();
        //exercise
        Company companyFound = companyDao.findByTelephone("123431234");
        //verify
        assertNull(companyFound);
    }

    @Test
    public void testSaveCompany() {
        //exercise
        companyDao.save(company);
        //verify
        Company companyFound = companyDao.findByCompanyInternalId(1);
        assertEquals(companyFound.toString(),company.toString());
    }
    @Test
    public void testSave()
    {
    Company companys = new Company("Luvina","Viet Nam");
    companys.setCompanyInternalId(1);
    //exercise
        companyDao.save(companys);
        //verify
        assertEquals(companyDao.findByCompanyInternalId(1).toString(), companys.toString());

    }
}
