package com.xsx.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * 
 * @Title: Md5Utils.java
 * @Package com.xsx.util
 * @Description: md5加密
 * @author xsx
 * @date 2017年11月1日 下午3:17:59
 * @version V1.0
 */
public class Md5Utils {
	

	/**
	 * 利用MD5进行加密 　　
	 * @param str 待加密的字符串 　　
	 * @return 加密后的字符串 　　
	 * @throws
	 * NoSuchAlgorithmException 没有这种产生消息摘要的算法 　　 
	 * @throws
	 * UnsupportedEncodingException 　　
	 */
	public static String encoderByMd5(String str){
		if(str == null){
			return "";
		}
		try {
			// 确定计算方法
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			// 加密后的字符串
			String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
			return newstr;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
