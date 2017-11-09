package com.test.init;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xsx.domain.Employee;
import com.xsx.domain.Orders;
import com.xsx.domain.Shops;
import com.xsx.exception.CustomException;
import com.xsx.mapper.OrdersMapper;
import com.xsx.mapper.PhonePayTypeMapper;
import com.xsx.mapper.RoleMapper;
import com.xsx.mapper.ShopsMapper;
import com.xsx.service.EmployeeService;
import com.xsx.service.OrdersService;
import com.xsx.util.DateHelper;
import com.xsx.util.RequestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application.xml",
		"classpath:spring-servlet.xml" })
public class InitTestData {

	@Resource
	private EmployeeService employeeService;
	@Resource
	private OrdersService ordersService;
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private PhonePayTypeMapper phonePayTypeMapper;
	@Resource
	private ShopsMapper shopMapper;

	 
	@Test
	public void init() {
		Shops shop = new Shops();
		shop.setStatu(1);
		shop.setName("金苹果吊坠");
		shop.setCreatedate(DateHelper.nowDate());
		shop.setTitle("这是标题，巴拉巴拉");
		shop.setContent("这是内容，哔哩哔哩...");
		shopMapper.insertSelective(shop);

	}

	@Test
	public void initEmp() {
		Employee employee = new Employee();
		employee.setName("0002谢生翔");
		employee.setPassword("123456");
		employee.setPhone("15821292493");
		// System.out.println(employeeService.insertSelective(employee));

	}

	@Test
	public void initOrder() {
		Orders orders = new Orders();
		orders.setShopname("金苹果吊坠	");
		orders.setReceiptname("王先生");
		orders.setReceiptphone("15899999999");
		orders.setReceiptaddress("上海市 浦东新区 浦东南路2159号5E");
		orders.setPhonepaytypeid(4);
		try {
			ordersService.insertSelective(orders,"192.168.10.129",employeeService.selectByName("003谢生翔").getId());
		} catch (CustomException e) {
			e.printStackTrace();
		}

	}
}
