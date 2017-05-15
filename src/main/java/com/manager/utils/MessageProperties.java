package com.manager.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageProperties {
	public static String getMSS(String key) {
		String data = "";
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(new File(
					"D:\\My projects\\training-springboot\\src\\main\\java\\com\\manager\\properties\\Message.properties")));
			// InputStream input =
			// MessageProperties.class.getResourceAsStream("/properties/Message.properties");
			// InputStream input =
			// MessageProperties.class.getClassLoader().getResourceAsStream("Message.properties");
			// URL location =
			// MessageProperties.class.getProtectionDomain().getCodeSource().getLocation();

			// String filePath = location.getPath().substring(1,
			// location.getPath().length());

			// InputStream input =
			// MessageProperties.class.getResourceAsStream(filePath +
			// "/Message.properties");

			// .load(input);
			System.out.println(new File(
					"D:\\My projects\\training-springboot\\src\\main\\java\\com\\manager\\properties\\Message.properties")
							.getAbsolutePath().toString());
			data = properties.getProperty(key);
			// input.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		return data;
	}
}
