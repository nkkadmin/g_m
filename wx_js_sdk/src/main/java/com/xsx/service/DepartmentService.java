package com.xsx.service;

import java.util.List;

import com.xsx.domain.Department;
import com.xsx.domain.Page;

public interface DepartmentService {

	int insertSelective(Department record);

	Department selectByPrimaryKey(Integer id);
	
	Department selectByName(String name);

	int updateByPrimaryKeySelective(Department record);

	int deleteByPrimaryKey(Integer id);

	List<Department> selectAllDepartment();
	
	Page<Department> selectAllDepartment(Department department,
			Page<Department> page);
}
