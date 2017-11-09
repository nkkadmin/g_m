package com.xsx.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpAttributeUtils {
	
	private HttpAttributeUtils(){}

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder
				.getRequestAttributes())).getRequest();
		return request;
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static void setSession(String name, Object value) {
		setSession(name, value, null);
	}

	public static void setSession(String name, Object value,Integer maxInactive) {
		HttpSession session = getSession();
		if(maxInactive != null){
			session.setMaxInactiveInterval(maxInactive);
		}
		session.setAttribute(name, value);
	}

	public static Object getSessionValue(String name) {
		return getSession().getAttribute(name);
	}
}
