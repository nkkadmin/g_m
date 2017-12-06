<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>后台管理</title>
	<link rel="stylesheet" href="${ctx }/manager/Css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/manager/Css/htmleaf-demo.css">
	<style type="text/css">
		.form-horizontal{
		    background: #ddd;
		    padding-bottom: 40px;
		    border-radius: 15px;
		    text-align: center;
		}
		.form-horizontal .heading{
		    display: block;
		    font-size: 35px;
		    font-weight: 700;
		    padding: 35px 0;
		    border-bottom: 1px solid #f0f0f0;
		    margin-bottom: 30px;
		    color:#204077;
		}
		.form-horizontal .form-group{
		    padding: 0 40px;
		    margin: 0 0 25px 0;
		    position: relative;
		}
		.form-horizontal .form-control{
		    background: #f0f0f0;
		    border: none;
		    border-radius: 20px;
		    box-shadow: none;
		    padding: 0 20px 0 45px;
		    height: 40px;
		    transition: all 0.3s ease 0s;
		}
		.form-horizontal .form-control:focus{
		    background: #e0e0e0;
		    box-shadow: none;
		    outline: 0 none;
		}
		.form-horizontal .form-group i{
		    position: absolute;
		    top: 12px;
		    left: 60px;
		    font-size: 17px;
		    color: #c8c8c8;
		    transition : all 0.5s ease 0s;
		}
		.form-horizontal .form-control:focus + i{
		    color: #00b4ef;
		}
		.form-horizontal .fa-question-circle{
		    display: inline-block;
		    position: absolute;
		    top: 12px;
		    right: 60px;
		    font-size: 20px;
		    color: #808080;
		    transition: all 0.5s ease 0s;
		}
		.form-horizontal .fa-question-circle:hover{
		    color: #000;
		}
		.form-horizontal .main-checkbox{
		    float: left;
		    width: 20px;
		    height: 20px;
		    background: #224075;
		    border-radius: 50%;
		    position: relative;
		    margin: 5px 0 0 5px;
		    border: 1px solid #224075;
		}
		.form-horizontal .main-checkbox label{
		    width: 20px;
		    height: 20px;
		    position: absolute;
		    top: 0;
		    left: 0;
		    cursor: pointer;
		}
		.form-horizontal .main-checkbox label:after{
		    content: "";
		    width: 10px;
		    height: 5px;
		    position: absolute;
		    top: 5px;
		    left: 4px;
		    border: 3px solid #fff;
		    border-top: none;
		    border-right: none;
		    background: transparent;
		    opacity: 0;
		    -webkit-transform: rotate(-45deg);
		    transform: rotate(-45deg);
		}
		.form-horizontal .main-checkbox input[type=checkbox]{
		    visibility: hidden;
		}
		.form-horizontal .main-checkbox input[type=checkbox]:checked + label:after{
		    opacity: 1;
		}
		.form-horizontal .text{
		    float: left;
		    margin-left: 7px;
		    line-height: 20px;
		    padding-top: 5px;
		    text-transform: capitalize;
		    color: #224075;
		}
		.form-horizontal .btn{
		    float: right;
		    font-size: 14px;
		    color: #fff;
		    background: #204077;
		    border-radius: 30px;
		    padding: 10px 25px;
		    border: none;
		    text-transform: capitalize;
		    transition: all 0.5s ease 0s;
		}
		@media only screen and (max-width: 479px){
		    .form-horizontal .form-group{
		        padding: 0 25px;
		    }
		    .form-horizontal .form-group i{
		        left: 45px;
		    }
		    .form-horizontal .btn{
		        padding: 10px 20px;
		    }
		}
		.center-c{
			margin-top: 100px;
		}
	</style>
</head>
<body>
<div class="htmleaf-container">
	<div class="demo form-bg" style="padding: 20px 0;">
	        <div class="container">
	            <div class="row">
	                <div class="col-md-offset-3 col-md-6 center-c">
	                    <form class="form-horizontal">
	                        <span class="heading">后台登录</span>
	                        <div class="form-group">
	                            <input type="text" class="form-control" id="name" name="name" placeholder="用户名">
	                            <i class="fa fa-user"></i>
	                        </div>
	                        <div class="form-group help">
	                            <input type="password" class="form-control" id="password" name="password" placeholder="密　码">
	                            <i class="fa fa-lock"></i>
	                            <a href="#" class="fa fa-question-circle"></a>
	                        </div>
	                        <div class="form-group">
	                            <div class="main-checkbox">
	                                <input type="checkbox" value="None" id="checkbox1" name="check"/>
	                                <label for="checkbox1"></label>
	                            </div>
	                            <span class="text">Remember me</span>
	                            <button onclick="loginIn();" type="submit" class="btn btn-default">登录</button>
	                        </div>
	                        <div id="errorMsg" style="color:red"></div>
	                    </form>
	                    
	                </div>
	                
	            </div>
	        </div>
	    </div>
</div>
<iframe id="rfFrame" name="rfFrame" src="about:blank"
		style="display: none;"></iframe>
<script src="${ctx }/manager/Js/jquery.js"></script>
<script>
function loginIn(){
	document.forms[0].target = "rfFrame";
	$.ajax({
		url: "${ctx}/system/login",
		type: "post",
		data: $(".form-horizontal").serialize(),
		dataType: "json",
		success: function(data){
			if(data.success){
				window.location.href="${ctx}/system/index";
			}else{
				$("#errorMsg").text(data == null ? "登录失败" : data.message);
			}
		},
		error: function(data){
			console.log("error");
		}
	})
}
</script>
</body>
</html>