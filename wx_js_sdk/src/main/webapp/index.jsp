<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<title></title>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/config.js"></script>
<script src="js/utils.js"></script>
<script type="text/javascript">
	var ticket = null;
	var timestamp = nowTime();
	var nonceStr = randomStr(false, 8, 8);
	var signature = null;
	setTimeout('getTicket()',2000);
	//获取ticket
	function getTicket(){
		//alert("getTicket");
		$.ajax({
			type : "post",
			url : "wxApi/getSignature",
			data : {
				"appId" : "wx287ec711f3bdb1c6",
				"timestamp" : timestamp,
				"noncestr" : nonceStr,
				"url" : window.location.href
			},
			async : true,
			success : function(data) {
				signature = data;
				config();
			},
			error : function(data) {
				console.log(data);
			}
		});
	}
	

	//通过config接口注入权限验证配置
	function config() {
		//console.log("signature=" + signature);
		wx.config({
			debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId : 'wx287ec711f3bdb1c6', // 必填，公众号的唯一标识
			timestamp : timestamp, // 必填，生成签名的时间戳
			nonceStr : nonceStr, // 必填，生成签名的随机串
			signature : signature, // 必填，签名，见附录1
			jsApiList : [ 'onMenuShareTimeline', 'onMenuShareAppMessage',
					'onMenuShareQQ', 'onMenuShareWeibo', 'onMenuShareWeibo','onMenuShareQZone' ]
		// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
	}
	//通过ready接口处理成功验证
	wx.ready(function() {
		alert("======ready===>>>>>>");
		shareTo();
		shareToFriend();
		 
		// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后
	});
	wx.error(function(res) {
		console.log("======error===>>>>>>");
		// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
	});

	function shareTo() {
		//获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
		wx.onMenuShareTimeline({
			title : '分享标题', // 分享标题
			link : window.location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
			imgUrl : window.location.href, // 分享图标
			success : function() {
				// 用户确认分享后执行的回调函数
				alert("分享成功");
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
				alert("关闭分享");
			},
			fail:function(){
				alert("分享失败");
			}
		});
		
	}
	
	function shareToFriend(){
		//获取“分享给朋友”按钮点击状态及自定义分享内容接口
		wx.onMenuShareAppMessage({
		    title: '分享标题分享标题分享标题', // 分享标题
		    desc: '分享描述分享描述分享描述', // 分享描述
		    link: window.location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		    imgUrl: window.location.href, // 分享图标
		    success: function (res) { 
		        // 用户确认分享后执行的回调函数
		    	alert("给朋友分享成功");
		    },
		    fail:function(res){
				alert("分享失败");
			},
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    	alert("取消给朋友分享");
		    }
		});
		alert("注册成功");
	}
	
</script>
</head>

<body>JS-SDK TEST
</body>

</html>