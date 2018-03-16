package com.xsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xsx.mapper.PhonePayTypeMapper;
import com.xsx.service.PhonePayTypeService;


@Service("phonePayTypeService")
public class PhonePayTypeServiceImpl implements PhonePayTypeService {

	
	@Resource
	private PhonePayTypeMapper phonePayTypeMapper;
	
}
