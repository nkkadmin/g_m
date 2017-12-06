<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link href="${ctx }/css/weui.min.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctx }/css/jquery-weui.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/manager/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/manager/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/manager/Css/style.css" />

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
            <td width="10%" class="tableleft">订单号</td>
            <td><input type="hidden" name="id" id="orderId" value="${order.id }"/>${order.id }</td>
        </tr>
        <tr>
        	<td width="10%" class="tableleft">款式</td>
        	<td>${order.shopname }</td>
        </tr>
        <tr>
        	<td width="10%" class="tableleft">收货人</td>
        	<td>${order.receiptname }</td>
        </tr>
        <tr>
        	<td width="10%" class="tableleft">电话</td>
        	<td><input type="text" name="receiptphone" id="receiptphone" value="${order.receiptphone }"/></td>
        </tr>
        <tr>
        	<td width="10%" class="tableleft">收货地址</td>
        	<td>
        	<textarea rows="3" cols="5" name="receiptaddress" id="receiptaddress">${order.receiptaddress }</textarea>
        	</td>
        </tr>
        <tr>
        	<td width="10%" class="tableleft">推荐员工</td>
        	<td>${order.empName }</td>
        </tr>
        <tr>
        	<td width="10%" class="tableleft">下单时间</td>
        	<td>${order.createdate }</td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="button" class="btn btn-primary" type="button" onclick="save()">修改</button>
                &nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
</body>
<script type="text/javascript" src="${ctx}/manager/Js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/manager/Js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/manager/Js/ckform.js"></script>
<script type="text/javascript" src="${ctx}/manager/Js/common.js"></script>
<script src="${ctx }/js/jquery-weui.min.js"></script>
<script>
$(function () {       
$('#backid').click(function(){
		window.location.href="${ctx}/system/orderUI";
 });
});
function save() {
	$.ajax({
		url : '${ctx}/system/editOrder', //接口地址
		type : 'post', //调用方法
		data : $("form").serialize(), //调用的数据
		dataType : 'json', //返回数据类型
		success : function(data) {
			$.alert(data == null ? "修改失败" : data.message,"系统提示");
		},
		error : function(data) {
			console.log("error");
		}
	})
}
</script>
</html>
