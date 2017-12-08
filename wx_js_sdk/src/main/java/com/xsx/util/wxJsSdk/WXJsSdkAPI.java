package com.xsx.util.wxJsSdk;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import com.xsx.util.HttpSendUtils;
import com.xsx.util.JsonUtils;

/**
 * 
 * @Title: WXJsSdkAPIUtils.java
 * @Package com.xsx.util.wxJsSdk
 * @Description: 微信JS-SDK api
 * @author xsx
 * @date 2017年10月30日 上午10:16:33
 * @version V1.0
 */
public class WXJsSdkAPI {

	private final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";
	private final static String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=";

	public final static String APPID = "wx124bf3aa94ea98b8";
	private final static String APPSECRET = "afb70760743b30a7706530336ba4dd29";


	public Map<String, Object> getAccessToken() {
		return getAccessToken(APPID, APPSECRET);
	}

	public Map<String, Object> getAccessToken(String appId) {
		return getAccessToken(appId, APPSECRET);
	}

	public Map<String, Object> getAccessToken(String appId,
			String appSecret) {
		String url = ACCESS_TOKEN_URL + appId + "&secret="+appSecret;
		Map<String, Object> map = null;
		try {
			String content = HttpSendUtils.sendGet(url);
			map = JsonUtils.jsonToMap(content);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public Map<String, Object> getTicketValue(String accessToken) {
		Map<String, Object> map = null;
		try {
			String url = JSAPI_TICKET + accessToken + "&type=jsapi";
			String content = HttpSendUtils.sendGet(url);
			map = JsonUtils.jsonToMap(content);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
