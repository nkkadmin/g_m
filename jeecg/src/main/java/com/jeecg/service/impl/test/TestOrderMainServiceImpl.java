package com.jeecg.service.impl.test;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.jeecg.service.test.TestOrderMainServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.jeecg.entity.test.TestOrderMainEntity;
import com.jeecg.entity.test.TestOrderCustomEntity;
import com.jeecg.entity.test.TestOrderTicketEntity;
@Service("testOrderMainService")
@Transactional
public class TestOrderMainServiceImpl extends CommonServiceImpl implements TestOrderMainServiceI {

	
	public void addMain(TestOrderMainEntity testOrderMain,
	        List<TestOrderCustomEntity> testOrderCustomList,List<TestOrderTicketEntity> testOrderTicketList){
			//保存主信息
			this.save(testOrderMain);
		
			/**保存-客户明细*/
			for(TestOrderCustomEntity testOrderCustom:testOrderCustomList){
				//外键设置
				testOrderCustom.setFkId(testOrderMain.getId());
				this.save(testOrderCustom);
			}
			/**保存-产品明细*/
			for(TestOrderTicketEntity testOrderTicket:testOrderTicketList){
				//外键设置
				testOrderTicket.setFckId(testOrderMain.getId());
				this.save(testOrderTicket);
			}
	}

	
	public void updateMain(TestOrderMainEntity testOrderMain,
	        List<TestOrderCustomEntity> testOrderCustomList,List<TestOrderTicketEntity> testOrderTicketList) {
		//保存订单主信息
		this.saveOrUpdate(testOrderMain);
		
		
		//===================================================================================
		//获取参数
		Object id0 = testOrderMain.getId();
		Object id1 = testOrderMain.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-客户明细
	    String hql0 = "from TestOrderCustomEntity where 1 = 1 AND fkId = ? ";
	    List<TestOrderCustomEntity> testOrderCustomOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-客户明细
		for(TestOrderCustomEntity oldE:testOrderCustomOldList){
			boolean isUpdate = false;
				for(TestOrderCustomEntity sendE:testOrderCustomList){
					//需要更新的明细数据-客户明细
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-客户明细
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-客户明细
		for(TestOrderCustomEntity testOrderCustom:testOrderCustomList){
			if(testOrderCustom.getId()==null){
				//外键设置
				testOrderCustom.setFkId(testOrderMain.getId());
				this.save(testOrderCustom);
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-产品明细
	    String hql1 = "from TestOrderTicketEntity where 1 = 1 AND fckId = ? ";
	    List<TestOrderTicketEntity> testOrderTicketOldList = this.findHql(hql1,id1);
		//2.筛选更新明细数据-产品明细
		for(TestOrderTicketEntity oldE:testOrderTicketOldList){
			boolean isUpdate = false;
				for(TestOrderTicketEntity sendE:testOrderTicketList){
					//需要更新的明细数据-产品明细
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-产品明细
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-产品明细
		for(TestOrderTicketEntity testOrderTicket:testOrderTicketList){
			if(testOrderTicket.getId()==null){
				//外键设置
				testOrderTicket.setFckId(testOrderMain.getId());
				this.save(testOrderTicket);
			}
		}
		
	}

	
	public void delMain(TestOrderMainEntity testOrderMain) {
		//删除主表信息
		this.delete(testOrderMain);
		
		//===================================================================================
		//获取参数
		Object id0 = testOrderMain.getId();
		Object id1 = testOrderMain.getId();
		//===================================================================================
		//删除-客户明细
	    String hql0 = "from TestOrderCustomEntity where 1 = 1 AND fkId = ? ";
	    List<TestOrderCustomEntity> testOrderCustomOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(testOrderCustomOldList);
		//===================================================================================
		//删除-产品明细
	    String hql1 = "from TestOrderTicketEntity where 1 = 1 AND fckId = ? ";
	    List<TestOrderTicketEntity> testOrderTicketOldList = this.findHql(hql1,id1);
		this.deleteAllEntitie(testOrderTicketOldList);
	}
	
}