<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>我的订单</title>
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
<style type="text/css">
body {
	background: #f2f2f2;
}
</style>
</head>
<body> 
	<div class="container container-fill">
		<div class="container container-fill">
			<header class="weui_grids m-navbar" style="text-align: center;">
				<a href="${ctx }/employee/index" style="float:left;"
					class="weui_grid js_grid navbar-item" data-id="button"> <i
					class="fa fa-angle-left"></i>
				</a> <a href="javascript:;" class="weui_grid js_grid navbar-center"
					data-id="cell"> <span class="navbar-title">我的订单</span>
				</a> <a href="javascript:;" class="weui_grid js_grid navbar-item"
					data-id="toast"></a>
			</header>
			<div class="g-scrollview">
				<c:if test="${empty list}">
					<div style="text-align: center; color: #999;">暂无订单</div>
				</c:if>
				<c:forEach items="${list }" var="order">
					<div class="weui_panel">
						<div class="weui_panel_hd">
							订单编号： <span class="number">${order.id }</span>
						</div>
						<div class="weui_panel_bd">
							<div class="weui__desc">
								<div class="block">款式</div>
								<div class="text">${order.shopname }</div>
							</div>
							<div class="weui__desc">
								<div class="block">收货人</div>
								<div class="text">${order. receiptname }</div>
							</div>
							<div class="weui__desc">
								<div class="block">电话</div>
								<div class="text">${order. receiptphone}</div>
							</div>
							<div class="weui__desc">
								<div class="block" style="vertical-align: middle;">收货地址</div>
								<div class="text">${order. receiptaddress}</div>
							</div>
							<div class="weui__desc">
								<div class="block">下单时间</div>
								<div class="text">${order. createdate}</div>
							</div>
							<div class="weui__desc">
								<div class="block">操作</div>
								<div class="text">
									<a href="javaScript:deleteOrder(${order.id })" class="btn">删除订单</a>
								</div>
							</div>
						</div>
					</div>
					<br />
				</c:forEach>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx }/js/jquery-3.2.1.min.js"></script>
<script src="${ctx }/js/jquery-weui.min.js"></script>
<script type="text/javascript">
	function deleteOrder(orderId) {
		$.confirm("确定要删除该订单吗？", "系统提示", function() {
			//点击确认后的回调函数
			$.ajax({
				url : '${ctx}/order/deleteOrder',
				type : 'POST',
				data : {
					'orderId' : orderId
				},
				dataType : 'json',
				success : function(data) {
					$.alert(data != null ? data.message : "删除失败", "系统提示");
					window.location.reload();
				},
				error : function(data) {
					console.log("error");
				}
			})
		}, function() {
			//点击取消后的回调函数
		});
	}
</script>
</html>
