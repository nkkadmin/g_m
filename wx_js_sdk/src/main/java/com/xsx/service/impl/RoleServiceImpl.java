package com.xsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xsx.domain.Role;
import com.xsx.mapper.RoleMapper;
import com.xsx.service.RoleService;


@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper roleMapper;

	@Override
	public int insert(Role record) {
		return roleMapper.insert(record);
	}

	@Override
	public int insertSelective(Role record) {
		return roleMapper.insertSelective(record);
	}

	@Override
	public Role selectByPrimaryKey(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		return roleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		return roleMapper.updateByPrimaryKey(record);
	}

	@Override
	public int countAll() {
		return roleMapper.countAll();
	}

	@Override
	public Role selectByDescript(String descript) {
		return roleMapper.selectByDescript(descript);
	}
	
}
