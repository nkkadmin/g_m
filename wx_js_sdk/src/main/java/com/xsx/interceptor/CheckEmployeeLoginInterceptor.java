package com.xsx.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xsx.constant.Constants;
import com.xsx.domain.Employee;

/**
 * 
 * @Title: CheckEmployeeLoginInterceptor.java
 * @Package com.xsx.annotation
 * @Description: 员工后台拦截器
 * @author xsx
 * @date 2017年10月31日 下午5:28:43
 * @version V1.0
 */
public class CheckEmployeeLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();
		if(requestURI != null && (requestURI.endsWith("employee/loginUI") || requestURI.endsWith("employee/login"))){
			return true;
		}
		Employee employee = (Employee) request.getSession().getAttribute(Constants.CURRENTP_SESSION_EMP);
		if(employee != null){
			return true;
		}
		dealUnLogin(request,response);
		return false;
	}

	private void dealUnLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setHeader(Constants.CURRENTP_SESSION_EMP, "timeout");
		response.setContentType("application/json;charset=UTF-8");
		response.sendRedirect(request.getContextPath() + "/employee/loginUI");
	}

}
