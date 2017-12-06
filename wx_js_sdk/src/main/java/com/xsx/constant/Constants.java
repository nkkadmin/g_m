package com.xsx.constant;

/**
 * 
 * @Title: Constants.java
 * @Package com.xsx.constant
 * @Description: 常量定义
 * @author xsx
 * @date 2017年10月31日 上午11:36:49
 * @version V1.0
 */
public class Constants {

	private Constants() {
	}

	// 状态
	public final static int STATU_OK = 1;
	public final static int STATU_DELETE = 0;

	// 角色
	public final static String ROLE_COMMPANY = "COMPANY";
	public final static String ROLE_EMPLOYEE = "EMPLOYEE";
	public final static String ROLE_CUSTOMER = "CUSTOMER";
	
	//头像
	public final static String IMG_DEFAULT_1 = "img/headpro/default_1.png";
	public final static String IMG_DEFAULT_2 = "img/headpro/default_2.png";
	public final static String IMG_DEFAULT_3 = "img/headpro/default_3.png";
	public final static String[] IMG_DEFAULTS = {IMG_DEFAULT_1,IMG_DEFAULT_2,IMG_DEFAULT_3};
	
	//员工后台登录
	public final static String CURRENTP_SESSION_EMP = "currentSessionEmp";
	//公司角色后台登录
	public final static String CURRENTP_SESSION_COMPANY = "currentSessionCompany";
	
	public final static String WXTOKENANDTICKETSESSION = "wxTokenAndTicketSession";
	
}
