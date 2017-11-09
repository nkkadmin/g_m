package com.xsx.service;

import java.util.List;

import com.xsx.domain.WxInfo;

public interface WxInfoService {

	
	int insertSelective(WxInfo record);
	
	WxInfo selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(WxInfo record);
	
	List<WxInfo> selectAll();
	
	int deleteAll();
}
