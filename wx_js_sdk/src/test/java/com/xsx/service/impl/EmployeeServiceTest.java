package com.xsx.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xsx.domain.Employee;
import com.xsx.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml","classpath:spring-servlet.xml"}) 
public class EmployeeServiceTest {

	
	@Resource
	private EmployeeService employeeService;
	
	@Test
	public void insertEmployee(){
		Employee employee = new Employee();
		employee.setName("xsx");
		employee.setPhone("15811111111");
		employee.setRoleid(1);
		//employeeService.insertSelective(employee);
	}
	
}
