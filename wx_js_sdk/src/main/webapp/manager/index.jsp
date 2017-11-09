<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx }/css/weui.min.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx }/css/jquery-weui.min.css">
<link href="${ctx}/manager/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/manager/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/manager/assets/css/main-min.css" rel="stylesheet" type="text/css" />

</head>
<body>

	<div class="header">

		<div class="dl-title">
			<!--<img src="/chinapost/Public/assets/img/top.png">-->
		</div>

		<div class="dl-log">
			欢迎您，<span class="dl-log-user">${currentSessionCompany.name}</span><a href="javaScript:logOut();" title="退出系统" class="dl-log-quit">[退出]</a>
		</div>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected"><div class="nav-item-inner nav-home">系统管理</div></li>
			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">
		</ul>
	</div>
	<script type="text/javascript" src="${ctx}/manager/assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/manager/assets/js/bui-min.js"></script>
	<script type="text/javascript" src="${ctx}/manager/assets/js/common/main-min.js"></script>
	<script type="text/javascript" src="${ctx}/manager/assets/js/config-min.js"></script>
	<script src="${ctx }/js/jquery-weui.min.js"></script>
	<script>
		BUI.use('common/main', function() {
			var config = [ {
				id : '1',
				homePage : '6',
				menu : [ {
					text : '系统管理',
					items : [ {
						id : '6',
						text : '员工后台二维码',
						href : '${ctx}/system/employeeadmincoreUI',
						closeable : false
					},{
						id : '7',
						text : '公司角色管理',
						href : '${ctx}/system/companyUI',
						closeable : false
					},{
						id : '7',
						text : '部门管理',
						href : '${ctx}/system/departmentUI',
						closeable : false
					},{
						id : '4',
						text : '员工信息管理',
						href : '${ctx}/system/employeeUI'
					}, {
						id : '3',
						text : '客户订单管理',
						href : '${ctx}/system/orderUI'
					},  {
						id : '12',
						text : '员工订单量统计',
						href : '${ctx}/system/employeeordernumUI'
					} ]
				} ]
			}];
			new PageUtil.MainPage({
				modulesConfig : config
			});
		});
		
		function logOut(){
			$.confirm({
				  title: '系统提示',
				  text: '确认退出系统吗？',
				  onOK: function () {
				    //点击确认
					  $.ajax({
							url:'${ctx }/system/logOut',
							type:'GET',
							data:{"empId":'${currentSessionCompany.id}'},
							dataType:'json',
							success:function(data){
								console.log(data);
								if(data != null && data.success){
									window.location.href = "${ctx}/system/loginUI";
								}else{
									$.alert(data.message, "系统提示");
								}
							},
							error:function(data){
								
							}
						});
				  },
				  onCancel: function () {
				  }
			});
		}
	</script>
	<div style="text-align: center;"></div>
</body>
</html>