package com.manager.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * @author nguyenthanhtung
 *
 */
public class Common {
	public static int startPage;
	public static int endPage;
	public static int currentRange;
	public static int endPoint;
	public static int startPoint;

	/**
	 * Phương thức tính chuỗi paging
	 * 
	 * @param totalUser
	 * @param limit
	 * @param currentPage
	 * @return
	 */
	public static List<Integer> getListPaging(int totalUser, int limit, int currentPage) {
		List<Integer> listPaging = new ArrayList<Integer>();
		int totalPage = getTotalPage(totalUser, limit);
		int range = Constant.RANGE;
		int totalChuoi = totalChuoi(totalPage, range);
		currentRange = getCurrentRange(currentPage, range);
		endPoint = getEndPoint(currentRange, range);
		if (currentRange == totalChuoi) {
			endPoint = totalPage;
		}
		startPoint = getStartPoint(currentRange, range);
		if (startPoint == 1 && startPoint == endPoint) {
			return listPaging;
		}
		for (int i = startPoint; i <= endPoint; i++) {
			listPaging.add(i);
		}
		return listPaging;
	}

	/**
	 * Phương tính vị trí bắt đầu lấy bản ghi
	 * 
	 * @param currentPage
	 * @param limit
	 * @return
	 */
	public static int getOffset(int currentPage, int limit) {
		return (currentPage - 1) * limit;

	}

	/**
	 * Phương thức lấy số bản ghi hiển thị 1 trang
	 * 
	 * @return
	 */
	public static int getLimit() {
		return Constant.LIMIT;
	}

	/**
	 * Phương thức tính tổng số trang
	 * 
	 * @param totalUser
	 * @param limit
	 * @return
	 */
	public static int getTotalPage(int totalUser, int limit) {
		if (totalUser % limit == 0) {
			return totalUser / limit;
		} else {
			return totalUser / limit + 1;
		}
	}

	/**
	 * Phương thức tính tổng số chuỗi
	 * 
	 * @param totalPage
	 * @param range
	 * @return
	 */
	public static int totalChuoi(int totalPage, int range) {
		return (int) Math.ceil((float) totalPage / range);
	}

	/**
	 * Phương thức tính chuỗi hiện tại
	 * 
	 * @param currentPage
	 * @param range
	 * @return
	 */
	public static int getCurrentRange(int currentPage, int range) {
		return (int) Math.ceil((float) currentPage / range);
	}

	/**
	 * Phương thức tính điểm cuối cùng của chuỗi
	 * 
	 * @param chuoiHT
	 * @param range
	 * @return
	 */
	public static int getEndPoint(int chuoiHT, int range) {
		return chuoiHT * range;
	}

	/**
	 * Phương thức tính điểm bắt đầu của chuỗi
	 * 
	 * @param chuoiHT
	 * @param range
	 * @return
	 */
	public static int getStartPoint(int chuoiHT, int range) {
		return (chuoiHT - 1) * range + 1;
	}

	/**
	 * Phương thức chuyển Date thành 1 chuỗi String
	 * 
	 * @param date
	 * 
	 * @return chuỗi ngày tháng
	 */
	public static String convertDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	/**
	 * Phương thức mã hóa MD5
	 * 
	 * @param input
	 * @return
	 */
	public static String encryptMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}

	/**
	 * Phương thức kiểm tra chuỗi có rỗng không
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkInput(String text) {
		if ("".equals(text) || text == null) {
			return false;
		}
		return true;
	}

	/**
	 * Phương thức kiểm tra định dạng ngày tháng của chuỗi nhập vào
	 * 
	 * @param dateInput
	 * @return
	 */
	public static boolean checkFormatDate(String dateInput) {
		SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
		try {
			simple.parse(dateInput);
			return true;
		} catch (ParseException e) {
			return false;
		}

	}

	/**
	 * Phương thức kiểm tra định dạng email
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkMailFormat(String email) {
		try {
			String regexEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			boolean check = email.matches(regexEmail);
			return check;
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * Phương thức chuyển từ chuỗi String sang Date
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date convertToDate(String date) throws ParseException {
		DateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
		return simple.parse(date);
	}

	/**
	 * Phương thức kiểm tra độ dài max của chuỗi
	 * 
	 * @param text
	 * @param maxLength
	 * @return
	 */
	public static boolean checkMaxLength(String text, int maxLength) {
		if (text.length() > maxLength) {
			return false;
		}
		return true;
	}

	/**
	 * Phương thức loại bỏ dấu tiếng việt
	 * 
	 * @param text
	 * @return
	 */
	public static String removeAccent(String text) {
		String temp = Normalizer.normalize(text, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
	}

	/**
	 * Phương thức loại bỏ dấu cách
	 * 
	 * @param text
	 * @return
	 */
	public static String removeSpace(String text) {
		String sp = " ";
		String sp2 = "  ";
		while (text.contains(sp2)) {
			text = text.replace(sp2, sp);
		}
		return text;
	}

	/**
	 * Phương thức kiểm tra định dạng ký tự có phải chữ latin không
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkLatin(String text) {
		try {
			String regexName = "^[_A-Za-z]$";
			boolean check = text.matches(regexName);
			return check;
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * Phương thức format chỉ lấy các ký tự latin
	 * 
	 * @param text
	 * @return
	 */
	public static String formatLatin(String text) {
		int length = text.length();
		String outString = "";
		for (int i = 0; i < length; i++) {
			char tmp = text.charAt(i);
			if (checkLatin(tmp + "") || " ".equals("" + tmp)) {
				outString += tmp;
			}
		}
		return outString;
	}

	/**
	 * Phương thức viết hoa chữ cái đầu mỗi từ
	 * 
	 * @param text
	 * @return
	 */
	public static String toUpcaseWord(String text) {
		String arrayStr[] = text.split(" ");
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < arrayStr.length; i++) {
			if (arrayStr[i].length() > 0) {
				arrayStr[i] = arrayStr[i].substring(0, 1).toUpperCase() + arrayStr[i].substring(1);
				result.append(arrayStr[i]);
				result.append(" ");
			}
		}
		return result.toString();
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static String formatText(String text) {
		return toUpcaseWord(formatLatin(removeAccent(removeSpace(text))));
	}

	/**
	 * Phương thức loại escape các ký tự sql
	 * 
	 * @param text
	 * @return
	 */
	public static String escapeSQLInjection(String text) {
		if (text.contains("%")) {
			text = text.replace("%", "\\%");
		}

		if (text.contains("\\")) {
			text = text.replace("\\", "\\\\");
		}

		if (text.contains("_")) {
			text = text.replace("_", "\\_");
		}
		return text;
	}

}
