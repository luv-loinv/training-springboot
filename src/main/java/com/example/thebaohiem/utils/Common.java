
package com.example.thebaohiem.utils;

import com.example.thebaohiem.model.Form.UserInfoForm;
import com.example.thebaohiem.model.User;
import com.example.thebaohiem.model.UserInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * methods common
 *
 * @author lethiha
 */
public class Common {
    /**
     * convert string dat with format dd/MM/YYYY to string ha format YYYY-MM-dd
     *
     * @param date: string date
     * @return : string has format YYYY-MM-dd
     */
    public static String convertDateHQL(String date) {
        if (date != null && date.length() > 0) {
            try {
                String[] rs = date.split("/");
                return rs[2] + "-" + rs[1] + "-" + rs[0];
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * convert string dat with format YYYY-MM-dd to string ha format dd/MM/YYYY
     *
     * @param date: string date
     * @return : string has format dd/mm/yyyy
     */
    public static String convertDateMysql(String date) {
        if (date != null && date.length() > 0) {
            try {
                String[] rs = date.split("-");
                return rs[2] + "/" + rs[1] + "/" + rs[0];
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * check leap year
     *
     * @param year : year
     * @return : true : leap year , false : not leap year
     */
    public static boolean checkLeapYear(int year) {

        if (year > 0 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)))
            return true;
        return false;
    }

    /**
     * check valid date
     *
     * @param date
     * @return : true : valid , false mot valid
     */
    public static boolean checkDate(String date) {
        try {
            String[] dates = date.split("/");
            int year = Integer.parseInt(dates[2]);
            int month = Integer.parseInt(dates[1]);
            int day = Integer.parseInt(dates[0]);
            boolean check = false;
            if (year > 0 && day > 0 && month > 0) {
                switch (month) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        if (day <= 31) {
                            check = true;
                        }
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        if (day < 31) {
                            check = true;
                        }
                    case 2:
                        if (day < 29) {
                            check = true;
                        } else if (day == 29 && checkLeapYear(year) == true) {
                            check = true;
                        }
                }
            }
            return check;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * compare end date with start date .
     *
     * @param endDate   : end date
     * @param startDate : start date
     * @return : true end date > start date , false end date <= start date
     */
    public static boolean compareEndDateWithStartDate(String endDate, String startDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/DD");
        boolean resultCompare;
        try {
            Date dt1 = sdf.parse(endDate);
            Date dt2 = sdf.parse(startDate);
            resultCompare = dt1.after(dt2);
            if (resultCompare == true) {
                return true;
            }
        } catch (ParseException e) {
            System.out.println("error: " + e.getMessage());
        }
        return false;
    }

    /**
     * check empty string.
     *
     * @param str : String to check.
     * @return : true : null or empty , false :has date.
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.trim().equals(""));
    }

    /**
     * check string has length between min <=str.length <= max.
     *
     * @param str : String to check.
     * @param min : min
     * @param max : mã
     * @return true : string has length between min and max, false string hasn't length between min and max
     */
    public static boolean checkLength(String str, int min, int max) {
        str = str.trim();
        return min <= str.length() && str.length() <= max;
    }

    /**
     * check format String
     *
     * @param str   : string to check
     * @param regex : regex
     * @return : true : true format , false : false format
     */
    public static boolean checkFormat(String str, String regex) {
        return Pattern.matches(regex, str);
    }

    /**
     * get listPaging
     *
     * @param currentPage : currentPage
     * @param totalPage   : toltalPage
     * @param maxPage     : max number page
     * @return List<Integer>
     */
    public static List<Integer> getListPaging(int totalPage, int currentPage, int maxPage) {
        List<Integer> pagingList = new ArrayList<>();
        int startDate = 1;
        int endPage;
        int middePage = maxPage / 2 + 1;
        if (totalPage > maxPage) {
            if (currentPage > middePage) {
                endPage = currentPage + middePage - 1;
                if ((endPage > totalPage)) {
                    endPage = totalPage;
                }
                startDate = endPage - (maxPage - 1);
            } else {
                endPage = maxPage;
            }
        } else {
            endPage = totalPage;
        }
        for (int i = startDate; i <= endPage; i++) {
            pagingList.add(i);
        }
        return pagingList;
    }

    /**
     * get object UserInfo from object User
     *
     * @param user : User
     * @return : UserInfo if user not null , null if user null
     */
    public static UserInfoForm getUserInfoFormFromUser(User user) {
        if (user != null) {
            UserInfoForm userInfo = new UserInfoForm();
            userInfo.setBirthday(Common.convertDateMysql(user.getBirthdate()));
            userInfo.setInsuranceEndDate(Common.convertDateMysql(user.getInsurance().getInsuranceEndDate()));
            userInfo.setInsuranceStartDate(Common.convertDateMysql(user.getInsurance().getInsuranceStartDate()));
            userInfo.setInsuranceNumber(user.getInsurance().getInsuranceNumber());
            userInfo.setPlaceOfRegister(user.getInsurance().getPlaceOfRegister());
            userInfo.setSex(user.getUserSexDivision());
            userInfo.setCompanyAddress(user.getCompany().getAddress());
            userInfo.setEmail((user.getCompany().getEmail()));
            userInfo.setTelephone(user.getCompany().getTelephone());
            userInfo.setCompanyName(user.getCompany().getCompanyName());
            userInfo.setFullName(user.getUserFullName());
            userInfo.setUserInternalID(user.getUserInternalId());
            userInfo.setCompanyInternalID(user.getCompany().getCompanyInternalId());
            userInfo.setInsuranceInternalId(user.getInsurance().getInsuranceInternalId());
            return userInfo;
        }
        return null;
    }

    /**
     * get total page
     *
     * @param totalRecord : total record in database with condition search
     * @param limit       : numbers of record in page
     * @return : total page
     */
    public static int getTotalPage(int totalRecord, int limit) {
        return (int) Math.ceil((float) totalRecord / limit);
    }

    /**
     * get currentPage
     *
     * @param str       : String currentPage
     * @param totalPage : total page
     * @return : currentPage
     */
    public static int getCurrentPage(String str, int totalPage) {
        try {
            int currentPage = Integer.parseInt(str);
            if (currentPage <= 0) {
                return 1;
            }
            if (currentPage > totalPage) {
                return totalPage;
            } else {
                return currentPage;
            }
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * get index first record  in database
     *
     * @param currentPage : currentPage
     * @param limit       : numbers of record in page
     * @return index
     */
    public static int getOffset(int currentPage, int limit) {
        return (currentPage - 1) * limit;
    }

    /**
     * replace wildcard character
     *
     * @param str : String want to replace
     * @return : String replaced wildcard character
     */
    public static String replaceWildcard(String str) {
        if (isEmpty(str) == false) {
            return str.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_");
        }
        return "";
    }

    /**
     * get sex
     *
     * @param str
     * @return
     */
    public static String getSex(String str) {
        if (isEmpty(str) == false) {
            if (str.equals("1")) {
                return "Nữ";
            }
            return "Nam";
        }
        return "";
    }

    /**
     * get current page when press button <<
     *
     * @param startPage : start page of list paging
     * @return : current page
     */
    public static int pressPrevious(int startPage) {
        if (startPage > 1) {
            return startPage - 1;

        }
        return 0;
    }

    /**
     * get current page when press button >>
     *
     * @param endPage   : endPage
     * @param totalPage : totalPage
     * @return: currentPage
     */
    public static int pressNext(int endPage, int totalPage) {
        if (totalPage > endPage) {
            return endPage + 1;
        }
        return 0;
    }

    /**
     * get sort type
     *
     * @param sortType :sortType
     * @return :sortType
     */
    public static String getSortType(String sortType) {
        if (isEmpty(sortType) == false && (sortType.toUpperCase()).equals("ASC")) {
            return "DESC";
        }
        return "ASC";
    }

    public static int getInt(String str) {
        try {
            int value = Integer.parseInt(str);
            if (value < 0) {
                return 1;
            }
            return value;
        } catch (Exception e) {
            return 1;
        }
    }

}