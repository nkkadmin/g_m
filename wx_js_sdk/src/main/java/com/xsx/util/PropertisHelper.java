package com.xsx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Set;

/**
 * propertis操作类
 * 
 * @author xsx
 *
 */
public class PropertisHelper {

	/**
	 * 写
	 * @param key
	 * @param value
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static boolean writer(String key, String value, String fileName) {
		Properties p = new Properties();
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(PropertisHelper.class.getClassLoader().getResource(fileName).getFile());
			p.load(is);
			os = new FileOutputStream(PropertisHelper.class.getClassLoader().getResource(fileName).getFile());
			p.setProperty(key, value);
			p.store(os, key);
			os.flush();
			os.close();
			System.out.println(fileName + " 成功");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				if (null != is)
					is.close();
				if (null != os)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 读
	 * @param fileName
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String read(String fileName,String key){
		Properties properties = new Properties();
		InputStream in = null;
		try {
			in = PropertisHelper.class.getClassLoader().getResourceAsStream(fileName);
			properties.load(in);
			/*Set<String> keys = properties.stringPropertyNames();
			Object value = null;
			for(String keyName : keys){
				value = properties.get(keyName);
			}*/
			String value = properties.getProperty(key);
			return value != null ? value.toString() : null;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 读取全部数据
	 * @param fileName
	 * @return
	 */
	public static String[] readAll(String fileName){
		Properties properties = new Properties();
		InputStream in = null;
		try {
			in = PropertisHelper.class.getClassLoader().getResourceAsStream(fileName);
			properties.load(in);
			Set<String> keys = properties.stringPropertyNames();
			String[] value = null;
			int num = 0;
			for(String keyName : keys){
				value[num] = properties.getProperty(keyName);
				num ++;
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}
