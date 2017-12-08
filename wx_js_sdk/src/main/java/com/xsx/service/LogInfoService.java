package com.xsx.service;

import com.xsx.domain.LogInfo;

/**
 * 
 * @Title: LogInfoService.java 
 * @Package com.xsx.service 
 * @Description: 日志记录
 * @author xsx
 * @date 2017年11月28日 下午9:07:14 
 * @version V1.0
 */
public interface LogInfoService {

	int insertSelective(LogInfo record);
}
