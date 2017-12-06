package com.xsx.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 * @Title: RequestUtils.java 
 * @Package com.xsx.util 
 * @Description: request相关工具类
 * @author xsx
 * @date 2017年11月7日 下午2:29:21 
 * @version V1.0
 */
public class RequestUtils {

	private RequestUtils(){}
	
	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknow".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();

			if (ipAddress.equals("127.0.0.1")
					|| ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡获取本机配置的IP地址
				InetAddress inetAddress = null;
				try {
					inetAddress = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inetAddress != null ? inetAddress.getHostAddress() : null;
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实的IP地址，多个IP按照','分割
		if (null != ipAddress && ipAddress.length() > 15) {
			// "***.***.***.***".length() = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}

		return ipAddress;
	}
}
