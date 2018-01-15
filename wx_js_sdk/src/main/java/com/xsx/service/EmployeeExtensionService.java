package com.xsx.service;

import com.xsx.domain.EmployeeExtension;

public interface EmployeeExtensionService {

	/**
	 * 编辑内容
	 * @param employeeExtension
	 * @return
	 */
	public int editContent(EmployeeExtension employeeExtension);

	/**
	 * 根据员工id获取
	 * @param employeeId
	 * @return
	 */
	public EmployeeExtension selectByEmployeeId(Integer employeeId);
	
}