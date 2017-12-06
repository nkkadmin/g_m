package com.xsx.domain;

/**
 * 
 * @Title: EmployeeOrderCount.java
 * @Package com.xsx.domain
 * @Description: 员工订单量统计
 * @author xsx
 * @date 2017年11月1日 下午1:55:39
 * @version V1.0
 */
public class EmployeeOrderCount {

	// 员工id
	private Integer empId;
	//部门id
	private Integer departmentId;

	private String empName;
	//部门名称
	private String departmentName;
	//统计类型：按部门统计（department），按个人统计（people）
	private String type;

	// 今天订单
	private Integer todayCount;
	// 昨天订单
	private Integer yesterCount;
	// 本月订单
	private Integer theMonthCount;
	// 上月订单
	private Integer lastMonthCount;
	// 全部订单数量
	private Integer allCount;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getTodayCount() {
		return todayCount;
	}

	public void setTodayCount(Integer todayCount) {
		this.todayCount = todayCount;
	}

	public Integer getYesterCount() {
		return yesterCount;
	}

	public void setYesterCount(Integer yesterCount) {
		this.yesterCount = yesterCount;
	}

	public Integer getTheMonthCount() {
		return theMonthCount;
	}

	public void setTheMonthCount(Integer theMonthCount) {
		this.theMonthCount = theMonthCount;
	}

	public Integer getLastMonthCount() {
		return lastMonthCount;
	}

	public void setLastMonthCount(Integer lastMonthCount) {
		this.lastMonthCount = lastMonthCount;
	}

	public Integer getAllCount() {
		if (allCount == null) {
			return (todayCount + yesterCount + theMonthCount + lastMonthCount);
		}
		return allCount;
	}

	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
}
