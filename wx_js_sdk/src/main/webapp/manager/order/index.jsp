<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>客户订单信息管理</title>
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
		收货人姓名： <input type="text" name="name" id="searchKey"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
		收货人手机号： <input type="text" name="phone" id="searchPhone"
			class="abc input-default" placeholder="" value="">
		<button class="btn btn-primary" onclick="search()">查询</button>
	</div> 
	<table class="table table-bordered table-hover definewidth m10"
		id="evaluationList" data-pagination="true" data-query-params-type=""
		data-side-pagination="server" data-page-list="[100,50,10]"
		data-page-size="100"
		data-url="${ctx}/order/allOrder" data-query-params="searchParams"
		data-response-handler="responseHandler">
		<thead>
			<tr>
				<th class="ao_line-in" data-field="id" data-align="center"
					data-visible="true" data-cell-style="idStyle">订单号</th>
				<th class="ao_line-in" data-field="shopname" data-align="center"
					data-visible="true" data-cell-style="idStyle">款式</th>
				<th class="ao_line-in" data-field="receiptname" data-align="center"
					data-visible="true" data-cell-style="idStyle">收货人</th>
				<th class="ao_line-in" data-field="receiptphone" data-align="center"
					data-visible="true" data-cell-style="idStyle">电话</th>
				<th class="ao_line-in" data-field="receiptaddress"
					data-align="center" data-visible="true" data-cell-style="idStyle">收货地址</th>
				<th class="ao_line-in" data-field="empName" data-align="center"
					data-visible="true" data-cell-style="idStyle">推荐员工姓名</th>
				<th class="ao_line-in" data-field="createdate" data-align="center"
					data-cell-style="dateStyle">下单时间</th>
				<th class="ao_line-in" data-field="rowOption" data-align="center"
					data-cell-style="operStyle">操作</th>
				<th data-field="id" data-align="left" data-visible="false">id</th>
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
	$(function() {
		$('#evaluationList').bootstrapTable({
			method : 'post'
		});
	});
	
	function search() {
		$('#evaluationList').bootstrapTable("refresh", {
			url : '${ctx}/order/allOrder'
		});
	}

	/* 查询参数 */
	function searchParams(params) {
		params.pageNo = params.pageNumber;// 当前页
		params.pageSize = params.pageSize;// 每页记录数
		params.receiptname = $("#searchKey").val();
		params.receiptphone = $("#searchPhone").val();
		params.empid = '${empId}';
		return params;
	}

	/*构建操作按钮*/
	function responseHandler(res) {
		$
				.each(
						res.rows,
						function(i, row) {
							row.rowOption = "";
							row.rowOption += "<a style='margin-right:10px;' href='${ctx}/system/editOrderUI?id="+row.id+"'>修改</a>";
							row.rowOption += "<a style='margin-right:10px;' href='##' onclick='del("
									+ row.id + ")'>刪除</a>";
						});
		return res;
	}

	function del(id) {
		$.confirm({
			title : '系统提示',
			text : '确定要删除吗？',
			onOK : function() {
				$.ajax({
					url : '${ctx}/order/deleteOrder',
					type : 'POST',
					data : {
						'orderId' : id
					},
					dataType : 'json',
					success : function(data) {
						$.alert(data != null ? data.message : "删除失败", "系统提示");
						$("#evaluationList").bootstrapTable('refresh', {
							url : '${ctx}/order/allOrder'
						});
					},
					error : function(data) {
						console.log("error");
					}
				})
			},
			onCancel : function() {
			}
		});
	}
</script>
</html>
