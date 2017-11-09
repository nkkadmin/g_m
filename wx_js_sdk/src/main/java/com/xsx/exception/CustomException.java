package com.xsx.exception;


/**
 * 
 * @Title: CustomException.java 
 * @Package com.xsx.customexception 
 * @Description: 自定义异常
 * @author xsx
 * @date 2017年11月7日 下午2:34:47 
 * @version V1.0
 */
public class CustomException extends Exception{

	private String message;
	
	public CustomException(String message){
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
