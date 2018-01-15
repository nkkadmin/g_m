<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<title></title>
<style>
img {
	max-width: 100%;
	height: auto;
}

body, button, input, select, textarea, h1, h2, h3, h4, h5, h6 {
	font-family: Microsoft YaHei, Tahoma, Helvetica, Arial, sans-serif;
}

#foot {
	position: fixed;
	_position: absolute;
	bottom: 0px;
	_bottom: 0px;
	_margin-top: expression(this.style.pixelHeight + document.documentElement.scrollTop)
}
</style>
<style>
button {
	display: inline-block;
	width: 100%;
	height: 50px;
	border-radius: 3px;
	background: #ff391f;
	text-decoration: none;
	line-height: 39px;
	text-align: center;
	font-size: 17px;
	color: #fff;
	font-weight: 900;
	border: none;
	margin-top: 5px;
	margin-bottom: 5px;
	background-image: url(button.jpg)
	background-size: 100% 100%;
}

.STYLE1 {
	color: #FF0000
}
</style>
<link href="${ctx }/css/show_style.css" rel="stylesheet" />
</head>

<body>

	<div class="inner" id="inner">
		<div class="wrap">
			<div id="content"><img style="max-width: 100%;" alt="" src="${info.content }"></div>
		</div>
	</div>
</body>
</html>

