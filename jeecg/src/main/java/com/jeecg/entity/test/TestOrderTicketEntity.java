package com.jeecg.entity.test;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 产品明细
 * @author zhangdaihao
 * @date 2018-04-27 16:20:02
 * @version V1.0   
 *
 */
@Entity
@Table(name = "jform_order_ticket", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TestOrderTicketEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**航班号*/
	private java.lang.String ticketCode;
	/**航班时间*/
	private java.util.Date tickectDate;
	/**外键*/
	private java.lang.String fckId;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
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
	 *@return: java.lang.String  航班号
	 */
	@Column(name ="TICKET_CODE",nullable=false,length=100)
	public java.lang.String getTicketCode(){
		return this.ticketCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  航班号
	 */
	public void setTicketCode(java.lang.String ticketCode){
		this.ticketCode = ticketCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  航班时间
	 */
	@Column(name ="TICKECT_DATE",nullable=false)
	public java.util.Date getTickectDate(){
		return this.tickectDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  航班时间
	 */
	public void setTickectDate(java.util.Date tickectDate){
		this.tickectDate = tickectDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外键
	 */
	@Column(name ="FCK_ID",nullable=false,length=36)
	public java.lang.String getFckId(){
		return this.fckId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外键
	 */
	public void setFckId(java.lang.String fckId){
		this.fckId = fckId;
	}
}
