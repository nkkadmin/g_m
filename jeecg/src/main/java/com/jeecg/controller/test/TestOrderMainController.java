package com.jeecg.controller.test;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import com.jeecg.entity.test.TestOrderMainEntity;
import com.jeecg.page.test.TestOrderMainPage;
import com.jeecg.service.test.TestOrderMainServiceI;
import com.jeecg.entity.test.TestOrderCustomEntity;
import com.jeecg.entity.test.TestOrderTicketEntity;
/**   
 * @Title: Controller
 * @Description: 订单
 * @author zhangdaihao
 * @date 2018-04-27 16:20:03
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/testOrderMainController")
public class TestOrderMainController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestOrderMainController.class);

	@Autowired
	private TestOrderMainServiceI testOrderMainService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	
	/**
	 * 订单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/test/testOrderMainList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TestOrderMainEntity testOrderMain,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TestOrderMainEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, testOrderMain);
		this.testOrderMainService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除订单
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TestOrderMainEntity testOrderMain, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		testOrderMain = systemService.getEntity(TestOrderMainEntity.class, testOrderMain.getId());
		message = "删除成功";
		testOrderMainService.delete(testOrderMain);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加订单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TestOrderMainEntity testOrderMain,TestOrderMainPage testOrderMainPage, HttpServletRequest request) {
		String message = null;
		List<TestOrderCustomEntity> testOrderCustomList =  testOrderMainPage.getTestOrderCustomList();
		List<TestOrderTicketEntity> testOrderTicketList =  testOrderMainPage.getTestOrderTicketList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(testOrderMain.getId())) {
			message = "更新成功";
			testOrderMainService.updateMain(testOrderMain, testOrderCustomList,testOrderTicketList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			testOrderMainService.addMain(testOrderMain, testOrderCustomList,testOrderTicketList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 订单列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TestOrderMainEntity testOrderMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(testOrderMain.getId())) {
			testOrderMain = testOrderMainService.getEntity(TestOrderMainEntity.class, testOrderMain.getId());
			req.setAttribute("testOrderMainPage", testOrderMain);
		}
		return new ModelAndView("com/jeecg/test/testOrderMain");
	}
	
	
	/**
	 * 加载明细列表[客户明细]
	 * 
	 * @return
	 */
	@RequestMapping(params = "testOrderCustomList")
	public ModelAndView testOrderCustomList(TestOrderMainEntity testOrderMain, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = testOrderMain.getId();
		//===================================================================================
		//查询-客户明细
	    String hql0 = "from TestOrderCustomEntity where 1 = 1 AND fkId = ? ";
		try{
		    List<TestOrderCustomEntity> testOrderCustomEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("testOrderCustomList", testOrderCustomEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/test/testOrderCustomList");
	}
	/**
	 * 加载明细列表[产品明细]
	 * 
	 * @return
	 */
	@RequestMapping(params = "testOrderTicketList")
	public ModelAndView testOrderTicketList(TestOrderMainEntity testOrderMain, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id1 = testOrderMain.getId();
		//===================================================================================
		//查询-产品明细
	    String hql1 = "from TestOrderTicketEntity where 1 = 1 AND fckId = ? ";
		try{
		    List<TestOrderTicketEntity> testOrderTicketEntityList = systemService.findHql(hql1,id1);
			req.setAttribute("testOrderTicketList", testOrderTicketEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/test/testOrderTicketList");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TestOrderMainEntity> list() {
		List<TestOrderMainEntity> listTestOrderMains=testOrderMainService.getList(TestOrderMainEntity.class);
		return listTestOrderMains;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		TestOrderMainEntity task = testOrderMainService.get(TestOrderMainEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TestOrderMainEntity testOrderMain, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TestOrderMainEntity>> failures = validator.validate(testOrderMain);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		testOrderMainService.save(testOrderMain);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = testOrderMain.getId();
		URI uri = uriBuilder.path("/rest/testOrderMainController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TestOrderMainEntity testOrderMain) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TestOrderMainEntity>> failures = validator.validate(testOrderMain);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		testOrderMainService.saveOrUpdate(testOrderMain);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		testOrderMainService.deleteEntityById(TestOrderMainEntity.class, id);
	}
}
