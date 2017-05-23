package com.manager.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MessageProperties {
	public static String getMSS(String key) {
		String data = "";
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(new File(
					"D:\\My projects\\training-springboot\\src\\main\\java\\com\\manager\\properties\\Message.properties")));
			data = properties.getProperty(key);
		} catch (IOException e) {
			return "";
		}

		return data;
	}
}
