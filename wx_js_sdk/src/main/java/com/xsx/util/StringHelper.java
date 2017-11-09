package com.xsx.util;

/**
 * 字符串工具类
 * @author xsx
 *
 */
public class StringHelper {

	/**
	 * 字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str == null || str.equals("")){
			return true;
		}
		return false;
	}
}
