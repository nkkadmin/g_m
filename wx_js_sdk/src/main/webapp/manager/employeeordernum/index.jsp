<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>员工订单量</title>
<meta charset="UTF-8">
<%--  <link rel="stylesheet" type="text/css" href="${ctx}/manager/Css/bootstrap.css" />
 --%>
<link href="${ctx }/css/weui.min.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx }/css/jquery-weui.min.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/bootstrap/bootstrap-table/bootstrap-table.min.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/manager/Css/bootstrap-responsive.css" />
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/manager/Css/style.css" />

<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
</head>
<body>
	<div class="form-inline definewidth m20">
		统计分类：
		<select name="typeName" id="typeName">
			<option value="people">按个人统计</option> 
			<option value="department">按部门统计</option>
		</select>
		<select name="departmentId" id="department">
			<option value="">请选择部门</option>
			<c:forEach items="${departmentList }" var="department">
				<option value="${department.id }">${department.name }</option>
			</c:forEach>
		</select>
	</div> 
	<table class="table table-bordered table-hover definewidth m10"
		id="evaluationList" data-pagination="true" data-query-params-type=""
		data-side-pagination="server" data-page-list="[10, 20, 50, 100]"
		data-url="${ctx}/system/employeeordernum" data-query-params="searchParams"
		data-response-handler="responseHandler">
		<thead>
			<tr>
				<th  data-field="index" data-formatter="indexFormatter"  data-align="center">序号</th>  
				<th class="ao_line-in" data-field="empNameOpt" data-align="center"
					data-visible="true" data-cell-style="idStyle">员工名称</th>
				<th class="ao_line-in" data-field="departmentName" data-align="center"
					data-visible="true" data-cell-style="idStyle">员工所属部门</th>
				<th class="ao_line-in" data-field="todayCount" data-align="center"
					data-visible="true" data-cell-style="idStyle">今日订单量</th>
				<th class="ao_line-in" data-field="theMonthCount" data-align="center"
					data-visible="true" data-cell-style="idStyle">本月订单量</th>
				<th class="ao_line-in" data-field="allCount" data-align="center"
					data-visible="true" data-cell-style="idStyle">全部订单量</th>
				<th data-field="departmentId" data-align="left" data-visible="false">departmentId</th>
				<th data-field="empName" data-align="left" data-visible="false">empName</th>
				<th data-field="empId" data-align="left" data-visible="false">empId</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript" src="${ctx}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${ctx}/manager/Js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/manager/Js/ckform.js"></script>
<script src='${ctx}/js/bootstrap/bootstrap-table/bootstrap-table.min.js'
	type='text/javascript'></script>
<script type="text/javascript" src="${ctx}/manager/Js/common.js"></script>
<script src="${ctx }/js/jquery-weui.min.js"></script>
<script>
	function indexFormatter(value, row, index) {  
	    return index+1;  
	}  
	$(function() {
		$('#evaluationList').bootstrapTable({
			method : 'post'
		});
		
		$("#typeName").change(function(){
			$('#evaluationList').bootstrapTable("refresh", {
				url : '${ctx}/system/employeeordernum'
			});
		});
		$("#department").change(function(){
			$('#evaluationList').bootstrapTable("refresh", {
				url : '${ctx}/system/employeeordernum'
			});
		});
	});
	
	/*构建操作按钮*/
	function responseHandler(res) {
		$
				.each(
						res.rows,
						function(i, row) {
							if(row.empName != "-"){
								row.empNameOpt = "";
								row.empNameOpt += "<a style='margin-right:10px;' href='${ctx}/system/orderUI?empId="+row.empId+"'>"+row.empName+"</a>";
							}
							
						});
		return res;
	}
	
	
	/* 查询参数 */
	function searchParams(params) {
		params.pageNo = params.pageNumber;// 当前页
		params.pageSize = params.pageSize;// 每页记录数
		params.type = $("#typeName").val();
		params.departmentId = $("#department").val();
		return params;
	}
 
</script>
</html>
