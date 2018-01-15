package com.xsx.util.send;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


/**
 * 
 * @Title: AIliSendCodeUtil.java 
 * @Package com.xsx.util.send 
 * @Description: 阿里云短信接口
 * @author xsx
 * @date 2018年1月12日 下午1:16:08 
 * @version V1.0
 */
public class AIiSendCodeUtil {

	private AIiSendCodeUtil(){}
	
	private final static String ACCESSKEYID = "LTAIHoztkT2NIjmx";
	private final static String ACCESSKEYSECRET = "JnHoXIy9MlvhuIgh0uUYM9GDevADVa";
	
	
	public static void main(String[] args) {
		boolean flag = sendCode("15821292493");
		System.out.println(flag);
	}
	
	public static boolean sendCode(String phoneNumber){
		return sendCode(phoneNumber, "SMS_121910540", null);
	}
	
	/**
     * 发送短信
     * @param number  发送内容
     * @param templateCode  模板
     * @param phoneNumber   接收短信的号码
     * @author xsx
     */
    public static boolean sendCode(String phoneNumber,String templateCode,Map<String,String> params){
        try{
            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            //初始化ascClient需要的几个参数
            final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
            final String accessKeyId = ACCESSKEYID;//你的accessKeyId,参考本文档步骤2
            final String accessKeySecret = ACCESSKEYSECRET;//你的accessKeySecret，参考本文档步骤2
            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            request.setPhoneNumbers(phoneNumber);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("金凤呈祥");
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            String jsonParam = (String) JSON.toJSON(params);
            request.setTemplateParam(jsonParam);
            //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            // request.setOutId("yourOutId");
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                return true;
            }else{
            	String codeInfo = getErrorInfo(sendSmsResponse.getCode());
            	System.out.println("codeInfo："+codeInfo);
            }
        } catch (ClientException e){
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 解析code意思
     * @param aliCode
     * @return
     */
    private static String getErrorInfo(String aliCode){
    	String resurnStr = "";
    	switch (aliCode) {
		case "isp.RAM_PERMISSION_DENY":
			resurnStr = "RAM权限DENY";
			break;
		case "isv.OUT_OF_SERVICE":
			resurnStr = "业务停机";
			break;
		case "isv.PRODUCT_UN_SUBSCRIPT":
			resurnStr = "未开通云通信产品的阿里云客户";
			break;
		case "isv.PRODUCT_UNSUBSCRIBE":
			resurnStr = "产品未开通";
			break;
		case "isv.ACCOUNT_NOT_EXISTS":
			resurnStr = "账户不存在";
			break;
		case "isv.SMS_TEMPLATE_ILLEGAL":
			resurnStr = "短信模板不合法";
			break;
		case "isv.SMS_SIGNATURE_ILLEGAL":
			resurnStr = "短信签名不合法";
			break;
		case "isv.INVALID_PARAMETERS":
			resurnStr = "参数异常";
			break;
		case "isv.SYSTEM_ERROR":
			resurnStr = "系统错误";
			break;
		case "isv.MOBILE_NUMBER_ILLEGAL":
			resurnStr = "非法手机号";
			break;
		case "isv.MOBILE_COUNT_OVER_LIMIT":
			resurnStr = "手机号码数量超过限制";
			break;
		case "isv.TEMPLATE_MISSING_PARAMETERS":
			resurnStr = "模板缺少变量";
			break;
		case "isv.BUSINESS_LIMIT_CONTROL":
			resurnStr = "业务限流";
			break;
		case "isv.INVALID_JSON_PARAM":
			resurnStr = "JSON参数不合法，只接受字符串值";
			break;
		case "isv.BLACK_KEY_CONTROL_LIMIT":
			resurnStr = "黑名单管控";
			break;
		case "isv.PARAM_LENGTH_LIMIT":
			resurnStr = "参数超出长度限制";
			break;
		case "isv.PARAM_NOT_SUPPORT_URL":
			resurnStr = "不支持URL";
			break;
		case "isv.AMOUNT_NOT_ENOUGH":
			resurnStr = "账户余额不足";
			break;
		default:
			resurnStr = "发送成功";
			break;
		}
    	return resurnStr;
    }
    
}
