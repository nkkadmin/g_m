package com.xsx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 * 
 * @author xsx
 *
 */
public class DateHelper {


	/**
	 * 获取当前时间 返回格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String nowDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date()).toString();
	}
	
	/**
	 * 获取当前时间 返回格式：yyyy-MM-dd
	 * @return
	 */
	public static String formDate(String patternStr) {
		SimpleDateFormat df = new SimpleDateFormat(patternStr);
		return df.format(new Date()).toString();
	}
	
	/**
	 * 判断今天是否在指定日期范围内
	 * @param preDate  指定日期
	 * @param day 	      指定天数
	 * @return
	 */
	public static boolean judgeToDayIsRangeDate(String preDate){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = df.parse(preDate);
			//开始日期
			Calendar startTime = new GregorianCalendar();
			startTime.setTime(date);
			startTime.add(startTime.DATE, 21);
			//结束日期
			Calendar endTime = new GregorianCalendar();
			endTime.setTime(date);
			endTime.add(endTime.DATE, 35);
			//今天
			Calendar time = new GregorianCalendar();
			time.setTime(new Date());
			if(time.after(startTime) && time.before(endTime)){
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
