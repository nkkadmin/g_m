package com.xsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xsx.domain.LogInfo;
import com.xsx.mapper.LogInfoMapper;
import com.xsx.service.LogInfoService;


@Service("logInfoService")
public class LogInfoServiceImpl implements LogInfoService {

	@Resource
	private LogInfoMapper logInfoMapper;
	
	@Override
	public int insertSelective(LogInfo record) {
		return logInfoMapper.insertSelective(record);
	}

}
