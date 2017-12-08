<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <script type="text/javascript" src="${ctx}/manager/Js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/manager/Js/bootstrap.js"></script>
    <script type="text/javascript" src="${ctx}/manager/Js/ckform.js"></script>
    <script type="text/javascript" src="${ctx}/manager/Js/common.js"></script>
	<script src="${ctx }/js/jquery-weui.min.js"></script>
	
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
	<input type="hidden" name="id" value="${ips.id }"/>
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft"><span style="color: red;">*</span>域名</td>
            <td><input type="text" name="name" id="name" value="${ips.name }"/>（格式：www.xxx.cn）</td>
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
		window.location.href="${ctx}/system/ipsUI";
 });
});
function save() {
	//校验表单
	if($("#name").val() == ''){
		$.alert("域名不能为空","系统提示");
		return false;
	}
	 
	$.ajax({
		url : '${ctx}/system/editIp', //接口地址
		type : 'post', //调用方法
		data : $("form").serialize(), //调用的数据
		dataType : 'json', //返回数据类型
		success : function(data) {
			$.alert(data != null ? data.message : "保存失败","系统提示");
		},
		error : function(data) {
			console.log("error");
		}
	})
}
</script>
</html>
