<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${ctx}/manager/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/manager/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/manager/Css/style.css" />
    <script type="text/javascript" src="${ctx}/manager/Js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/manager/Js/bootstrap.js"></script>
    <script type="text/javascript" src="${ctx}/manager/Js/ckform.js"></script>
    <script type="text/javascript" src="${ctx}/manager/Js/common.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
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
<form action="##" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">员工姓名</td>
            <td><input type="text" name="name" id="name"/></td>
        </tr>
        <tr>
        	<td width="10%" class="tableleft">电话</td>
        	<td><input type="text" name="phone" id="phone"/></td>
        </tr>
        <tr>
        	<td width="10%" class="tableleft">密码</td>
        	<td><input type="text" name="password" id="password"/></td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="button" class="btn btn-primary" type="button" onclick="save()">保存</button>
                &nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
        <tr>
        	<td class="tableleft"></td><td id="tips"></td>
        </tr>
    </table>
</form>
</body>
<script>
$(function () {       
$('#backid').click(function(){
		window.location.href="${ctx}/system/employeeUI";
 });
});
function save() {
	console.log($("form").serialize());
	$.ajax({
		url : '${ctx}/employee/addEmp', //接口地址
		type : 'post', //调用方法
		data : $("form").serialize(), //调用的数据
		dataType : 'json', //返回数据类型
		success : function(data) {
			console.log(data);
			$("#tips").text(data.message);
		},
		error : function(data) {
			console.log("error");
		}
	})
}
</script>
</html>
