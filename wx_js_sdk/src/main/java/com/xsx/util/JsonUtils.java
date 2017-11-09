package com.xsx.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @Title: JsonUtils.java
 * @Package com.xsx.util
 * @Description:处理JSON数据
 * @author xsx
 * @date 2017年10月30日 下午12:40:10
 * @version V1.0
 */
public class JsonUtils {

	private JsonUtils() {
	}

	public static Map<String, Object> jsonToMap(String json) {
		if (json == null || json.equals(""))
			return null;
		return (Map) JSON.parse(json);
	}
}
