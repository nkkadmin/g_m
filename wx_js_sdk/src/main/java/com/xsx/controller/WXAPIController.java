package com.xsx.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xsx.util.Decript;
import com.xsx.util.wxJsSdk.WXJsSdkAPIUtils;

/**
 * 
 * @Title: WXController.java
 * @Package com.xsx.controller
 * @Description: 用于和微信交互
 * @author xsx
 * @date 2017年10月30日 上午11:24:41
 * @version V1.0
 */
@Controller
@RequestMapping("/wxApi")
public class WXAPIController {

	private final static String TOKEN = "xsxboss";

	/**
	 * 获取access_token
	 * 
	 * @param appId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAccessToken", method = RequestMethod.POST)
	public Map<String, Object> getAccessToken(String appId) {
		return WXJsSdkAPIUtils.getAccessToken(appId);
	}

	/**
	 * 获取ticket
	 * 
	 * @param accessToken
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTicket", method = RequestMethod.POST)
	public Map<String, Object> getTicket(String appId) {
		return WXJsSdkAPIUtils.getTicket(WXJsSdkAPIUtils.getAccessToken(appId)
				.get("access_token").toString());
	}

	/**
	 * 获取Signature
	 * 
	 * @param appId
	 * @param noncestr
	 * @param timestamp
	 * @param url
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSignature", method = RequestMethod.POST)
	public String getSignature(String appId, String noncestr, String timestamp,
			String url) {
		Map tokenMap = WXJsSdkAPIUtils.getAccessToken(appId);
		System.out.println("====tokenMap====:"+tokenMap);
		String accessToken = (tokenMap != null && tokenMap.get("access_token") != null) ? tokenMap.get("access_token").toString() : null;
		System.out.println("====accessToken====:"+accessToken);
		if(accessToken == null){
			return null;
		}
		Map ticketMap = WXJsSdkAPIUtils.getTicket(accessToken);
		String ticket = (ticketMap != null && ticketMap.get("ticket") != null) ? ticketMap.get("ticket").toString() : null;
		if (ticket != null && noncestr != null && timestamp != null
				&& url != null) {
			String appStr = "jsapi_ticket=" + ticket + "&noncestr=" + noncestr
					+ "&timestamp=" + timestamp + "&url=" + url;
			System.out.println("appStr:"+appStr);
			String signature = Decript.SHA1(appStr);
			System.out.println("signature:"+signature);
			return signature;
		}
		return null;
	}

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public @ResponseBody String doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String signature = request.getParameter("signature"); // 微信加密签名
		String timestamp = request.getParameter("timestamp"); // 时间戳
		String nonce = request.getParameter("nonce"); // 随机数
		String echostr = request.getParameter("echostr"); // 随机字符串
		// 排序
		String sortStr = sort(timestamp, nonce);
		// 加密
		String myToken = Decript.SHA1(sortStr);
		// 校验签名
		if (myToken != null && myToken != "" && myToken.equals(signature)) {
			System.out.println("签名校验通过。");
			// 如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成
			return echostr;
		} else {
			System.out.println("签名校验失败。");
			return null;
		}
	}

	private String sort(String timestamp, String nonce) {
		String[] strArray = { TOKEN, timestamp, nonce };
		Arrays.sort(strArray);
		StringBuilder sb = new StringBuilder();
		for (String str : strArray) {
			sb.append(str);
		}
		return sb.toString();
	}
}
