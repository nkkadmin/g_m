package com.xsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xsx.constant.Constants;
import com.xsx.domain.Department;
import com.xsx.domain.Page;
import com.xsx.mapper.DepartmentMapper;
import com.xsx.service.DepartmentService;


@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Resource
	private DepartmentMapper departmentMapper;

	@Override
	public int insertSelective(Department record) {
		return departmentMapper.insertSelective(record);
	}

	@Override
	public Department selectByPrimaryKey(Integer id) {
		return departmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Department record) {
		return departmentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		if(id == null){
			return 0;
		}
		Department department = new Department();
		department.setId(id);
		department.setStatu(Constants.STATU_DELETE);
		departmentMapper.updateByPrimaryKeySelective(department);
		return 1;
	}

	public Page<Department> selectAllDepartment(Department department,
			Page<Department> page) {
		List<Department> list = departmentMapper.selectAllDepartment(department, page);
		page.setRows(list);
		return page;
	}

	@Override
	public List<Department> selectAllDepartment() {
		return departmentMapper.selectDepartmentList();
	}

	@Override
	public Department selectByName(String name) {
		return departmentMapper.selectByName(name);
	}
	
}
