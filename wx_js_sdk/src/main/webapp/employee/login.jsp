<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>员工后台</title>
<meta name="format-detection" content="telephone=no, address=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent">
<link href="${ctx }/employee/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/employee/css/common.min.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx }/css/weui.min.css">
<link rel="stylesheet" href="${ctx }/css/jquery-weui.min.css">
<link rel="stylesheet" href="${ctx }/employee/css/login.css">
</head>
<body>
	<div class="container container-fill">
		<div class="panel panel-info ng-container ng-scope"
			ng-controller="loginPanel">
			<div class="panel-heading">
				<h4>员工后台登录</h4>
			</div>
			<div class="panel-body">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#">账号登录</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane animated fadeInLeft active" id="basic">
						<div class="form-group has-feedback">
							<input type="text"
								class="form-control ng-pristine ng-untouched ng-valid" id="name"
								name="name" placeholder="账号">
						</div>
						<div class="form-group has-feedback">
							<input type="password"
								class="form-control ng-pristine ng-untouched ng-valid"
								id="password" name="password" placeholder="密码">
						</div>
					</div>
				</div>
				<button id="loginBtn" class="btn btn-primary btn-block">登录</button>
			</div>
		</div>
	</div>

	<jsp:include page="public/footer.jsp" />

</body>
<script type="text/javascript" src="${ctx }/js/jquery-3.2.1.min.js"></script>
<script src="${ctx }/js/jquery-weui.min.js"></script>
<script type="text/javascript">
	function dialog_alert_Hide() {
		$("#dialog_alert").hide();
	}
	$(function() {
		$("#loginBtn").click(function() {
			$.ajax({
				url : '${ctx }/employee/login',
				data : {
					"name" : $("#name").val(),
					"password" : $("#password").val()
				},
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					if (data != null && data.success) {
						window.location.href = "${ctx}/employee/index";
					} else {
						$.alert(data.message, "系统提示");
					}
				},
				error : function(data) {

				}
			});
		});
	});
</script>
</html>