package com.example.thebaohiem.logic.impl;

import com.example.thebaohiem.Dao.InsuranceDao;
import com.example.thebaohiem.logic.Impl.InsuranceLogicImpl;
import com.example.thebaohiem.model.Insurance;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.booleanThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InsuranceLogicImplTest {
    @Mock
    private InsuranceDao insuranceDao;
    @InjectMocks
    private InsuranceLogicImpl sut;

    @Before
    public void setUp() {
        when(insuranceDao.findByInsuranceNumber(anyString())).thenAnswer(new Answer<Insurance>() {
            public Insurance answer(InvocationOnMock invocation) throws Throwable {
                Insurance insurance = new Insurance("1234567878", "20/01/1994", "20/01/1995", "Ha Noi");
                insurance.setInsuranceInternalId(1);
                return insurance;
            }
        });
    }

    @Test
    public void testCheckExistInsuranceNumber() {
        //setup
        String insuranceNumber = "1234567878";
        int insuranceInternalId = 0;
        //exercise
        boolean actual = sut.checkExistInsuranceNumber(insuranceNumber, insuranceInternalId);
        //verify
        verify(insuranceDao, times(1)).findByInsuranceNumber(anyString());
        assertTrue(actual);
    }
    @Test
    public void testCheckExistInsuranceNumber1() {
        //setup
        String insuranceNumber = "1234567878";
        int insuranceInternalId = 1;
        //exercise
        boolean actual = sut.checkExistInsuranceNumber(insuranceNumber, insuranceInternalId);
        //verify
        verify(insuranceDao, times(1)).findByInsuranceNumber(anyString());
        assertFalse(actual);
    }
}
