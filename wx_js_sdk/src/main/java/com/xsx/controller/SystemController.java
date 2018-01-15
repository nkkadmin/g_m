package com.xsx.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.xsx.constant.Constants;
import com.xsx.domain.AjaxJson;
import com.xsx.domain.Department;
import com.xsx.domain.Employee;
import com.xsx.domain.EmployeeExtension;
import com.xsx.domain.EmployeeOrderCount;
import com.xsx.domain.Ips;
import com.xsx.domain.Orders;
import com.xsx.domain.Page;
import com.xsx.util.DateHelper;
import com.xsx.util.Md5Utils;
import com.xsx.util.StringHelper;

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

	/**
	 * 生成员工后台二维码页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/employeeadmincoreUI", method = RequestMethod.GET)
	public ModelAndView employeeadmincoreUI(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/manager/employeeadmincore");
		String url = request.getScheme() + "://" + request.getServerName()
				+ request.getContextPath() + "/employee/loginUI";
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
			String message = loginImp(employee);
			if (message != null && message.equals("登录成功")) {
				json.setSuccess(true);
			} else {
				json.setSuccess(false);
			}
			json.setMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMessage("登陆失败，请联系开发人员！");
			json.setSuccess(false);
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
		try {
			String message = logOutImp(empId,Constants.CURRENTP_SESSION_COMPANY);
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
	 * 后台管理-首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("/manager/index");
		String menuStr = "[{}]";
		if(getSessionValue(Constants.CURRENTP_SESSION_COMPANY) != null){
			menuStr =  "[{\"id\" : \"6\",\"text\" : \"员工后台二维码\",\"href\" : \"employeeadmincoreUI\",\"closeable\" : false},"
					+ "{\"id\" : \"7\",\"text\" : \"部门管理\",\"href\" : \"departmentUI\",\"closeable\" : false},"
					+ "{\"id\" : \"8\",\"text\" : \"公司角色管理\",\"href\": \"companyUI\",\"closeable\" : false},"
					+ "{\"id\" : \"4\",\"text\" : \"员工信息管理\",\"href\": \"employeeUI\"},"
					+ "{\"id\" : \"3\",\"text\" : \"客户订单管理\",\"href\": \"orderUI\"},"
					+ "{\"id\" : \"12\",\"text\" : \"员工订单量统计\",\"href\": \"employeeordernumUI\"},"
					+ "{\"id\" : \"13\",\"text\" : \"域名管理\",\"href\" : \"ipsUI\"}]";
		}
		mv.addObject("menu", JSONObject.parse(menuStr));
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
		List<Department> list = departmentService.selectAllDepartment();
		mv.addObject("departmentList", list);
		return mv;
	}

	/**
	 * 员工订单量统计
	 * 
	 * @param type
	 *            统计类型：按部门统计（department），按个人统计（people）
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
					&& !StringHelper.isEmpty(employeeOrderCount.getEmpName())) {
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
	public ModelAndView employeeUI(Integer departmentId) {
		ModelAndView mv = new ModelAndView("/manager/employee/index");
		mv.addObject("departmentId", departmentId);
		return mv;
	}

	/**
	 * 修改
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/editOrderUI", method = RequestMethod.GET)
	public ModelAndView editOrderUI(Integer id) {
		ModelAndView mv = new ModelAndView("/manager/order/edit");
		Orders order = ordersService.selectByPrimaryKey(id);
		mv.addObject("order", order);
		return mv;
	}

	/**
	 * 修改订单
	 * 
	 * @param order
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/editOrder", method = RequestMethod.POST)
	public AjaxJson editOrder(Orders order) {
		AjaxJson json = new AjaxJson();
		try {
			if (order == null || order.getId() == null
					|| StringHelper.isEmpty(order.getReceiptphone())
					|| StringHelper.isEmpty(order.getReceiptaddress())) {
				json.setMessage("参数不合格!");
				json.setSuccess(false);
				return json;
			}
			ordersService.updateByPrimaryKeySelective(order);
			json.setMessage("修改成功");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMessage("修改失败，请联系开发人员!");
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
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
	public ModelAndView orderUI(Integer empId) {
		ModelAndView mv = new ModelAndView("/manager/order/index");
		mv.addObject("empId",empId);
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
					+ request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			if (employee != null && !StringHelper.isEmpty(employee.getName())) {
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
			if (employee != null && !StringHelper.isEmpty(employee.getName())) {
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
	 * @param employee
	 * @param roleDescriptName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEmp", method = RequestMethod.POST)
	public AjaxJson addEmp(Employee employee, String roleDescriptName) {
		AjaxJson json = new AjaxJson();
		try {
			if (employee == null || StringHelper.isEmpty(employee.getName())
					|| StringHelper.isEmpty(employee.getPassword())
					|| StringHelper.isEmpty(employee.getPhone())) {
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
			json.setMessage("保存失败，请联系开发人员!");
			json.setSuccess(false);
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
			json.setMessage("删除失败,请联系开发人员！");
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
				employee.setPassword(Md5Utils.encoderByMd5(employee.getName()
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
			json.setMessage("重置失败，请联系开发人员！");
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}
	
	//=====ips=====
	/**
	 * ip列表页
	 * @return
	 */
	@RequestMapping(value="/ipsUI",method=RequestMethod.GET)
	public ModelAndView ipsUI(){
		ModelAndView mv = new ModelAndView("/manager/ips/index");
		return mv;
	}
	
	/**
	 * 获取全部有效ip
	 * 
	 * @param ips
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/allIp", method = RequestMethod.POST)
	public Page<Ips> allIp(Ips ips, Page<Ips> page) {
		try {
			page = ipsService.selectAllOrders(ips, page);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 添加或修改ip
	 * @param ips
	 * @return
	 */
	@RequestMapping(value = "/editIpUI", method = RequestMethod.GET)
	public ModelAndView editIpUI(Integer id){
		ModelAndView mv = new ModelAndView("/manager/ips/edit");
		try {
			mv.addObject("ips", id == null ? null : ipsService.selectByPrimaryKey(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/**
	 * 编辑ip
	 * @param ips
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/editIp", method = RequestMethod.POST)
	public AjaxJson editIp(Ips ips){
		AjaxJson json = new AjaxJson();
		json.setSuccess(false);
		json.setMessage("操作失败");
		try {
			if(ips != null){
				if(ips.getId() != null && !ips.getId().equals("")){
					//修改
					Ips ip = ipsService.selectByPrimaryKey(ips.getId());
					ip.setName(ips.getName().trim());
					ipsService.updateByPrimaryKeySelective(ip);
					json.setSuccess(true);
					json.setMessage("修改成功");
				}else{
					//添加
					ips.setStatu(Constants.STATU_OK);
					ips.setCreatedate(DateHelper.nowDate());
					ipsService.insertSelective(ips);
					json.setSuccess(true);
					json.setMessage("添加成功");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("添加失败");
		}
		return json;
	}
	
	/**
	 * 刪除ip
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteIp", method = RequestMethod.POST)
	public AjaxJson deleteIp(Integer id){
		AjaxJson json = new AjaxJson();
		json.setSuccess(false);
		json.setMessage("刪除失败");
		try {
			if(id != null){
				Ips ips = ipsService.selectByPrimaryKey(id);
				if(ips != null){
					ipsService.deleteByPrimaryKey(id);
					json.setSuccess(true);
					json.setMessage("刪除成功");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("刪除失败");
		}
		return json;
	}
	
	
	
}
