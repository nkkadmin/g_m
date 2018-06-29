package com.xsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xsx.constant.Constants;
import com.xsx.domain.Department;
import com.xsx.domain.Page;
import com.xsx.mapper.DepartmentMapper;
import com.xsx.service.DepartmentService;

/**
 * 
 * 缓存机制说明：所有的查询结果都放进了缓存，也就是把MySQL查询的结果放到了redis中去，
 * 然后第二次发起该条查询时就可以从redis中去读取查询的结果，从而不与MySQL交互，从而达到优化的效果，
 * redis的查询速度之于MySQL的查询速度相当于 内存读写速度 /硬盘读写速度 
 * @Cacheable("a")注解的意义就是把该方法的查询结果放到redis中去，下一次再发起查询就去redis中去取，存在redis中的数据的key就是a；
 * @CacheEvict(value={"a","b"},allEntries=true) 的意思就是执行该方法后要清除redis中key名称为a,b的数据；
 */
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
