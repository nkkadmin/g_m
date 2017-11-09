package com.xsx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xsx.domain.Orders;
import com.xsx.domain.Page;
import com.xsx.exception.CustomException;

public interface OrdersService {

	/**
	 * 今日订单量
	 * 
	 * @param empId
	 * @return
	 */
	Integer todayOrderCount(Integer empId);

	/**
	 * 昨日订单量
	 * 
	 * @param empId
	 * @return
	 */
	Integer yesterOrderCount(Integer empId);

	/**
	 * 本月订单量
	 * 
	 * @param empId
	 * @return
	 */
	Integer theMonthOrderCount(Integer empId);

	/**
	 * 上月订单量
	 * 
	 * @param empId
	 * @return
	 */
	Integer lastMonthOrderCount(@Param("empId") Integer empId);

	/**
	 * 员工所有订单量
	 * 
	 * @param empId
	 * @return
	 */
	Integer employeeOrderCount(@Param("empId") Integer empId);

	/**
	 * 根据员工id查询员工订单
	 * 
	 * @param empId
	 * @return
	 */
	List<Orders> selectOrderByEmpId(@Param("empId") Integer empId);

	/**
	 * 查看全部订单
	 * 
	 * @param orders
	 * @param page
	 * @return
	 */
	Page<Orders> selectAllOrders(Orders orders, Page<Orders> page);

	int deleteByParamKey(Integer orderId);

	int insertSelective(Orders orders,String clientIP,Integer empId) throws CustomException ;

	/**
	 * 根据客户端ip和员工id查询
	 * @param clientIP
	 * @param id
	 * @return
	 */
	Orders selectByEmpIdAndClientIP(String clientIP, Integer id);
}
