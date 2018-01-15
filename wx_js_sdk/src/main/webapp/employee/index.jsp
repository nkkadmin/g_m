<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>员工后台</title>
<meta name="format-detection" content="telephone=no, address=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent" />
<link rel="stylesheet" href="${ctx }/css/weui.min.css">
<link rel="stylesheet" href="${ctx }/css/jquery-weui.min.css">
<link href="${ctx }/employee/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/employee/css/common.min.css" rel="stylesheet">
<link href="${ctx }/css/weui.min.css" rel="stylesheet">
<link href="${ctx }/employee/css/font-awesome.min.css" rel="stylesheet" />
<link href="${ctx }/employee/css/style.css" rel="stylesheet" />

</head>
<body>
	<div class="container container-fill">
		<div class="container container-fill">
			<div class="pcenter-main">
				<div class="head">
					<a class="ptool" href="#"><i class="fa fa-cog fa-spin"></i></a>
					<div class="pdetail">
						<div class="img-circle">
							<img src="${ctx }/employee/images/tx.jpg" />
						</div>
						<div class="pull-left">
							<span class="name"><a href="#" style="color: white;">微营销平台</a></span>
							<span class="name"></span> <span class="name"></span> <span
								class="type"><i class="fa fa-flag-o"></i>&nbsp;&nbsp;员工:${currentSessionEmp.name }</span>
						</div>
					</div>
					<div class="head-nav">
						<a class="head-nav-list" href="#">今日订单<span>${employeeOrderCount.todayCount }</span></a>
						<a class="head-nav-list" href="#">昨日订单<span>${employeeOrderCount.yesterCount }</span></a>
						<a class="head-nav-list" href="#">本月订单<span>${employeeOrderCount.theMonthCount }</span></a>
						<a class="head-nav-list" href="#">上月订单<span>${employeeOrderCount.lastMonthCount }</span></a>
					</div>
				</div>
			</div>
		</div>

		<div class="list-group">
			<a href="${ctx }/employee/selectOrders?empId=${currentSessionEmp.id}" class="list-group-item">查看订单记录
				<span class="glyphicon glyphicon-list-alt" aria-hidden="true" style="float: right"></span></a> 
			<a href="${ctx }/employee/extension?empId=${currentSessionEmp.id}" class="list-group-item">微信链接管理
				<span class="glyphicon glyphicon-list-alt" aria-hidden="true" style="float: right"></span></a> 
			<a onclick="removeBindShoworHide()" class="list-group-item">退出登录
				<span class="glyphicon glyphicon-fire" aria-hidden="true" style="float: right;"></span>
			</a>
		</div>
	</div>

	<jsp:include page="public/footer.jsp" />
</body>
<script src="${ctx }/js/jquery-3.2.1.min.js"></script>
<script src="${ctx }/js/jquery-weui.min.js"></script>
<script>
	function removeBindShoworHide() {
		$.confirm("确定要退出登录吗？", "系统提示", function() {
			//点击确认后的回调函数
			$.ajax({
				url : '${ctx }/employee/logOut',
				data : {
					"empId" : '${currentSessionEmp.id}'
				},
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					if (data != null && data.success) {
						window.location.href = "${ctx}/employee/loginUI";
					} else {
						$.alert(data != null ? data.message : "退出失败", "系统提示");
					}
				},
				error : function(data) {

				}
			});
		}, function() {
			//点击取消后的回调函数
		});
	}
</script>
</html>
