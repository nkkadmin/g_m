package com.xsx.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xsx.constant.Constants;
import com.xsx.domain.Employee;
import com.xsx.domain.EmployeeOrderCount;
import com.xsx.domain.Page;
import com.xsx.mapper.EmployeeMapper;
import com.xsx.mapper.RoleMapper;
import com.xsx.service.EmployeeService;
import com.xsx.util.DateHelper;
import com.xsx.util.Md5Utils;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	private EmployeeMapper employeeMapper;
	@Resource
	private RoleMapper roleMapper;


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
			employee.setRoleid(roleMapper.selectByDescript(roleDescriptName).getId());
		}
		if (employee.getPassword() != null) {
			employee.setPassword(Md5Utils.EncoderByMd5(employee.getName()
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
				employee != null ? employee.getName() : null,roleDescriptName, page);
		for(Employee e :list){
			e.setExtensionurl(hostName + "/shop/info?empId="+e.getId()+"&code="+e.getExtensionrandomcode());
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
		employeeMapper.statisticsEmployeeOrder(employeeOrderCount != null ? employeeOrderCount.getEmpName() : null,page);
		return page;
	}
}
