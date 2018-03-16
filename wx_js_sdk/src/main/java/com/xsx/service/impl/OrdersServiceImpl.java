package com.xsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xsx.constant.Constants;
import com.xsx.domain.Orders;
import com.xsx.domain.Page;
import com.xsx.exception.CustomException;
import com.xsx.mapper.OrdersMapper;
import com.xsx.service.OrdersService;
import com.xsx.util.DateHelper;


@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {

	
	@Resource
	private OrdersMapper ordersMapper;

	@Override
	public Integer todayOrderCount(Integer empId) {
		return ordersMapper.todayOrderCount(empId);
	}

	@Override
	public Integer yesterOrderCount(Integer empId) {
		return ordersMapper.yesterOrderCount(empId);
	}

	@Override
	public Integer theMonthOrderCount(Integer empId) {
		return ordersMapper.theMonthOrderCount(empId);
	}

	@Override
	public Integer lastMonthOrderCount(Integer empId) {
		return ordersMapper.lastMonthOrderCount(empId);
	}

	@Override
	public Integer employeeOrderCount(Integer empId) {
		return ordersMapper.employeeOrderCount(empId);
	}

	@Override
	public List<Orders> selectOrderByEmpId(Integer empId) {
		return ordersMapper.selectOrderByEmpId(empId);
	}

	@Override
	public Page<Orders> selectAllOrders(Orders orders, Page<Orders> page) {
		List<Orders> list = ordersMapper.selectAllOrders(orders, page);
		page.setRows(list);
		return page;
	}

	@Override
	public int deleteByParamKey(Integer orderId) {
		if(orderId == null){
			return 0;
		}
		Orders order = new Orders();
		order.setId(orderId);
		order.setStatu(Constants.STATU_DELETE);
		ordersMapper.updateByPrimaryKeySelective(order);
		return 1;
	}

	@Override
	public int insertSelective(Orders order,String clientIP,Integer empId) throws CustomException {
		//判断该ip是否已经提交过该链接的订单（该链接通过empId来标识）
		Orders isOrder = ordersMapper.selectByEmpIdAndClientIP(clientIP,empId);
		if(isOrder != null){
			throw new CustomException("您已提交，不需要再次提交");
		}
		order.setStatu(Constants.STATU_OK);
		order.setCreatedate(DateHelper.nowDate());
		order.setClientip(clientIP);
		return ordersMapper.insertSelective(order);
	}

	@Override
	public Orders selectByEmpIdAndClientIP(String clientIP, Integer id) {
		return ordersMapper.selectByEmpIdAndClientIP(clientIP,id);
	}

	@Override
	public Orders selectByPrimaryKey(Integer id) {
		return ordersMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Orders record) {
		return ordersMapper.updateByPrimaryKeySelective(record);
	}
}
