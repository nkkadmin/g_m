<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<title>♥感恩回馈♥好礼不停♥</title>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css">
<script src="${ctx}/js/jquery.min.js"></script>
<script src="${ctx}/js/d.js"></script>
<style type="text/css">
.inpbox {
	background: #e6e6e6;
	display: block;
	font-weight: normal;
	line-height: 250%;
	color: #737373;
	padding-left: 10px;
	border-radius: 3px;
	margin-bottom: 3px;
}

label.label_sel {
	background: #428bca !important;
	color: #fff !important;
}
</style>
<body>
	<div class="wrap entry-container">
		<div class="inner">
			<div class="form-description">

				<div id="content_div">
					<p align="center">
						<img style="max-width: 100%;" src="${ctx}/images/运费新.jpg"> <b></b><i></i><u></u><sub></sub><sup></sup><strike></strike><br>
					</p>
					<p>
						<br>
					</p>
				</div>
				<div style="display: none;" id="success_div">
					<p align="center">
						<font color="#ff0000" face="Comic Sans MS" size="6">恭喜您领取成功</font>
					</p>
					<p align="center">
						<font color="#000000" face="Comic Sans MS" size="3"
							style="background-color: rgb(255, 0, 0);">请您把领取成功后的此界面截图并群发给您全部好友即可参加官方抽奖活动：
							iPhone8  iPhonex    美图t8s 最低百元红包至千元红包不等(188.88－1888.88)    
							ps：此活动在免费送取黄金吊坠活动结束后开始，凡是参加领取金苹果吊坠拍拍反馈图给客服的都可以领取到上述其中之一</font>
					</p>
					<p align="center" >
						<img alt="" src="${ctx}/images/phone1.jpg" width="35%" style="display: inline-block">
						<img alt="" src="${ctx}/images/phone2.jpg" width="30%" style="display: inline-block">
						<img alt="" src="${ctx}/images/phone3.jpg" width="30%" style="display: inline-block">
					</p>
					<p align="center">
						<font color="#000000" face="Comic Sans MS" size="5"
							style="background-color: rgb(255, 0, 0);">48小时内发货，发出后包裹信息会以短信形式发送到您的手机</font>
					</p>
					<p align="center">
						<font color="#000000" face="Comic Sans MS" size="5"
							style="background-color: rgb(255, 0, 0);">贵重物品注意跟踪物流，以免快递丢失</font>
					</p>
					<p align="center">
						<img style="max-width: 100%;" src="${ctx}/images/小苹果尾页.jpg"><b></b><i></i><u></u><sub></sub><sup></sup><strike></strike><br>
					</p>
					<p>
						<br>
					</p>
				</div>
			</div>
		</div>

		<div id="join_div">
			<form class="center with-shadow" id="form1" method="post">
				<div class="form-content">
					<div class="form-group">
						<input type="hidden" name="empid" value="${empId }"/>
						<input type="hidden" name="code" value="${code }"/>
						<div class="form-group">
							<label class="field-title">款式 <span class="red">*</span></label>
							<div class="col-xs-12" id="sel_color">
								<label class="inpbox label_sel" style="font-size: 14px;"><input
									type="radio" value="金苹果吊坠" name="shopname" checked>金苹果吊坠</label>

							</div>
						</div>
						<img src="${ctx}/images/款式b.jpg" />
						<div class="form-group mt20">
							<label class="field-title">收货人姓名 <span class="red">*</span></label>
							<div class="field-content">
								<input name="receiptname" id="name" type="text">
							</div>
						</div>

						<div class="form-group">
							<label class="field-title">手机号码 <span class="red">*</span></label>
							<div class="field-content">
								<input name="receiptphone" id="mobile" type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="field-title">收货地址 <span class="red">*</span></label>
							<div class="field-content address">
								<div data-toggle="distpicker">
									<select class="form-control tpl-province" name="province"></select>
									<select class="form-control tpl-city" name="city"></select> <select
										class="form-control tpl-district" name="district"></select>
								</div>
								<div class="mt10">
									<textarea name="detailaddress" id="detailaddress"
										placeholder="此处填写您详细的收货地址：XX乡镇XX街道XX小区"></textarea>
								</div>
							</div>
						</div>
						<div class="form-group"
							style="margin: 10px 0px 0px; padding: 0px; color: rgb(51, 51, 51); font-family: 'Helvetica Neue', Helvetica, 'Hiragino Sans GB', 'Microsoft YaHei', Arial, sans-serif; font-size: 14px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: 21px; orphans: auto; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 1; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);">
							<label class="field-title"
								style="font-weight: bold; margin-bottom: 10px; display: block; font-size: 18px;">电话充值卡类型<span
								class="Apple-converted-space">&nbsp;</span><span class="red"
								style="color: red;">*</span></label>
							<div style="margin: 0px; padding: 0px;">
								<select style="width: 300px; height: 30px;" name="phonepaytypeid" id="phonepaytypeid">&nbsp;
									<option value="0">请选择充值卡类型</option>&nbsp;
									<option value="1">移动充值卡100元</option>&nbsp;
									<option value="2">联通充值卡100元</option>&nbsp;
									<option value="3">电信充值卡100元</option>&nbsp;
								</select>
							</div>
						</div>
						<p>
							<br class="Apple-interchange-newline"> <b></b><i></i><u></u><sub></sub><sup></sup><strike></strike><br>
						</p>
						<div class="form-group">
							<label class="field-title"><span style="color: red;">温馨提示</span></label>
							<div class="col-xs-12">个别表格没有的县市，将省市区县及收货地址填入详细地址即可</div>
						</div>
						<input type="hidden" id="aid" name="aid" value="1" /> <input
							type="hidden" name="sid" id="sid" value="58" />

						<div class="field submit-field ">
							<div class="value">
								<input id="subbtn" type="button" class="btn btn-primary"
									onclick="checkInput();" value="填写好了  确认提交">
								<!--<a class="report-form" href="javascript:if(confirm(%27http://chinaau.aktvo.cn/Public/report/report1.html  \n\nThis file was not retrieved by Teleport Pro, because it is addressed on a domain or path outside the boundaries set for its Starting Address.  \n\nDo you want to open it from the server?%27))window.location=%27http://chinaau.aktvo.cn/Public/report/report1.html%27" tppabs="http://chinaau.aktvo.cn/Public/report/report1.html">举报</a>-->
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

<script src="${ctx}/js/distpicker.data.js"></script>
<script src="${ctx}/js/distpicker.js"></script>
<script src="${ctx}/js/layer.min.js"></script>

<script type="text/javascript">
	$("div#sel_color label").click(function() {
		$("div#sel_color label").removeClass('label_sel');
		$(this).addClass('label_sel');
	});

	function checkInput() {
		

		 $('#subbtn').attr("disabled", true);

		if ($("input[name=shopname]:checked").length < 1) {
			layer.open({
				content : '请选择一个款式',
				btn : '我知道了'
			});
			$('#subbtn').attr("disabled", false);
			return false;
		}

		if ($('#name').val() == '') {
			layer.open({
				content : '收货人姓名不能为空',
				btn : '我知道了'
			});
			$('#subbtn').attr("disabled", false);
			return false;
		}

		if ($('#mobile').val() == '') {
			layer.open({
				content : '手机号码不能为空',
				btn : '我知道了'
			});
			$('#subbtn').attr("disabled", false);
			return false;
		}

		if (!checkMobile($('#mobile').val())) {
			layer.open({
				content : '手机号码有误，请重填',
				btn : '我知道了'
			});
			$('#subbtn').attr("disabled", false);
			return false;
		}

		var province = $("select[name='province'] option:selected").text();
		var city = $("select[name='city'] option:selected").text();
		var district = $("select[name='district'] option:selected").text();

		if (province == '省/直辖市' || city == '市' || province == '' || city == ''
				|| district == '区/县' || district == '') {
			layer.open({
				content : '请填写正确的省市区',
				btn : '我知道了'
			});
			$('#subbtn').attr("disabled", false);
			return false;
		}

		if ($('#detailaddress').val() == '') {
			layer.open({
				content : '详细街道地址不能为空',
				btn : '我知道了'
			});
			$('#subbtn').attr("disabled", false);
			return false;
		}

		if ($('#detailaddress').val().length < 4) {
			layer.open({
				content : '街道地址少于4个字符,请填写详细',
				btn : '我知道了'
			});
			$('#subbtn').attr("disabled", false);
			return false;
		}
		
		if($('#phonepaytypeid').val() == '0'){
			layer.open({
				content : '请选择电话充值卡类型',
				btn : '我知道了'
			});
			$('#subbtn').attr("disabled", false);
			return false;
		}

		var jz = layer.open({
			type : 2,
			content : '提交中',
			shadeClose : false
		}); 
		
	  $.ajax({
			type : "POST", //提交方式
			url : "${ctx}/order/addOrder",
			data : $("#form1").serialize(),
			dataType : "json",
			success : function(result) {
				if (result.success) {
					layer.close(jz);
					$('#content_div').hide();
					$('#join_div').hide();
					$(window).scrollTop(0);
					$('#success_div').show();
				} else {
					layer.close(jz);
					layer.open({
						content : result.message + ',订单提交失败！',
						btn : '我知道了'
					});
					$('#subbtn').attr("disabled", false);
				}
			},
			error : function() {
				layer.close(jz);
				layer.open({
					content : '您的网络不稳定，请关闭页面后重试！',
					btn : '我知道了'
				});
				$('#subbtn').attr("disabled", false);
			}
		});  

	}

	//验证必须是正确的手机号
	function checkMobile(val) {
		var reg = /^1[34578]\d{9}$/;
		if (reg.test(val)) {
			return true;
		} else {
			return false;
		}
	}
</script>
</html>
