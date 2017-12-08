package com.xsx.service;

import com.xsx.domain.Ips;
import com.xsx.domain.Page;

public interface IpsService {

	
	int deleteByPrimaryKey(Integer id);


    int insertSelective(Ips record);

    Ips selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Ips record);
    
	public Page<Ips> selectAllOrders(Ips ips, Page<Ips> page);
	
	/**
	 * 获取一个有效的ip
	 * @return
	 */
	public Ips seleceIpOne();
}
