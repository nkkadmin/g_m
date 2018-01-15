package com.xsx.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xsx.constant.Constants;
import com.xsx.domain.Employee;
import com.xsx.domain.EmployeeOrderCount;
import com.xsx.domain.Page;
import com.xsx.domain.Role;
import com.xsx.mapper.EmployeeMapper;
import com.xsx.mapper.OrdersMapper;
import com.xsx.mapper.RoleMapper;
import com.xsx.service.EmployeeService;
import com.xsx.service.IpsService;
import com.xsx.util.DateHelper;
import com.xsx.util.Md5Utils;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	private EmployeeMapper employeeMapper;
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private IpsService ipsService;
	@Resource
	private OrdersMapper ordersMapper;
	

	@Override
	public int deleteByPrimaryKey(Integer id) {
		if (id == null) {
			return 0;
		}
		Employee employee = new Employee();
		employee.setId(id);
		employee.setStatu(Constants.STATU_DELETE);
		return employeeMapper.updateByPrimaryKeySelective(employee);
	}


	@Override
	public int insertSelective(Employee employee,String roleDescriptName) {
		if (employee == null) {
			return 0;
		}
		if (!isHashEmpName(employee)) {
			return 0;
		}
		employee = setAttrEmp(employee,roleDescriptName);
		int num = employeeMapper.insertSelective(employee);
		return num;
	}

	/**
	 * 判断该用户名是否存在
	 * 
	 * @return
	 */
	private boolean isHashEmpName(Employee employee) {
		Employee emp = employeeMapper.selectByName(employee.getName());
		if (emp != null) {
			return false;
		}
		return true;
	}

	private Employee setAttrEmp(Employee employee,String roleDescriptName) {
		if (employee == null) {
			return null;
		}
		if (employee.getRoleid() == null) {
			Role role = roleMapper.selectByDescript(roleDescriptName);
			employee.setRoleid(role.getId());
		}
		if (employee.getPassword() != null) {
			employee.setPassword(Md5Utils.encoderByMd5(employee.getName()
					+ employee.getPassword()));
		}
		employee.setCreatedate(DateHelper.nowDate());
		employee.setImg(Constants.IMG_DEFAULTS[(int) (Math.random() * 3)]);
		employee.setStatu(Constants.STATU_OK);
		employee.setExtensionrandomcode(UUID.randomUUID().toString()
				.replaceAll("-", ""));
		return employee;
	}

	@Override
	public Employee selectByPrimaryKey(Integer id) {
		return employeeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Employee record) {
		return employeeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Employee record) {
		return employeeMapper.updateByPrimaryKey(record);
	}

	@Override
	public int countAll() {
		return employeeMapper.countAll();
	}

	@Override
	public Employee selectByName(String name) {
		return employeeMapper.selectByName(name);
	}

	@Override
	public Page<Employee> selectAllEmp(Employee employee,String roleDescriptName, Page<Employee> page,String hostName) {
		if(roleDescriptName == null){
			roleDescriptName = Constants.ROLE_EMPLOYEE;
		}
		List<Employee> list = employeeMapper.selectAllEmp(
				employee != null ? employee.getName() : null,
				employee != null ? employee.getDepartmentid() : null,
				roleDescriptName, page);
		for(Employee e :list){
			//生成分享链接
			e.setExtensionurl(hostName + "/shop/info/"+e.getId()+"/"+e.getExtensionrandomcode()+"/"+new Date().getTime()+"/f");
		}
		page.setRows(list);
		return page;
	}

	@Override
	public List<Employee> selectByDepartmentId(Integer departmentId) {
		return employeeMapper.selectByDepartmentId(departmentId);
	}

	@Override
	public int changeDepartment(List<Employee> oldList, Integer id) {
		try {
			for (Employee e : oldList) {
				e.setDepartmentid(id);
				employeeMapper.updateByPrimaryKeySelective(e);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Page<EmployeeOrderCount> statisticsEmployeeOrder(EmployeeOrderCount employeeOrderCount, Page<EmployeeOrderCount> page) {
		if(employeeOrderCount != null && employeeOrderCount.getType() != null
				&& employeeOrderCount.getType().equals("people")){
			List<EmployeeOrderCount> list = employeeMapper.statisticsEmployeeOrderByPeople(employeeOrderCount,page);
			for(EmployeeOrderCount emp : list){
				emp.setTodayCount(ordersMapper.todayOrderCount(emp.getEmpId()));
				emp.setTheMonthCount(ordersMapper.theMonthOrderCount(emp.getEmpId()));
			}
		}
		if(employeeOrderCount != null && employeeOrderCount.getType() != null
				&& employeeOrderCount.getType().equals("department")){
			List<EmployeeOrderCount> list = employeeMapper.statisticsEmployeeOrderByDepartment(employeeOrderCount,page);
			for(EmployeeOrderCount emp : list){
				emp.setEmpName("-");
				//获取该部门全部员工
				List<Employee> emps = employeeMapper.selectByDepartmentId(emp.getDepartmentId());
				String empIds = "";
				for(Employee employee : emps){
					empIds += employee.getId()+",";
				}
				empIds = empIds.equals("") ? "0" : "(" + empIds.substring(0, empIds.length()-1) + ")";
				emp.setTodayCount(ordersMapper.todayOrderCountByEmpIds(empIds));
				emp.setTheMonthCount(ordersMapper.theMonthOrderCountByEmpIds(empIds));
			}
		}
		return page;
	}
}
