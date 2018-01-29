<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<title>周♡大♡生~感恩回馈！！快来拿吧！！！</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css">
<script src="${ctx }/js/jquery-3.2.1.min.js"></script>
<script src="${ctx }/js/d.js"></script>
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

#success_div {
	z-index: 9999;
	position: fixed;
	top: 0;
	bottom: 0;
	background: #fff;
	padding: 0 15px;
}

#success_div img {
	width: 80%;
	border: 0;
	margin: 0 auto;
	display: block;
	padding-top: 30px;
}

#success_div h1 {
	padding-bottom: 20px;
}

#success_div h2 {
	font-size: 14px;
	text-align: center;
	width: 100%;
	height: 30px;
	line-height: 30px;
}

#success_div h3 {
	font-size: 1rem !important;
}

#success_div h4 {
	font-size: .9rem !important;
}
</style>
<link href="${ctx }/css/layer.css" type="text/css" rel="styleSheet"
	id="layermcss">
</head>
<body>
	<div class="wrap entry-container">
		<div class="inner">
			<div class="form-description">
				<div id="content_div">
					<p>
						<img style="max-width: 100%;" src="${ctx }/images/yunf.jpg">
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
						<input type="hidden" name="empid" value="${empId }" /> <input
							type="hidden" name="code" value="${code }" />
						<div class="form-group">
							<label class="field-title">请选择产品<span class="red">*</span></label>
							<div class="field-content">
								<div class="list clearfix" id="js_prolists">
									<ul>
										<li><div class="pwrap">
												<div class="img-wrap">
													<img src="${ctx }/img/1823c04c455e2601.png">
												</div>
												<div>
													<input type="radio" name="shopname" value="金苹果吊坠"
														checked="">金苹果吊坠
												</div>
											</div></li>
									</ul>
								</div>
							</div>
						</div>

						<div class="form-group mt20">
							<label class="field-title">收货人姓名 <span class="red">*</span></label>
							<div class="field-content">
								<input name="receiptname" id="name" type="text">
							</div>
						</div>

						<div class="form-group " data-role="teltype">
							<label class="field-title">电话充值卡类型<span class="red">*</span></label>
							<div class="field-content teltype">
								<div>
									<select style="width: 300px; height: 30px;"
										name="phonepaytypeid" id="teltype_provinc">
										<option value="1">移动充值卡100元</option>
										<option value="2">联通充值卡100元</option>
										<option value="3">电信充值卡100元</option>
									</select>
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
											placeholder="详细地址"></textarea>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="field-title">邮费自理 <span class="red">*</span></label>
								<div class="col-xs-12" id="sel_color">
									<label class="inpbox" style="font-size: 14px;"><input
										type="checkbox" value="1" name="agree">请接受邮费自理（邮费在15至39之间，收到货支付给快递人员即可，超过39可拒收，不接受的不予发货！）</label>
								</div>
							</div>

							<div class="form-group">
								<label class="field-title"><span style="color: red;">温馨提示</span></label>
								<div class="col-xs-12">
									签收后拍照反馈，好评宣传，我们会以红包形式给亲全额报销运费的！为确保宝贝准确到达您手中，请认真填写您的真实信息。支持开箱验货！！！！
								</div>
							</div>
							<input type="hidden" id="pattern" name="pattern"
								value="周♡六♡福~感恩回馈！！快来拿吧！！！">
							<div class="field submit-field ">
								<div class="value">
									<input id="subbtn" type="button" class="btn btn-primary"
										onclick="checkInput();" value="填写好了  确认提交">
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>

	</div>
	<div style="display: none;"  id="success_div" class="success_div1">
		<h4 style="text-align: center;">
			<p font-size:medium;"=""
				style="word-wrap: break-word; margin-top: 10px; margin-bottom: 5px; color: #000; font-family: &amp; amp; amp; amp; amp; quot; sans serif&amp;amp; amp; amp; amp; quot; , tahoma , verdana, helvetica; font-size: 12px;">
				<span style="font-size: 18px;"><span style="color: #000;">公司将于48小时内统一快递发货，请您耐心等待3-5天左右。</span></span>
			</p>
			<img src="${ctx }/img/4c6a54bc7ed59e2a.jpg">
			<p font-size:medium;"=""
				style="word-wrap: break-word; margin-top: 5px; margin-bottom: 5px; color: rgb(0, 0, 0); font-family: &amp; amp; amp; amp; amp; quot; sans serif&amp;amp; amp; amp; amp; quot; , tahoma , verdana, helvetica; font-size: 12px;">
				<span style="font-size: 18px;"> <span
					style="color: rgb(255, 0, 0);font-weight: bold;">温馨提示：收到礼品支持开箱验货，支持各大金店检验。收到礼品的10个工作日内会由您当地所在周大生店面联系您到店面领取66.66紅包和参与下方所述抽取奖品活动(最低抽取188.88紅包)
				</span>
				</span>
			</p>
			<br />
			<p font-size:medium;"=""
				style="word-wrap: break-word; margin-top: 5px; margin-bottom: 5px; color: rgb(0, 0, 0); font-family: &amp; amp; amp; amp; amp; quot; sans serif&amp;amp; amp; amp; amp; quot; , tahoma , verdana, helvetica; font-size: 12px;">
				<span style="font-size: 18px;font-weight: bold;"> <span
					style="color: rgb(255, 0, 0);">收到礼品，签收拍照反馈即可参加官方抽奖活动：iPhone
						8.iPhone x.美图T8s 最低百元紅包至千元紅包不等（188.88.-1888.88） </span>
				</span>
			</p>

		</h4>

	</div>

	<script src="${ctx }/js/distpicker.data.js"></script>
	<script src="${ctx }/js/distpicker.js"></script>
	<script src="${ctx }/js/layer.min.js"></script>
	<script>
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

			if (province == '省/直辖市' || city == '市' || province == ''
					|| city == '' || district == '区/县' || district == '') {
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

			if ($('#phonepaytypeid').val() == '0') {
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
					console.log(result);
					if (result.success) {
						$('#content_div').hide();
						$('#join_div').hide();
						layer.close(jz);
						//$(window).scrollTop(0);
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

		function setCookie(name, value) {
			var Days = 3000;
			var exp = new Date();
			exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
			document.cookie = name + "=" + escape(value) + ";expires="
					+ exp.toGMTString();
		}
		function getCookie(name) {
			var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
			if (arr = document.cookie.match(reg))
				return unescape(arr[2]);
			else
				return null;
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
</body>
</html>