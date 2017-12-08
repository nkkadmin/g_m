package com.xsx.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xsx.domain.AjaxJson;
import com.xsx.domain.LogInfo;
import com.xsx.service.LogInfoService;
import com.xsx.util.DateHelper;

/**
 * 
 * @Title: LogInfoController.java 
 * @Package com.xsx.controller 
 * @Description: 日志记录
 * @author xsx
 * @date 2017年11月28日 下午9:09:40 
 * @version V1.0
 */
@Controller
@RequestMapping("/log")
public class LogInfoController extends BaseController {
	
	@Resource
	private LogInfoService logInfoService;

	@RequestMapping(value="/addLog",method=RequestMethod.POST)
	public AjaxJson addLog(HttpServletRequest request){
		AjaxJson json = new AjaxJson();
		try {
			LogInfo logInfo = new LogInfo();
			logInfo.setCreatedate(DateHelper.nowDate());
			logInfo.setErrormessage("举报");
			logInfo.setOperurl(request.getServerName());
			logInfo.setOperuser("");
			logInfoService.insertSelective(logInfo);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
}
