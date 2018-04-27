package com.jeecg.service.test;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.jeecg.entity.test.TestOrderMainEntity;
import com.jeecg.entity.test.TestOrderCustomEntity;
import com.jeecg.entity.test.TestOrderTicketEntity;

public interface TestOrderMainServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(TestOrderMainEntity testOrderMain,
	        List<TestOrderCustomEntity> testOrderCustomList,List<TestOrderTicketEntity> testOrderTicketList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TestOrderMainEntity testOrderMain,
	        List<TestOrderCustomEntity> testOrderCustomList,List<TestOrderTicketEntity> testOrderTicketList);
	public void delMain (TestOrderMainEntity testOrderMain);
}
