package com.xsx.init;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;

import com.xsx.constant.Constants;
import com.xsx.domain.Employee;
import com.xsx.domain.PhonePayType;
import com.xsx.domain.Role;
import com.xsx.mapper.PhonePayTypeMapper;
import com.xsx.mapper.RoleMapper;
import com.xsx.service.EmployeeService;
import com.xsx.util.DateHelper;

/**
 * 
 * @Title: InitData.java
 * @Package com.xsx.init
 * @Description: 初始化数据
 * @author xsx
 * @date 2017年10月31日 下午1:16:34
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class InitData implements ApplicationListener {

	@Resource
	private RoleMapper roleMapper;

	@Resource
	private PhonePayTypeMapper phonePayTypeMapper;
	
	@Resource
	private EmployeeService employeeService;
	
	private boolean flag = true;

	
	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		if(flag == true){
			initRole();
			initPhonePayType();
			initUser();
		}
		flag = false;
	}

	/**
	 * 初始化公司管理员账号
	 */
	private void initUser() {
		if(employeeService.countAll() < 1){
			Employee employee = new Employee();
			employee.setName("company");
			employee.setPhone("15821292493");
			employee.setPassword("123456");
			employee.setRoleid(roleMapper.selectByDescript(Constants.ROLE_COMMPANY).getId());
			employee.setCreatedate(DateHelper.nowDate());
			employee.setImg("img/headpro/default.png");
			employee.setStatu(Constants.STATU_OK);
			employee.setExtensionrandomcode(UUID.randomUUID().toString()
					.replaceAll("-", ""));
			employeeService.insertSelective(employee,Constants.ROLE_EMPLOYEE);
		}
	}

	private void initPhonePayType() {
		if (phonePayTypeMapper.countAll() < 1) {
			PhonePayType payType = new PhonePayType();
			payType.setName("移动充值卡");
			payType.setPrice("100");
			payType.setCreatedate(DateHelper.nowDate());
			payType.setStatu(1);
			phonePayTypeMapper.insertSelective(payType);

			payType = new PhonePayType();
			payType.setName("联通充值卡");
			payType.setPrice("100");
			payType.setCreatedate(DateHelper.nowDate());
			payType.setStatu(1);
			phonePayTypeMapper.insertSelective(payType);

			payType = new PhonePayType();
			payType.setName("电信充值卡");
			payType.setPrice("100");
			payType.setCreatedate(DateHelper.nowDate());
			payType.setStatu(1);
			phonePayTypeMapper.insertSelective(payType);
		}
	}

	/*
	 * 初始化角色
	 */
	private void initRole() {
		if (roleMapper.countAll() < 1) {
			Role role = new Role();
			role.setName("公司管理员");
			role.setDescript(Constants.ROLE_COMMPANY);
			role.setCreatedate(DateHelper.nowDate());
			role.setStatu(1);
			roleMapper.insert(role);

			role = new Role();
			role.setName("员工");
			role.setDescript(Constants.ROLE_EMPLOYEE);
			role.setCreatedate(DateHelper.nowDate());
			role.setStatu(1);
			roleMapper.insert(role);

			role = new Role();
			role.setName("客户");
			role.setDescript(Constants.ROLE_CUSTOMER);
			role.setCreatedate(DateHelper.nowDate());
			role.setStatu(1);
			roleMapper.insert(role);
		}
	}

}
