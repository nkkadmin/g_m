package com.xsx.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsx.constant.Constants;
import com.xsx.domain.AjaxJson;
import com.xsx.domain.Department;
import com.xsx.domain.Employee;
import com.xsx.domain.EmployeeOrderCount;
import com.xsx.domain.Page;
import com.xsx.util.Md5Utils;

/**
 * 
 * @Title: SystemController.java
 * @Package com.xsx.controller
 * @Description:公司权限后台
 * @author xsx
 * @date 2017年11月1日 上午9:38:55
 * @version V1.0
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {

	@RequestMapping(value = "/employeeadmincoreUI", method = RequestMethod.GET)
	public ModelAndView employeeadmincoreUI(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/manager/employeeadmincore");
		String url = request.getScheme() + "://" + request.getServerName()
				+ request.getContextPath();
		mv.addObject("url", url);
		return mv;
	}

	/**
	 * 后台登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginUI", method = RequestMethod.GET)
	public ModelAndView loginUI() {
		ModelAndView mv = new ModelAndView("/manager/login");
		return mv;
	}

	/**
	 * 公司账号登录
	 * 
	 * @param employee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public AjaxJson login(Employee employee) {
		AjaxJson json = new AjaxJson();
		try {
			String message = loginImp(employee, Constants.ROLE_COMMPANY,
					Constants.CURRENTP_SESSION_COMPANY);
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
	 * 退出登录
	 * 
	 * @param empId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public AjaxJson logOut(Integer empId) {
		AjaxJson json = new AjaxJson();
		String message = logOutImp(empId, Constants.CURRENTP_SESSION_COMPANY);
		if (message != null && message.equals("退出成功")) {
			json.setSuccess(true);
		} else {
			json.setSuccess(false);
		}
		json.setMessage(message);
		return json;
	}

	/**
	 * 后台管理-首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("/manager/index");
		return mv;
	}

	/**
	 * 员工订单量统计
	 * 
	 * @return
	 */
	@RequestMapping(value = "/employeeordernumUI", method = RequestMethod.GET)
	public ModelAndView employeeordernumUI() {
		ModelAndView mv = new ModelAndView("/manager/employeeordernum/index");
		return mv;
	}

	/**
	 * 员工订单量统计
	 * 
	 * @param employeeOrderCount
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/employeeordernum", method = RequestMethod.POST)
	public Page<EmployeeOrderCount> employeeordernum(
			EmployeeOrderCount employeeOrderCount, Page<EmployeeOrderCount> page) {
		try {
			if (employeeOrderCount != null
					&& employeeOrderCount.getEmpName() != null
					&& !employeeOrderCount.getEmpName().equals("")) {
				employeeOrderCount.setEmpName("%"
						+ new String(employeeOrderCount.getEmpName().getBytes(
								"iso-8859-1"), "utf-8") + "%");
			}
			page = employeeService.statisticsEmployeeOrder(employeeOrderCount,
					page);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 后台管理-员工管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/employeeUI", method = RequestMethod.GET)
	public ModelAndView employeeUI() {
		ModelAndView mv = new ModelAndView("/manager/employee/index");
		return mv;
	}

	/**
	 * 后台首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public ModelAndView company() {
		ModelAndView mv = new ModelAndView("/manager/index");
		return mv;
	}

	/**
	 * 订单页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/orderUI", method = RequestMethod.GET)
	public ModelAndView orderUI() {
		ModelAndView mv = new ModelAndView("/manager/order/index");
		return mv;
	}

	/**
	 * 部门页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/departmentUI", method = RequestMethod.GET)
	public ModelAndView departmentUI() {
		ModelAndView mv = new ModelAndView("/manager/department/index");
		return mv;
	}

	/**
	 * 公司角色用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/companyUI", method = RequestMethod.GET)
	public ModelAndView companyUI() {
		ModelAndView mv = new ModelAndView("/manager/company/index");
		return mv;
	}

	/**
	 * 获取全部员工
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectAllEmp", method = RequestMethod.POST)
	public Page<Employee> selectAllEmp(Employee employee, Page<Employee> page,
			HttpServletRequest request) {
		try {

			String hostName = request.getScheme() + "://"
					+ request.getServerName() + request.getContextPath();
			if (employee != null && employee.getName() != null
					&& !employee.getName().equals("")) {
				employee.setName("%"
						+ new String(employee.getName().getBytes("iso-8859-1"),
								"utf-8") + "%");
			}
			page = employeeService.selectAllEmp(employee,
					Constants.ROLE_EMPLOYEE, page, hostName);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取全部公司角色账号
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectAllCommpany", method = RequestMethod.POST)
	public Page<Employee> selectAllCommpany(Employee employee,
			Page<Employee> page, HttpServletRequest request) {
		try {
			if (employee != null && employee.getName() != null
					&& !employee.getName().equals("")) {
				employee.setName("%"
						+ new String(employee.getName().getBytes("iso-8859-1"),
								"utf-8") + "%");
			}
			String hostName = request.getScheme() + "://"
					+ request.getServerName() + request.getContextPath();
			page = employeeService.selectAllEmp(employee,
					Constants.ROLE_COMMPANY, page, hostName);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加员工
	 * 
	 * @param type
	 *            添加角色类型
	 * @return
	 */
	@RequestMapping(value = "/addEmpUI", method = RequestMethod.GET)
	public ModelAndView addEmpUI(String type) {
		String url = null;
		if (type != null && type.equals("employee")) {
			url = "/manager/employee/edit";
		} else {
			url = "/manager/company/edit";
		}
		ModelAndView mv = new ModelAndView(url);
		try {
			List<Department> list = departmentService.selectAllDepartment();
			mv.addObject("departments", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 添加员工
	 * 
	 * @param employee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEmp", method = RequestMethod.POST)
	public AjaxJson addEmp(Employee employee, String roleDescriptName) {
		AjaxJson json = new AjaxJson();
		try {
			if (employee == null || employee.getName() == null
					|| employee.getPassword() == null
					|| employee.getPhone() == null) {
				json.setMessage("参数不合格!");
				json.setSuccess(false);
				return json;
			}
			if (employeeService.selectByName(employee.getName()) != null) {
				json.setMessage("该用户名已存在，请更换!");
				json.setSuccess(false);
				return json;
			}
			employeeService.insertSelective(employee, roleDescriptName);
			json.setMessage("保存成功");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 删除员工
	 * 
	 * @param empId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteEmp", method = RequestMethod.GET)
	public AjaxJson deleteEmp(Integer empId) {
		AjaxJson json = new AjaxJson();
		try {
			if (employeeService.deleteByPrimaryKey(empId) == 1) {
				json.setMessage("删除成功");
				json.setSuccess(true);
			} else {
				json.setMessage("删除失败");
				json.setSuccess(false);
			}
		} catch (Exception e) {
			json.setMessage("删除失败");
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 重置密码
	 * 
	 * @param empId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public AjaxJson resetPassword(Integer empId, String resetPassowrd) {
		AjaxJson json = new AjaxJson();
		try {
			Employee employee = employeeService.selectByPrimaryKey(empId);
			if (employee != null) {
				employee.setPassword(Md5Utils.EncoderByMd5(employee.getName()
						+ resetPassowrd));
				if (employeeService.updateByPrimaryKeySelective(employee) == 1) {
					json.setMessage("重置成功");
					json.setSuccess(true);
					return json;
				}
			}
			json.setMessage("重置失败");
			json.setSuccess(false);
		} catch (Exception e) {
			json.setMessage("重置失败");
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}
}
