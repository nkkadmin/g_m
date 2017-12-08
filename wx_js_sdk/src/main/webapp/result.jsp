<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta id="viewport" name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <title>投诉</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/weui.min.css"/>
</head>
<body>

<div class="weui-msg">
    <div class="weui-msg__icon-area">
                <i class="weui-icon-success weui-icon_msg"></i>
            </div>
    <div class="weui-msg__text-area">
                <h2 class="weui-msg__title">投诉已提交</h2>
        <p class="weui-msg__desc">感谢你的参与，微信坚决反对色情、暴力、欺诈等违规信息，我们会认真处理你的举报，维护绿色、健康的网络环境。</p>

            </div>
    <div class="weui-msg__opr-area">
        <p class="weui-btn-area">
            <a href="javascript:WeixinJSBridge.invoke('closeWindow')" class="weui-btn weui-btn_primary">关闭</a>
        </p>
    </div>
</div>
</body>
</html>