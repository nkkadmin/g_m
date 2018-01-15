<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>我的订单</title>
<meta name="format-detection" content="telephone=no, address=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent" />
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx }/js/bootstrap/fileinput/fileinput.css"/>	 
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
	
	
 	<form onsubmit="return check();" name="example" style="margin: 40px 0px 0px 40px;">
		<input type="file" name="file" id="district-matching" multiple value="上传图片"/>
		<span>提示：上传多张只会保存最后一张。如需替换，重新上传一张即可。</span>		
	</form>
</body>
<script type="text/javascript" src="${ctx }/js/bootstrap/fileinput/fileinput.js"></script>

<script>
$(function(){
	 //样板间上传图片配置
    $("#district-matching").fileinput({
        language: 'zh', //设置语言
        uploadUrl:"uploadFile", //上传的地址
        allowedPreviewTypes : ['image'],
        allowedFileTypes: ['image'],
        allowedFileExtensions: ['jpg','jpeg', 'gif', 'png'],//接收的文件后缀
        removeFromPreviewOnError:true,
        uploadAsync: true, //false同步,true 异步
        showUpload:false, //是否显示上传按钮
        showRemove :false, //显示移除按钮
        showPreview :true, //是否显示预览
        showCaption:false,//是否显示标题
        showCancel: true, //是否显示文件上传取消按钮
        showClose: false, //是否在预览中显示关闭图标
        browseClass:"btn btn-info", //按钮样式
        zoomIndicator: "",
        browseLabel:"上传图片",
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileSize:10240,//单位为kb，如果为0表示不限制文件大小
        maxFileCount:1, //表示允许同时上传的最大文件个数
        autoReplace: true, //达到限制后是否自动替换预览中的文件
        enctype:'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-remove'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
    }).on("filebatchselected", function () {
         $("#district-matching").fileinput('upload');
    });
})
</script>
</html>
 