<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../common/taglibs.jsp"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script type="text/javascript" src="${ctx}/js/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" href="${ctx}/js/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="${ctx }/js/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="${ctx }/js/kindeditor/kindeditor-all.js"></script>
	<script charset="utf-8" src="${ctx }/js/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="${ctx }/js/kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content"]', {
				cssPath : '${ctx }/js/kindeditor/plugins/code/prettify.css',
				uploadJson : '${ctx }/js/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '${ctx }/js/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterBlur:function(){this.sync();},
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<script type="text/javascript" src="${ctx}/js/jquery.qrcode.min.js"></script>
<body>
	<div style="margin: 20px 0px 0px 40px;" id="qrcode" class="num"></div>
	<div style="margin: 2px 0px 0px 80px;font-weight: bolder;">浏览二维码</div>
	
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
				<h2>页面内容</h2>
			</div>
				${info.content }
		</c:if>
	</div>
	
	
	<button type="button" class="btn btn-success" onclick="showEdit()" style="margin: 20px 40px 100px 40px;">编辑内容</button>
	<form onsubmit="return check();" name="example" method="post" action="editEmployeeExtensionInfo" style="display: none;margin: 40px 0px 0px 40px;">
		<textarea name="content" id="content" cols="100" rows="8" style="width:700px;height:500px;">${info.content }</textarea>
		<br />
		<input type="submit" name="button" value="提交内容" />
	</form>
</body>
<script>
function showEdit(){
	$("form").show();
	$("#infoText").hide();
	$("button").hide();
}
function check(){
	var content = $.trim($("#content").val());  
    content = $("#content").val();  
    if(content == ""){
    	alert("请填写内容");
    	return false;
    }
}
</script>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>