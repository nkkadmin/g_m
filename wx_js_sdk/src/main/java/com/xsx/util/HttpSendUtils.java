package com.xsx.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * @Title: HttpSendUtils.java
 * @Package com.xsx.util
 * @Description: 用于发送http请求
 * @author xsx
 * @date 2017年10月30日 上午11:47:58
 * @version V1.0
 */
public class HttpSendUtils {

	private HttpSendUtils() {
	}


	public static String sendGet(String url) throws MalformedURLException,
			IOException {
System.out.println("....send...GET...." + url);
		HttpURLConnection connection = (HttpURLConnection) new URL(url)
				.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept-Charset", "utf-8");
		connection.setRequestProperty("contentType", "utf-8");
		connection.setReadTimeout(5000);
		if (connection.getResponseCode() == 200) {
			String responseContent = readerContent(connection.getInputStream());
			return responseContent;
		}
		return null;
	}

	private static String readerContent(InputStream inputStream) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int len = -1;
		byte[] by = new byte[1024];
		try {
			while ((len = inputStream.read(by)) != -1) {
				bos.write(by, 0, len);
				bos.flush();
			}
			return bos.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
