package com.example.thebaohiem.logic.impl;

import com.example.thebaohiem.Dao.CompanyDao;
import com.example.thebaohiem.logic.Impl.CompanyLogicImpl;
import com.example.thebaohiem.model.Company;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompanyLogicImplTest {
    @InjectMocks
    private CompanyLogicImpl sut;
    @Mock
    private CompanyDao companyDao;

    @Before
    public void setUp() {
        when(companyDao.findByEmail(anyString())).thenAnswer(new Answer<Company>() {
            public Company answer(InvocationOnMock invocation) throws Throwable {
                Company company = new Company();
                company.setCompanyInternalId(1);
                company.setEmail("lethiha@luvina.net");
                company.setCompanyName("Luvina");
                company.setAddress("Ha Noi");
                company.setTelephone("12345678");
                return company;
            }
        });
        when(companyDao.findByTelephone(anyString())).thenAnswer(new Answer<Company>() {
            public Company answer(InvocationOnMock invocation) throws Throwable {
                Company company = new Company();
                company.setCompanyInternalId(1);
                company.setEmail("leth.net");
                company.setCompanyName("Luvina");
                company.setAddress("Ha Noi");
                company.setTelephone("12345678");
                return company;
            }
        });
    }

    @Test
    public void checkExistEmailCreate() {
        //setup
        String email = "lethiha@luvina.net";
        int companyId = 0;
        //exercise
        boolean actual = sut.checkExistEmail(email, companyId);
        //verify
        assertEquals(actual, true);
    }

    @Test
    public void checkExistEmailUpdate() {
        //setup
        String email = "lethiha@luvina.net";
        int companyId = 1;
        //exercise
        boolean actual = sut.checkExistEmail(email, companyId);
        //verify
        assertEquals(actual, false);
    }

    @Test
    public void checkExistTelCreate() {
        //setup
        String tel = "12345678";
        int companyId = 0;
        //exercise
        boolean actual = sut.checkExistTel(tel, companyId);
        //verify
        assertEquals(actual, true);
    }

    @Test
    public void checkExistTelUpdate() {
        //setup
        String tel = "12345678";
        int companyId = 1;
        //exercise
        boolean actual = sut.checkExistTel(tel, companyId);
        //verify
        assertEquals(actual, false);
    }

}
