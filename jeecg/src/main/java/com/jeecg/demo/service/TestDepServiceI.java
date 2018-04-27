package com.jeecg.demo.service;
import com.jeecg.demo.entity.TestDepEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TestDepServiceI extends CommonService{
	
 	public void delete(TestDepEntity entity) throws Exception;
 	
 	public Serializable save(TestDepEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TestDepEntity entity) throws Exception;
 	
}
