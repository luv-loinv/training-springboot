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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.manager.dao.TblInsuranceDao;
import com.manager.logics.TblCompanyLogic;
import com.manager.logics.TblInsuranceLogic;

@Service
public class Common {

	@Autowired
	static TblInsuranceLogic insLogic;

	@Autowired
	static TblCompanyLogic compLogic;

	@Autowired
	static TblInsuranceDao insurenceDao;

	public static int startPage;
	public static int endPage;
	public static int currentRange;
	public static int endPoint;
	public static int startPoint;

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

	public static int getOffset(int currentPage, int limit) {
		return (currentPage - 1) * limit;

	}

	public static int getLimit() {
		return Constant.LIMIT;
	}

	public static int getTotalPage(int totalUser, int limit) {
		if (totalUser % limit == 0) {
			return totalUser / limit;
		} else {
			return totalUser / limit + 1;
		}
	}

	public static int totalChuoi(int totalPage, int range) {
		return (int) Math.ceil((float) totalPage / range);
	}

	public static int getCurrentRange(int currentPage, int range) {
		return (int) Math.ceil((float) currentPage / range);
	}

	public static int getEndPoint(int chuoiHT, int range) {
		return chuoiHT * range;
	}

	public static int getStartPoint(int chuoiHT, int range) {
		return (chuoiHT - 1) * range + 1;
	}

	/**
	 * Chuyển chuỗi ngày tháng thành dạng Date theo định dạng dd/MM/yyy
	 * 
	 * @param date
	 *            chuỗi ngày tháng
	 * @return null nếu chuỗi nhập vào không đúng định dạng hoặc ngày không tồn
	 *         tại Date nếu parse thành công
	 */
	public static Date parseStringToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = null;
		try {
			sdf.setLenient(false);
			dt = sdf.parse(date);
		} catch (Exception e) {
			System.out.println("Không parse được " + date + " sang date");
		}

		return dt;
	}

	/**
	 * Chuyển 1 đối tượng Date thành 1 chuỗi theo định dạng dd/MM/yyyy
	 * 
	 * @param date
	 *            đối tượng date
	 * @return chuỗi ngày tháng
	 */
	public static String convertDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	/**
	 * tạo key ngẫu nhiên
	 * 
	 * @return mili giây hiện tại tính từ 1970
	 */
	public static String getRandomKey() {
		return new Date().getTime() + "";
	}

	/**
	 * Hàm chuyển ký tự tiếng việt có dấu về không dấu
	 * 
	 * @param str
	 *            là chuỗi ký tự
	 * @return chuỗi ký tự không dấu
	 */
	public static String unAccent(String str) {
		String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
	}

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

	public static boolean checkInput(String text) {
		if ("".equals(text) || text == null) {
			return false;
		}
		return true;
	}

	public static boolean checkExistIns(String insNumber) {
		int count = insurenceDao.countByInsuranceNumber(insNumber);
		if (count > 0) {
			return false;
		} else {
			return true;
		}
		// return insLogic.checkExistIns(insNumber);
	}

	public static boolean checkFormatDate(String dateInput) {
		SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
		try {
			simple.parse(dateInput);
			return true;
		} catch (ParseException e) {
			return false;
		}

	}

	public static boolean checkMailFormat(String email) {
		try {
			String regexEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			boolean check = email.matches(regexEmail);
			return check;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public static Date convertToDate(String date) throws ParseException {
		DateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
		return simple.parse(date);
	}

	public static boolean checkMaxLength(String text, int maxLength) {
		if (text.length() > maxLength) {
			return false;
		}
		return true;
	}

	public static boolean checkExistCompany(String companyName) {
		return compLogic.findByCompanyName(companyName);

	}

	// loai bo dau tieng Viet
	public static String removeAccent(String text) {
		String temp = Normalizer.normalize(text, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
	}

	// loai bo dau cach
	public static String removeSpace(String text) {
		String sp = " ";
		String sp2 = "  ";
		while (text.contains(sp2)) {
			text = text.replace(sp2, sp);
		}
		return text;
	}

	// kiem tra ky tu la tin
	public static boolean checkNameFormat(String text) {
		try {
			String regexName = "^[_A-Za-z]$";
			boolean check = text.matches(regexName);
			return check;
		} catch (NullPointerException e) {
			return false;
		}
	}

	// format ky tu la tin
	public static String formatLatin(String text) {
		int length = text.length();
		String outString = "";
		for (int i = 0; i < length; i++) {
			char tmp = text.charAt(i);
			if (checkNameFormat(tmp + "") || " ".equals("" + tmp)) {
				outString += tmp;
			}
		}
		return outString;
	}

	// viet hoa chu cai dau tu
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

//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < text.length(); i++) {
//			char c = text.charAt(i);
//			switch (c) {
//			case '%':
//				sb.append("\\%");
//				break;
//			case '\\':
//				sb.append("\\\\");
//				break;
//			case '_':
//				sb.append("\\_");
//				break;
//			default:
//				sb.append(c);
//			}
//		}
		return text;
	}

}
