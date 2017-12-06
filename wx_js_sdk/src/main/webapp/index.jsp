<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<title></title>
<script src="http://192.168.124.8/wx_js_sdk/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="http://192.168.124.8/wx_js_sdk/js/WeixinApi.js"></script>
<script>
 var dataForWeixin = {
  appId: "",
  MsgImg: "Christmas/201012189457639.gif",//显示图片
  TLImg: "Christmas/201012189457639.gif",//显示图片
  url: "Christmas/6.html?stra=!u738B!u4F1F",//跳转地址
  title: "将我的思念和祝福送给您,颐养源祝大家圣诞快乐",//标题内容
  desc: "将我的思念和祝福送给您,颐养源祝大家圣诞快乐",//描述内容
  fakeid: "",
  callback: function () { alert("aaaaaaa"); }
 };
 (function () {
  var onBridgeReady = function () {
  WeixinJSBridge.on('menu:share:appmessage', function (argv) {
   WeixinJSBridge.invoke('sendAppMessage', {
   "appid": dataForWeixin.appId,
   "img_url": dataForWeixin.MsgImg,
   "img_width": "120",
   "img_height": "120",
   "link": dataForWeixin.url,
   "desc": dataForWeixin.desc,
   "title": dataForWeixin.title
   }, function (res) { (dataForWeixin.callback)(); });
  });
  
  
  };
  
  if (document.addEventListener) {
  document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
  } else if (document.attachEvent) {
  document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
  document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
  }
 })();
</script>
</head>

<body>JS-SDK TEST
</body>

</html>