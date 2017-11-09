<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>员工信息管理</title>
<meta charset="UTF-8">
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
<script type="text/javascript" src="${ctx}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${ctx}/manager/Js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/manager/Js/ckform.js"></script>
<script src='${ctx}/js/bootstrap/bootstrap-table/bootstrap-table.min.js'
	type='text/javascript'></script>
<script type="text/javascript" src="${ctx}/manager/Js/common.js"></script>
<script src="${ctx }/js/jquery-weui.min.js"></script>
<script type="text/javascript" src="../js/jquery.qrcode.min.js"></script>

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
		<button type="button" class="btn btn-success" id="addnew">新增公司管理员</button>
	</div> 
	<table class="table table-bordered table-hover definewidth m10"
		id="evaluationList" data-pagination="true" data-query-params-type=""
		data-side-pagination="server" data-page-list="[All]"
		data-url="${ctx}/system/selectAllCommpany" data-query-params="searchParams"
		data-response-handler="responseHandler">
		<thead>
			<tr>
				<th class="ao_line-in" data-field="name" data-align="center"
					data-visible="true" data-cell-style="idStyle">姓名（登录账号）</th>
				<th class="ao_line-in" data-field="phone" data-align="center"
					data-visible="true" data-cell-style="idStyle">电话</th>
				<th class="ao_line-in" data-field="departmentName"
					data-align="center" data-visible="true" data-cell-style="idStyle">所属部门</th>
				<!-- <th class="ao_line-in" data-field="extensionurl" data-align="center"
					data-visible="true" data-cell-style="idStyle">推广链接</th> -->
				<th class="ao_line-in" data-field="createdate" data-align="center"
					data-cell-style="dateStyle">创建时间</th>
				<th class="ao_line-in" data-field="rowOption" data-align="center"
					data-cell-style="operStyle">操作</th>
				<th data-field="id" data-align="left" data-visible="false">id</th>
			</tr>
		</thead>
	</table>
	 
</body>
<script>
	$(function() {
		$('#evaluationList').bootstrapTable({
			method : 'post'
		});
		$('#addnew').click(function() {
			window.location.href = "${ctx}/system/addEmpUI?type=company";
		});

	});
	function search() {
		$('#evaluationList').bootstrapTable("refresh", {
			url : '${ctx}/system/selectAllEmp'
		});
	}
	/* 查询参数 */
	function searchParams(params) {
		params.pageNo = params.pageNumber;// 当前页
		params.pageSize = params.pageSize;// 每页记录数
		params.name = $("#searchKey").val();
		return params;
	}

	/*构建操作按钮*/
	function responseHandler(res) {
		$
				.each(
						res.rows,
						function(i, row) {
							row.rowOption = "";
							row.rowOption += "<a style='margin-right:10px;' href='##' onclick='resetpass("
									+ row.id + ")'>重置密码</a>";
							row.rowOption += "<a style='margin-right:10px;' href='##' onclick='del("
									+ row.id + ")'>刪除</a>";
						});
		return res;
	}
	
	function resetpass(id) {
		$.prompt({
			title : '系统提示',
			text : '请填写重置的密码',
			input : '',
			empty : false, // 是否允许为空
			onOK : function(input) {
				$.ajax({
					url : '${ctx}/system/resetPassword',
					type : 'get',
					data : {
						'empId' : id,
						'resetPassowrd' : input
					},
					dataType : 'json',
					success : function(data) {
						$.alert(data != null ? data.message : "重置失败", "系统提示");
						$("#evaluationList").bootstrapTable('refresh', {
							url : '${ctx}/system/selectAllEmp'
						});
					},
					error : function(data) {
						console.log("error");
					}
				})
			}
		});
	}

	function del(id) {
		$.confirm({
			title : '系统提示',
			text : '确定要删除吗？',
			onOK : function() {
				$.ajax({
					url : '${ctx}/system/deleteEmp',
					type : 'get',
					data : {
						'empId' : id
					},
					dataType : 'json',
					success : function(data) {
						console.log(data);
						$.alert(data != null ? data.message : "删除失败",
								data.message, "系统提示");
						$("#evaluationList").bootstrapTable('refresh', {
							url : '${ctx}/system/selectAllCommpany'
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
