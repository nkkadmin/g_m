<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>员工后台二维码</title>
<script type="text/javascript" src="${ctx}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.qrcode.min.js"></script>
<style type="text/css">
.num {
	width: 200px;
	height: 201px;
	border: 2px solid #000;
	margin: 100px auto;
	position: relative;
	line-height: 230px;
	text-align: center;
}
</style>
</head>
<body style="text-align: center;">
	<div id="qrcode" class="num"></div>
	<script type="text/javascript">
		var text = "${url}";
		jQuery('#qrcode').qrcode({
			render : "canvas",
			width : 200,
			height : 200,
			correctLevel : 0,
			text : text
		});
	</script>
</body>
</html>