package com.example.thebaohiem.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CommonTest {
    @Test
    public void testCheckLeapYearWithYearTrue()
    {
        //setup
        int year = 2016 ;
        //exercise
        boolean actual = Common.checkLeapYear(year);
        //verify
        assertEquals(actual,true);
    }
    @Test
    public void testCheckLeapYearWithYearFalse()
    {
        //setup
        int year = 2015 ;
        //exercise
        boolean actual = Common.checkLeapYear(year);
        //verify
        assertEquals(actual,false);
    }

}
