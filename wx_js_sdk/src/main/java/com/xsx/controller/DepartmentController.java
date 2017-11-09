package com.xsx.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xsx.constant.Constants;
import com.xsx.domain.AjaxJson;
import com.xsx.domain.Department;
import com.xsx.domain.Employee;
import com.xsx.domain.Page;
import com.xsx.util.DateHelper;

/**
 * 
 * @Title: DepartmentController.java
 * @Package com.xsx.controller
 * @Description: 部门
 * @author xsx
 * @date 2017年11月2日 上午10:03:03
 * @version V1.0
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {

	/**
	 * 部门列表
	 * 
	 * @param department
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/allDepartment", method = RequestMethod.POST)
	public Page<Department> allDepartment(Department department,
			Page<Department> page) {
		try {
			page = departmentService.selectAllDepartment(department, page);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加部门UI
	 * 
	 * @param department
	 * @return
	 */
	@RequestMapping(value = "/addDepartmentUI", method = RequestMethod.GET)
	public ModelAndView addDepartmentUI() {
		ModelAndView mv = new ModelAndView("/manager/department/edit");
		return mv;
	}

	/**
	 * 添加部门
	 * 
	 * @param department
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
	public AjaxJson addDepartment(Department department) {
		AjaxJson json = new AjaxJson();
		try {
			if (department == null || department.getName() == null || (department.getName()!= null && department.getName().trim().equals(""))) {
				json.setMessage("添加失败，部门名称不能为空");
				json.setSuccess(false);
				return json;
			}
			//判断该部门名称是否存在
			Department dep = departmentService.selectByName(department.getName());
			if(dep != null){
				json.setMessage("添加失败，该部门已经存在！");
				json.setSuccess(false);
				return json;
			}
			department.setCreatedate(DateHelper.nowDate());
			department.setStatu(Constants.STATU_OK);
			departmentService.insertSelective(department);
			json.setMessage("添加成功");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMessage("添加失败");
			json.setSuccess(false);
		}
		return json;
	}

	/**
	 * 删除部门
	 * 
	 * @param empId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteDepartment", method = RequestMethod.GET)
	public AjaxJson deleteDepartment(Integer id) {
		AjaxJson json = new AjaxJson();
		try {
			// 判断该部门下是否有员工
			List<Employee> list = employeeService.selectByDepartmentId(id);
			if (list != null && list.size() > 0) {
				json.setMessage("该部门下存在员工信息，不能删除，需要转移该部门下的员工后才能删除！");
				json.setSuccess(false);
				return json;
			}
			if (departmentService.deleteByPrimaryKey(id) == 1) {
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
	 * 
	 * @param fromDepartmentId
	 * @param toDepartmentName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeEmpToOtherDepartment", method = RequestMethod.POST)
	public AjaxJson changeEmpToOtherDepartment(Integer fromDepartmentId,
			String toDepartmentName) {
		AjaxJson json = new AjaxJson();
		try {
			Department department = departmentService
					.selectByName(toDepartmentName);
			if (department != null) {
				// 原始部门下的所有员工
				List<Employee> oldList = employeeService
						.selectByDepartmentId(fromDepartmentId);
				employeeService.changeDepartment(oldList, department.getId());
				json.setMessage("转移成功");
				json.setSuccess(true);
				return json;
			} else {
				json.setMessage("【" + toDepartmentName + "】该部门不存在");
				json.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
