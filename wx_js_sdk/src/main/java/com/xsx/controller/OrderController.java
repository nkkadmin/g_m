package com.xsx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.domain.AjaxJson;
import com.xsx.domain.Employee;
import com.xsx.domain.Orders;
import com.xsx.domain.Page;
import com.xsx.util.RequestUtils;
import com.xsx.util.StringHelper;
import com.xsx.util.send.AIiSendCodeUtil;

/**
 * 
 * @Title: OrderController.java
 * @Package com.xsx.controller
 * @Description: 订单管理
 * @author xsx
 * @date 2017年11月1日 下午10:14:50
 * @version V1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

 	
	/**
	 * 获取全部订单
	 * 
	 * @param order
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/allOrder", method = RequestMethod.POST)
	public Page<Orders> allOrder(Orders order, Page<Orders> page) {
		try {
			if (order != null && !StringHelper.isEmpty(order.getReceiptname())) {
				order.setReceiptname(new String(order.getReceiptname().trim()
						.getBytes("ISO8859-1"), "UTF-8"));
			}
			page = ordersService.selectAllOrders(order, page);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加订单
	 * 
	 * @param order
	 * @param province
	 *            省份
	 * @param city
	 *            城市
	 * @param district
	 *            区/县
	 * @param detailaddress
	 *            具体街道
	 * @param code
	 *            员工唯一标识推广链接码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public AjaxJson addOrder(Orders order, String code, String province,
			String city, String district, String detailaddress,
			HttpServletRequest request) {
		AjaxJson json = new AjaxJson();
		try {
			if (StringHelper.isEmpty(province) || StringHelper.isEmpty(city)
					|| StringHelper.isEmpty(district)
					|| StringHelper.isEmpty(detailaddress)
					|| StringHelper.isEmpty(code) || order == null
					|| order.getEmpid() == null) {
				json.setMessage("参数不合格");
				json.setSuccess(false);
				return json;
			}
			// 获取客户端的ip
			String clientIP = RequestUtils.getIpAddress(request);
			// 校验该链接的code和empId
			Employee employee = employeeService.selectByPrimaryKey(order
					.getEmpid());
			if (employee == null
					|| (employee != null && !employee.getExtensionrandomcode()
							.equals(code))) {
				json.setMessage("推广链接有误");
				json.setSuccess(false);
				return json;
			}
			order.setReceiptaddress(province + " " + city + " " + district
					+ " " + detailaddress);
			if (ordersService
					.insertSelective(order, clientIP, employee.getId()) == 1) {
				//发送短信
				AIiSendCodeUtil.sendCode(order.getReceiptphone());
				json.setMessage("下单成功");
				json.setSuccess(true);
				return json;
			}
			json.setMessage("下单失败");
			json.setSuccess(false);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMessage(e.getMessage());
			json.setSuccess(false);
		}
		return json;
	}

	/**
	 * 删除订单
	 * 
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
	public AjaxJson deleteOrder(Integer orderId) {
		AjaxJson json = new AjaxJson();
		try {
			if (ordersService.deleteByParamKey(orderId) == 1) {
				json.setMessage("删除成功");
				json.setSuccess(true);
			} else {
				json.setMessage("删除失败");
				json.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * export all orders
	 */
	/*@ResponseBody
	@RequestMapping(value="/export")
	public String exportOrderData(HttpServletResponse response){
		return "想什么呢！！";
	}*/
}
