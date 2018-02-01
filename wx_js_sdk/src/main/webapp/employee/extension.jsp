<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>微信二维码</title>
<meta name="format-detection" content="telephone=no, address=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent" />
</head>
<script type="text/javascript" src="${ctx }/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.qrcode.min.js"></script>
<body> 
	<div style="margin-top: 20px;text-align: center;" id="qrcode" class="num"></div>
	<div style="text-align: center;font-weight: bolder;">浏览二维码</div>
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
	<div id="infoText" style="margin: 0px 40px 0px 40px;">
		<c:if test="${info != null}">
			<div style="text-align: center;">
				<h2>以下页面内容</h2>
			</div>
				<img style="max-width: 100%;" alt="" src="${info.content }">
		</c:if>
	</div>
	
 	<form action="${ctx }/employee/uploadFile" onsubmit="return check();" name="example" style="margin: 40px 0px 0px 40px;" method="post" enctype="multipart/form-data">
	   <input type="file" name="file" id="file" value="请选择图片"/>
	   <input type="hidden" name="isZip" value="yes"/>
	   <input type="submit" value="上传" />
	</form>
</body>
<script type="text/javascript">
function check(){
	if($("#file").val() == ""){
		alert("请选择图片");
		return false;
	}
}
</script>
</html>
 