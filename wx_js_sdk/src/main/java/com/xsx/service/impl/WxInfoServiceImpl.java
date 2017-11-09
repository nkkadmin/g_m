package com.xsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xsx.domain.WxInfo;
import com.xsx.mapper.WxInfoMapper;
import com.xsx.service.WxInfoService;


/**
 * 
 * @Title: WxInfoServiceImpl.java 
 * @Package com.xsx.service.impl 
 * @Description: 微信公众号相关配置
 * @author xsx
 * @date 2017年11月9日 上午10:40:08 
 * @version V1.0
 */
@Service("wxInfoService")
public class WxInfoServiceImpl implements WxInfoService {
	
	@Resource
	private WxInfoMapper wxInfoMapper;

	@Override
	public int insertSelective(WxInfo record) {
		//保证表内只有一条记录
		List<WxInfo> list = wxInfoMapper.selectAll();
		if(list == null || list.size() == 0){
			return wxInfoMapper.insertSelective(record);
		}
		return 0;
	}

	@Override
	public WxInfo selectByPrimaryKey(Integer id) {
		return wxInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WxInfo record) {
		return wxInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<WxInfo> selectAll() {
		return wxInfoMapper.selectAll();
	}

	@Override
	public int deleteAll() {
		return wxInfoMapper.deleteAll();
	}

}
