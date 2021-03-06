package com.xsx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xsx.domain.Employee;
import com.xsx.domain.EmployeeOrderCount;
import com.xsx.domain.Page;

public interface EmployeeMapper {

	int insert(Employee record);

	int insertSelective(Employee record);

	Employee selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Employee record);

	int updateByPrimaryKey(Employee record);

	int countAll();

	Employee selectByName(@Param("name") String name);

	/**
	 * 获取全部员工
	 * 
	 * @return
	 */
	List<Employee> selectAllEmp(@Param("name") String name,@Param("departmentId") Integer departmentId,@Param("roleDescriptName") String roleDescriptName,Page<Employee> page);
	
	/**
	 * 根据部门id查询用户
	 * @param departmentId
	 * @return
	 */
	List<Employee> selectByDepartmentId(@Param("departmentId") Integer departmentId);
	
	/**
	 * 统计员工订单量:按个人统计
	 * @return
	 */
	public List<EmployeeOrderCount> statisticsEmployeeOrderByPeople(@Param("employeeOrderCount") EmployeeOrderCount employeeOrderCount,Page<EmployeeOrderCount> page);
	
	/**
	 *  统计员工订单量:按部门统计
	 * @param employeeOrderCount
	 * @param page
	 * @return
	 */
	public List<EmployeeOrderCount> statisticsEmployeeOrderByDepartment(@Param("employeeOrderCount") EmployeeOrderCount employeeOrderCount,Page<EmployeeOrderCount> page);
	
}

