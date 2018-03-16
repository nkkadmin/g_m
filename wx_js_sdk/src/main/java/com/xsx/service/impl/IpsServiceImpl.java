package com.xsx.service.impl;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xsx.constant.Constants;
import com.xsx.domain.Ips;
import com.xsx.domain.Page;
import com.xsx.mapper.IpsMapper;
import com.xsx.service.IpsService;


@Service("ipsService")
public class IpsServiceImpl implements IpsService {

	
	@Resource
	private IpsMapper ipsMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		if(id != null){
			Ips ips = ipsMapper.selectByPrimaryKey(id);
			ips.setStatu(Constants.STATU_DELETE);
			return ipsMapper.updateByPrimaryKey(ips);
		}
		return 0;
	}

	@Override
	public int insertSelective(Ips record) {
		return ipsMapper.insertSelective(record);
	}

	@Override
	public Ips selectByPrimaryKey(Integer id) {
		return ipsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Ips record) {
		return ipsMapper.updateByPrimaryKey(record);
	}

	@Override
	public Page<Ips> selectAllOrders(Ips ips, Page<Ips> page) {
		List<Ips> list = ipsMapper.selectAllOrders(ips, page);
		page.setRows(list);
		return page;
	}

	@Override
	public Ips seleceIpOne() {
		List<Ips> list = ipsMapper.selectAllOrder();
		if(list != null && list.size() > 0){
			return list.get(new Random().nextInt(list.size()));
		} 
		return null;
	}
	
}
