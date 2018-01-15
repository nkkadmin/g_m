package com.xsx.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xsx.constant.Constants;
import com.xsx.domain.AjaxJson;
import com.xsx.domain.Employee;
import com.xsx.domain.EmployeeExtension;
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
			String message = loginImp(employee);
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
	
	/**
	 * 员工微信链接内容页面，员工角色可以访问
	 * @return
	 */
	@RequestMapping(value = "/extension", method = RequestMethod.GET)
	public ModelAndView extension(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("/employee/extension");
		Employee sessionEmp = (Employee) getSessionValue(Constants.CURRENTP_SESSION_EMP);
		Integer employeeId = sessionEmp.getId();
		EmployeeExtension employeeExtension = employeeExtensionService.selectByEmployeeId(employeeId);
		mv.addObject("info", employeeExtension);
		//二维码链接地址
		String url = request.getScheme() + "://" + request.getServerName()
				+ request.getContextPath() + "/shop/extensionJump/"+employeeId+"/"+new Date().getTime();
		mv.addObject("url", url);
		return mv;
	}
	
	/**
	 * 编辑员工微信链接内容，员工角色可以访问
	 * @return
	 *//*
	@RequestMapping(value = "/editExtension", method = RequestMethod.POST)
	public ModelAndView editExtension(EmployeeExtension employeeExtension){
		try {
			Employee sessionEmp = (Employee) getSessionValue(Constants.CURRENTP_SESSION_EMP);
			employeeExtension.setContent(new String(employeeExtension.getContent().getBytes("ISO8859-1"),"UTF-8"));
			employeeExtension.setEmployeeid(sessionEmp.getId());
			employeeExtensionService.editContent(employeeExtension);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:employeeExtensionInfo");
	}*/
	
	/**
	 * 文件上传
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public AjaxJson uploadFile(MultipartFile file){
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.setSuccess(false);
		try {
			String oldName = file.getOriginalFilename();
			String webRootDir = getRequest().getRealPath("/");
			String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."),oldName.length());
			String savePath = webRootDir + "/attached/" + newName;
			File saveFile = new File(savePath);
			file.transferTo(saveFile);
			//保存到数据库
			Employee sessionEmp = (Employee) getSessionValue(Constants.CURRENTP_SESSION_EMP);
			EmployeeExtension employeeExtension = employeeExtensionService.selectByEmployeeId(sessionEmp.getId());
			String oldUrl = null;
			if(employeeExtension != null){
				//获取原图片路径
				oldUrl = employeeExtension.getContent();
			}else{
				employeeExtension = new EmployeeExtension();
			}
			String saveDbPath = getRequest().getScheme() + "://" + getRequest().getServerName();
	        if (getRequest().getServerPort() != 80) {
	        	saveDbPath += ":" + getRequest().getServerPort();
	        }
	        saveDbPath += "/attached/" + newName;
			employeeExtension.setContent(saveDbPath);
			employeeExtension.setEmployeeid(sessionEmp.getId());
			employeeExtensionService.editContent(employeeExtension);
			if(oldUrl != null){
				oldUrl = webRootDir + "attached\\" + oldUrl.substring(oldUrl.lastIndexOf("/"), oldUrl.length());
				//删除图片
				File oldFile = new File(oldUrl);
				oldFile.delete();
			}
			ajaxJson.setSuccess(true);
			return ajaxJson;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ajaxJson;
	}
}
