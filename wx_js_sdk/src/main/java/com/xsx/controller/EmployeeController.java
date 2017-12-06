package com.xsx.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsx.constant.Constants;
import com.xsx.domain.AjaxJson;
import com.xsx.domain.Employee;
import com.xsx.domain.EmployeeOrderCount;
import com.xsx.domain.Orders;

/**
 * 
 * @Title: EmployeeController.java
 * @Package com.xsx.controller
 * @Description: 员工Controller
 * @author xsx
 * @date 2017年10月31日 下午1:49:06
 * @version V1.0
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {


	@RequestMapping(value = "/loginUI", method = RequestMethod.GET)
	public ModelAndView loginUI() {
		return new ModelAndView("employee/login");
	}

	/**
	 * 登录
	 * 
	 * @param employee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public AjaxJson login(Employee employee) {
		AjaxJson json = new AjaxJson();
		try {
			String message = loginImp(employee, Constants.ROLE_EMPLOYEE,
					Constants.CURRENTP_SESSION_EMP);
			if (message != null && message.equals("登录成功")) {
				json.setSuccess(true);
			} else {
				json.setSuccess(false);
			}
			json.setMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 后台首页
	 * 
	 * @param empId
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("employee/index");
		EmployeeOrderCount employeeOrderCount = null;
		if (getCurrentEmpSession() != null) {
			Integer empId = getCurrentEmpSession().getId();
			employeeOrderCount = new EmployeeOrderCount();
			employeeOrderCount.setTodayCount(ordersService
					.todayOrderCount(empId));
			employeeOrderCount.setYesterCount(ordersService
					.yesterOrderCount(empId));
			employeeOrderCount.setTheMonthCount(ordersService
					.theMonthOrderCount(empId));
			employeeOrderCount.setLastMonthCount(ordersService
					.lastMonthOrderCount(empId));
		}
		mv.addObject("employeeOrderCount", employeeOrderCount);
		return mv;
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logOut", method = RequestMethod.POST)
	public AjaxJson logOut(Integer empId) {
		AjaxJson json = new AjaxJson();
		try {
			String message = logOutImp(empId, Constants.CURRENTP_SESSION_EMP);
			if (message != null && message.equals("退出成功")) {
				json.setSuccess(true);
			} else {
				json.setSuccess(false);
			}
			json.setMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMessage("退出失败");
			json.setSuccess(false);
		}
		return json;
	}

	/**
	 * 根据员工id查看员工订单
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "/selectOrders", method = RequestMethod.GET)
	public ModelAndView selectOrders(Integer empId) {
		ModelAndView mv = new ModelAndView("employee/orders");
		try {
			List<Orders> list = ordersService.selectOrderByEmpId(empId);
			mv.addObject("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 获取推广链接
	 * 
	 * @param empId
	 * @return
	 */
	@RequestMapping(value = "/selectEmpExtensionUrl", method = RequestMethod.GET)
	public ModelAndView selectEmpExtensionUrl(Integer empId) {
		ModelAndView mv = new ModelAndView("extensionUrl");
		try {
			Employee employee = getCurrentEmpSession();
			if (employee != null && employee.getId().equals(empId)) {
				mv.addObject("extensionUrl", employee.getExtensionurl());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 获取推广二维码
	 * 
	 * @param empId
	 * @return
	 */
	@RequestMapping(value = "/selectEmpExtensionCore", method = RequestMethod.GET)
	public ModelAndView selectEmpExtensionCore(Integer empId) {
		ModelAndView mv = new ModelAndView("extensionCore");
		try {
			Employee employee = getCurrentEmpSession();
			if ( employee != null && employee.getId().equals(empId)) {
				mv.addObject("extensionCore", employee.getExtensioncore());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

}
