package com.example.thebaohiem.Dao;

import com.example.thebaohiem.model.Insurance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InsuranceDaoTest {
    @Autowired
    private InsuranceDao insuranceDao;
    Insurance insurance;

    @Before
    public void setUp() {
        insurance = new Insurance("1234567898", "20/01/2018", "20/01/2019", "Viet Nam");
    }

    @Test
    public void testCreate() {
        //exercise
        insuranceDao.save(insurance);
        //verify
        assertEquals(insuranceDao.findByInsuranceNumber(insurance.getInsuranceNumber()).toString(), insurance.toString());
    }

    @Test
    public void testUpdate() {
        //setup
        insuranceDao.save(insurance);
        Insurance insuranceUpdate = new Insurance("1234567898", "20/01/2019", "20/01/2020", "Thanh Pho Ho Chi Minh");
        insuranceUpdate.setInsuranceInternalId(insurance.getInsuranceInternalId());
        //exercise
        insuranceDao.save(insuranceUpdate);
        //verify
        assertEquals(insuranceDao.findByInsuranceNumber(insuranceUpdate.getInsuranceNumber()).toString(), insuranceUpdate.toString());
    }

    @Test
    public void testFindByInsuranceNumber() {
        //setup
        insuranceDao.save(insurance);
        //exercise
        Insurance insuranceFound = insuranceDao.findByInsuranceNumber(insurance.getInsuranceNumber());
        // verify
        assertEquals(insuranceFound.toString(), insurance.toString());

    }

    @Test
    public void testDelete() {
        //setup
        insuranceDao.save(insurance);
        //exercise
        insuranceDao.delete(insurance.getInsuranceInternalId());
        // verify
        assertNull(insuranceDao.findByInsuranceNumber(insurance.getInsuranceNumber()));
    }
}
