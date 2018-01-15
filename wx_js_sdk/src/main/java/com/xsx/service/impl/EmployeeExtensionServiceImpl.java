package com.xsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xsx.domain.EmployeeExtension;
import com.xsx.mapper.EmployeeExtensionMapper;
import com.xsx.service.EmployeeExtensionService;
import com.xsx.util.DateHelper;


@Service("employeeExtensionService")
public class EmployeeExtensionServiceImpl implements EmployeeExtensionService {
	
	@Resource
	private EmployeeExtensionMapper employeeExtensionMapper;

	@Override
	public int editContent(EmployeeExtension employeeExtension) {
		if(employeeExtension != null && employeeExtension.getEmployeeid() != null && employeeExtension.getContent() != null){
			EmployeeExtension temp = employeeExtensionMapper.selectByEmployeeId(employeeExtension.getEmployeeid());
			if(temp != null){//执行修改
				temp.setContent(employeeExtension.getContent());
				employeeExtensionMapper.updateByPrimaryKeySelective(temp);
			}else{ //执行保存
				employeeExtension.setCreatedate(DateHelper.nowDate());
				employeeExtensionMapper.insert(employeeExtension);
			}
			return 1;
		}
		return 0;
	}

	@Override
	public EmployeeExtension selectByEmployeeId(Integer employeeId) {
		if(employeeId != null){
			return employeeExtensionMapper.selectByEmployeeId(employeeId);
		}
		return null;
	}

}
