package com.xsx.service;

import java.util.List;

import com.xsx.domain.Employee;
import com.xsx.domain.EmployeeOrderCount;
import com.xsx.domain.Page;

public interface EmployeeService {

	int deleteByPrimaryKey(Integer id);

	int insertSelective(Employee record, String roleDescriptName);

	Employee selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Employee record);

	int updateByPrimaryKey(Employee record);

	int countAll();

	Employee selectByName(String name);
	
	/**
	 * 获取全部员工
	 * @param employee
	 * @param roleDescriptName 角色说明
	 * @param page
	 * @param hostName  域名
	 * @return
	 */
	Page<Employee> selectAllEmp(Employee employee,String roleDescriptName,Page<Employee> page,String hostName);
	
	/**
	 * 根据部门id查询用户
	 * @param departmentId
	 * @return
	 */
	List<Employee> selectByDepartmentId(Integer departmentId);

	int changeDepartment(List<Employee> oldList, Integer id);
	
	/**
	 * 统计员工订单量
	 * @return
	 */
	public Page<EmployeeOrderCount> statisticsEmployeeOrder(EmployeeOrderCount employeeOrderCount, Page<EmployeeOrderCount> page);
}
