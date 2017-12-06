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
	<input type="hidden" name="roleDescriptName" value="COMPANY"/>
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft"><span style="color: red;">*</span>姓名</td>
            <td><input type="text" name="name" id="name"/></td>
        </tr>
        <tr>
        	<td width="10%" class="tableleft"><span style="color: red;">*</span>电话</td>
        	<td><input type="text" name="phone" id="phone"/></td>
        </tr>
        <tr>
        	<td width="10%" class="tableleft"><span style="color: red;">*</span>密码</td>
        	<td><input type="text" name="password" id="password"/></td>
        </tr>
        <tr>
            <td class="tableleft"><span style="color: red;">*</span>所属部门</td>
            <td>
            	<select class="roleselect" name="departmentid" id="departmentid">
            		<c:forEach items="${departments }" var="department">
            			<option value="${department.id }">${department.name }</option>
            		</c:forEach>
            	</select>
            </td>
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
		window.location.href="${ctx}/system/companyUI";
 });
});
function save() {
	//校验表单
	if($("#name").val() == ''){
		$.alert("姓名不能为空","系统提示");
		return false;
	}
	if($("#phone").val() == ''){
		$.alert("电话不能为空","系统提示");
		return false;
	}else{
		if(!checkMobile($("#phone").val())){
			$.alert("电话有误","系统提示");
			return false;
		}
	}
	
	if($("#password").val() == ''){
		$.alert("密码不能为空","系统提示");
		return false;
	}
	
	if($("#departmentid").val() == ''){
		$.alert("请选择部门","系统提示");
		return false;
	}
	
	//验证必须是正确的手机号
	function checkMobile(val){
		var reg =/^1[34578]\d{9}$/;
		if(reg.test(val)){
			return true;
		}else{
			return false;
		}
	}
	
	$.ajax({
		url : '${ctx}/system/addEmp', //接口地址
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
