package com.example.thebaohiem.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CommonTest {
    @Test
    public void testCheckLeapYearWithYearTrue() {
        //setup
        int year = 2016;
        //exercise
        boolean actual = Common.checkLeapYear(year);
        //verify
        assertEquals(actual, true);
    }

    @Test
    public void testCheckLeapYearWithYearFalse() {
        //setup
        int year = 2015;
        //exercise
        boolean actual = Common.checkLeapYear(year);
        //verify
        assertEquals(actual, false);
    }

    @Test
    public void testGetTotalPage() {
        //setup
        int totalRecord = 11;
        int limit = 5;
        //exercise
        int actual = Common.getTotalPage(totalRecord, limit);
        //verify
        assertEquals(actual, 3);
    }

    @Test
    public void testGetTotalPage1() {
        //setup
        int totalRecord = 10;
        int limit = 5;
        //exercise
        int actual = Common.getTotalPage(totalRecord, limit);
        //verify
        assertEquals(actual, 2);
    }

    @Test
    public void testGetListPaging() {
        //setup
        int totalPage = 11;
        int currentPage = 9;
        int maxPage = 5;
        List<Integer> expectList = Arrays.asList(7, 8, 9, 10, 11);
        //exercise
        List<Integer> pagingList = Common.getListPaging(totalPage, currentPage, maxPage);
        //verify
        assertThat(pagingList, is(expectList));
    }

    @Test
    public void testGetListPagingFailure() {
        //setup
        int totalPage = 11;
        int currentPage = 4;
        int maxPage = 5;
        List<Integer> expectList = Arrays.asList(1, 2, 3, 4, 5);
        //exercise
        List<Integer> pagingList = Common.getListPaging(totalPage, currentPage, maxPage);
        //verify
        assertThat(pagingList, not(expectList));
    }

    @Test
    public void testGetListPagingTotalPageLessThanMaxPage() {
        //setup
        int totalPage = 4;
        int currentPage = 1;
        int maxPage = 5;
        List<Integer> expectList = Arrays.asList(1, 2, 3, 4);
        //exercise
        List<Integer> pagingList = Common.getListPaging(totalPage, currentPage, maxPage);
        //verify
        assertThat(pagingList, is(expectList));

    }

    @Test
    public void testGetListPagingCurrentPageFirst() {
        //setup
        int totalPage = 7;
        int currentPage = 1;
        int maxPage = 5;
        List<Integer> expectList = Arrays.asList(1, 2, 3, 4, 5);
        //exercise
        List<Integer> pagingList = Common.getListPaging(totalPage, currentPage, maxPage);
        //verify
        assertThat(pagingList, is(expectList));
    }

    @Test
    public void testGetListPagingCurrentPageLast() {
        //setup
        int totalPage = 7;
        int currentPage = 7;
        int maxPage = 5;
        List<Integer> expectList = Arrays.asList(3, 4, 5, 6, 7);
        //exercise
        List<Integer> pagingList = Common.getListPaging(totalPage, currentPage, maxPage);
        //verify
        assertThat(pagingList, is(expectList));
    }

    @Test
    public void testGetCurrentPage() {
        //setup
        String str = "12";
        int totalPage = 12;
        //exercise
        int actual = Common.getCurrentPage(str, totalPage);
        //verify
        assertEquals(actual, 12);
    }

    @Test
    public void testGetCurrentPageBiggerThanTotalPage() {
        //setup
        String str = "13";
        int totalPage = 12;
        //exercise
        int actual = Common.getCurrentPage(str, totalPage);
        //verify
        assertEquals(actual, 12);
    }

    @Test
    public void testGetCurrentPageNotNumber() {
        //setup
        String str = "aaa";
        int totalPage = 12;
        //exercise
        int actual = Common.getCurrentPage(str, totalPage);
        //verify
        assertEquals(actual, 1);
    }

    @Test
    public void testGetCurrentPageIsNegative() {
        //setup
        String str = "-1";
        int totalPage = 12;
        //exercise
        int actual = Common.getCurrentPage(str, totalPage);
        //verify
        assertEquals(actual, 1);

    }

    @Test
    public void testGetCurrentPageIsZero() {
        //setup
        String str = "0";
        int totalPage = 12;
        //exercise
        int actual = Common.getCurrentPage(str, totalPage);
        //verify
        assertEquals(actual, 1);

    }

    @Test
    public void getTotalPageRecordNotDivideLimit() {
        //setup
        int totalRecord = 51;
        int limit = 5;
        //exercise
        int toltalPage = Common.getTotalPage(totalRecord, limit);
        //verify
        assertEquals(toltalPage, 11);
    }

    @Test
    public void getTotalPage1RecordDivideLimit() {
        //setup
        int totalRecord = 50;
        int limit = 5;
        //exercise
        int totalPage = Common.getTotalPage(totalRecord, limit);
        //verify
        assertEquals(totalPage, 10);
    }

    @Test
    public void testPressPrevious() {
        //setup
        int startPage = 1;
        //exercise
        int actual = Common.pressPrevious(startPage);
        assertEquals(actual, 0);

    }

    @Test
    public void testPressPreviousStatDateBiggerThanOne() {
        //setup
        int startPage = 2;
        //exercise
        int actual = Common.pressPrevious(startPage);
        assertEquals(actual, 1);

    }

    @Test
    public void testPressNext() {
        //setup
        int endDat = 5;
        int totalPage = 6;
        //exercise
        int actual = Common.pressNext(endDat, totalPage);
        //verify
        assertEquals(actual, 6);
    }

    @Test
    public void testPressNextTotalPageEqualsWithEndPage() {
        //setup
        int endDat = 5;
        int totalPage = 5;
        //exercise
        int actual = Common.pressNext(endDat, totalPage);
        //verify
        assertEquals(actual, 0);
    }

    @Test
    public void testGetSortType() {
    //setup
        String sortType = "asc";
        //exercise
        String actual = Common.getSortType(sortType);
        //verify
        assertEquals(actual,"DESC");
    }
    @Test
    public void testGetSortTypeEmpty() {
        //setup
        String sortType = "";
        //exercise
        String actual = Common.getSortType(sortType);
        //verify
        assertEquals(actual,"ASC");
    }
    @Test
    public void testGetSortTypeDESC()
    {
        //setup
        String sortType = "desc";
        //exercise
        String actual = Common.getSortType(sortType);
        //verify
        assertEquals(actual,"ASC");
    }
    @Test
    public void testReplaceWildcardPercentCharacter()
    {
        //setup
        String str = "%";
        //exercise
        str = Common.replaceWildcard(str);
        //verify
        assertEquals(str,"\\%");
    }
    @Test
    public void testReplaceWildcardCharacter()
    {
        //setup
        String str = "_";
        //exercise
        str = Common.replaceWildcard(str);
        //verify
        assertEquals(str,"\\_");
    }
    @Test
    public void testReplaceWildcardCharacter1()
    {
        //setup
        String str = "\\";
        //exercise
        str = Common.replaceWildcard(str);
        //verify
        assertEquals(str,"\\\\");
    }
    @Test
    public void testReplaceWildcard()
    {
        //setup
        String str = "";
        //exercise
        str = Common.replaceWildcard(str);
        //verify
        assertEquals(str,"");
    }

}
