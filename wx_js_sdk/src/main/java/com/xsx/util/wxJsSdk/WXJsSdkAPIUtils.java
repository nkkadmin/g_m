package com.xsx.util.wxJsSdk;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.xsx.util.HttpAttributeUtils;
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
public class WXJsSdkAPIUtils {

	private static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	/*
	 * private final static String APPID = "wx287ec711f3bdb1c6"; private final
	 * static String APPSECRET = "5d05cbfc43255efc9767334bf43586d4";
	 */

	public final static String APPID = "wx04f506164521fcc5";
	private final static String APPSECRET = "6ec21165ee6252db10c5252bc258ba75";

	public static Map<String, Object> getAccessToken() {
		return getAccessToken(APPID, APPSECRET);
	}

	public static Map<String, Object> getAccessToken(String appId) {
		return getAccessToken(appId, APPSECRET);
	}

	public static Map<String, Object> getAccessToken(String appId,
			String appSecret) {
		ACCESS_TOKEN_URL = ACCESS_TOKEN_URL.replace("APPID", appId);
		ACCESS_TOKEN_URL = ACCESS_TOKEN_URL.replace("APPSECRET", appSecret);
		Map<String, Object> map = null;
		try {
			String content = HttpSendUtils.sendGet(ACCESS_TOKEN_URL);
			map = JsonUtils.jsonToMap(content);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static Map<String, Object> getTicket(String accessToken) {
		Map<String, Object> map = null;
		try {
			JSAPI_TICKET = JSAPI_TICKET.replace("ACCESS_TOKEN", accessToken);
			String content = HttpSendUtils.sendGet(JSAPI_TICKET);
			map = JsonUtils.jsonToMap(content);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
