package com.jeecg.page.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

import com.jeecg.entity.test.TestOrderCustomEntity;
import com.jeecg.entity.test.TestOrderTicketEntity;

/**   
 * @Title: Entity
 * @Description: 订单
 * @author zhangdaihao
 * @date 2018-04-27 16:20:04
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class TestOrderMainPage implements java.io.Serializable {
	/**保存-客户明细*/
	private List<TestOrderCustomEntity> testOrderCustomList = new ArrayList<TestOrderCustomEntity>();
	public List<TestOrderCustomEntity> getTestOrderCustomList() {
		return testOrderCustomList;
	}
	public void setTestOrderCustomList(List<TestOrderCustomEntity> testOrderCustomList) {
		this.testOrderCustomList = testOrderCustomList;
	}
	/**保存-产品明细*/
	private List<TestOrderTicketEntity> testOrderTicketList = new ArrayList<TestOrderTicketEntity>();
	public List<TestOrderTicketEntity> getTestOrderTicketList() {
		return testOrderTicketList;
	}
	public void setTestOrderTicketList(List<TestOrderTicketEntity> testOrderTicketList) {
		this.testOrderTicketList = testOrderTicketList;
	}


	/**主键*/
	private java.lang.String id;
	/**订单号*/
	private java.lang.String orderCode;
	/**订单日期*/
	private java.util.Date orderDate;
	/**订单金额*/
	private java.lang.Double orderMoney;
	/**备注*/
	private java.lang.String content;
	/**订单扫描件*/
	private java.lang.String ctype;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单号
	 */
	public java.lang.String getOrderCode(){
		return this.orderCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单号
	 */
	public void setOrderCode(java.lang.String orderCode){
		this.orderCode = orderCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  订单日期
	 */
	public java.util.Date getOrderDate(){
		return this.orderDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  订单日期
	 */
	public void setOrderDate(java.util.Date orderDate){
		this.orderDate = orderDate;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  订单金额
	 */
	public java.lang.Double getOrderMoney(){
		return this.orderMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  订单金额
	 */
	public void setOrderMoney(java.lang.Double orderMoney){
		this.orderMoney = orderMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单扫描件
	 */
	public java.lang.String getCtype(){
		return this.ctype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单扫描件
	 */
	public void setCtype(java.lang.String ctype){
		this.ctype = ctype;
	}
}
