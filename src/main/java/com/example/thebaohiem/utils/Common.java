
package com.example.thebaohiem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Common {
    public static String convertDateHQL(String date) {
        if (date.length() > 0) {
            String[] rs = date.split("/");
            return rs[2] + "-" + rs[1] + "-" + rs[0];
        }
        return null;
    }
    public static String convertDateMysql(String date) {
        if (date.length() > 0) {
            String[] rs = date.split("-");
            return rs[2] + "/" + rs[1] + "/" + rs[0];
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
            if(year>0 && day>0 && month >0) {
                switch (month) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        if ( day <= 31) {
                            check = true;
                        }
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        if ( day < 31) {
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
    public static boolean checkEmpty(String str )
    {
        if(str != null && str.trim().equals("") ==false )
        return false;
        return true;
    }
    public static boolean checkLength(String str , int min , int max)
    {
        str = str.trim();
        if(min <= str.length()&& str.length() <= max)
            return  true;
        return false;
    }
    public static boolean checkFormat(String str , String regex)
    {
        return Pattern.matches(regex,str);
    }

}