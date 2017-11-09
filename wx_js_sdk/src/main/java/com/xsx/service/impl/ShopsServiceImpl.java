package com.xsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xsx.mapper.ShopsMapper;
import com.xsx.service.ShopsService;

@Service("shopsService")
public class ShopsServiceImpl implements ShopsService {

	@Resource
	private ShopsMapper shopsMapper;

}
