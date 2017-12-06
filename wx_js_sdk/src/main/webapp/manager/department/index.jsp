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
		<button type="button" class="btn btn-success" id="addnew">新增部门</button>
	</div>
	<table class="table table-bordered table-hover definewidth m10"
		id="evaluationList" data-pagination="true" data-query-params-type=""
		data-side-pagination="server" data-page-list="[All]"
		data-url="${ctx}/department/allDepartment"
		data-query-params="searchParams"
		data-response-handler="responseHandler">
		<thead>
			<tr>
				<th class="ao_line-in" data-field="nameOption" data-align="center"
					data-visible="true" data-cell-style="idStyle">部门名称</th>
				<th class="ao_line-in" data-field="descript" data-align="center"
					data-visible="true" data-cell-style="idStyle">说明</th>
				<th data-width="20%" class="ao_line-in" data-field="createdate" data-align="center"
					data-cell-style="dateStyle">创建时间</th>
				<th data-width="20%" class="ao_line-in" data-field="rowOption" data-align="center"
					data-cell-style="operStyle">操作</th>
				<th data-field="id" data-align="left" data-visible="false">id</th>
				<th data-field="name" data-align="left" data-visible="false">name</th>
			</tr>
		</thead>
	</table>
</body>
<script src="${ctx }/js/jquery-weui.min.js"></script>
<script>
	$(function() {
		$('#evaluationList').bootstrapTable({
			method : 'post'
		});
		$('#addnew').click(function() {
			window.location.href = "${ctx}/department/addDepartmentUI";
		});

	});
	function search() {
		$('#evaluationList').bootstrapTable("refresh", {
			url : '${ctx}/employee/selectAllEmp'
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
							row.nameOption = "";
							row.nameOption = "<a href='${ctx}/system/employeeUI?departmentId="+row.id+"'>"+row.name+"</a>";
							row.rowOption = "";
							row.rowOption += "<a style='margin-right:10px;' href='##' onclick='changeEmp("
									+ row.id + ")'>部门员工转移</a>";
							row.rowOption += "<a style='margin-right:10px;' href='##' onclick='del("
									+ row.id + ")'>刪除</a>";
						});
		return res;
	}

	function changeEmp(fromId) {
		$.prompt({
			title : '系统提示',
			text : '填写转移到的部门名称',
			input : '',
			empty : false, // 是否允许为空
			onOK : function(input) {
				//点击确认
				$.ajax({
					url : '${ctx}/department/changeEmpToOtherDepartment',
					type : 'POST',
					data : {
						'fromDepartmentId' : fromId,
						'toDepartmentName' : input
					},
					dataType : 'json',
					success : function(data) {
						$.alert(data.message, "系统提示");
					},
					error : function(data) {
						console.log("error");
					}
				})
			},
			onCancel : function() {
				//点击取消
			}
		});

	}

	function del(id) {
		$.confirm({
			title : '系统提示',
			text : '确认退出系统吗？',
			onOK : function() {
				//点击确认
				$.ajax({
					url : '${ctx}/department/deleteDepartment',
					type : 'get',
					data : {
						'id' : id
					},
					dataType : 'json',
					success : function(data) {
						$.alert(data.message, "系统提示");
						$("#evaluationList").bootstrapTable('refresh', {
							url : '${ctx}/department/allDepartment'
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
