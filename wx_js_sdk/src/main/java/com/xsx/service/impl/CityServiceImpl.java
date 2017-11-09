package com.xsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xsx.mapper.CityMapper;
import com.xsx.mapper.ProvinceMapper;
import com.xsx.service.CityService;


@Service("cityService")
public class CityServiceImpl implements CityService {

	
	@Resource
	private CityMapper cityMapper;
	
	@Resource 
	private ProvinceMapper provinceMapper;
}
