package com.xsx.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xsx.domain.WxInfo;
import com.xsx.service.WxInfoService;
import com.xsx.util.Decript;
import com.xsx.util.StringHelper;
import com.xsx.util.wxJsSdk.WXJsSdkAPIUtils;


@Controller
@RequestMapping("/shop")
public class ShopController {
	
	@Resource
	private WxInfoService wxInfoService;

	@RequestMapping(value="/info")
	public ModelAndView info(Integer empId,String code,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("shopInfo");
		mv.addObject("empId", empId);
		mv.addObject("code", code);
		String jurl = request.getScheme()+"://"+request.getServerName() + request.getContextPath() + "/shop/shopSub?empId="+empId+"&&code="+code;
		mv.addObject("jurl", jurl);
		//时间戳
		String timestamp = String.valueOf(new Date().getTime()).substring(0, 10);
		//生成签名的随机串
		String nonceStr = UUID.randomUUID().toString().replace("-", "").substring(0, 15);
		String url = request.getRequestURL().toString();
		if(request.getQueryString() != null){
			url = url + "?" + request.getQueryString();
		}
		//签名
		String signature = null;
		String accessToken = getAccessToken();
		System.out.println("====accessToken====:"+accessToken);
		if(accessToken == null){
			return null;
		}
		
		String ticket = getTicket(accessToken);
		if (ticket != null && nonceStr != null && timestamp != null
				&& url != null) {
			String appStr = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr
					+ "&timestamp=" + timestamp + "&url=" + url;
			System.out.println(appStr);
			signature = Decript.SHA1(appStr);
			System.out.println("signature:"+signature);
		}
		mv.addObject("appid", WXJsSdkAPIUtils.APPID);
		mv.addObject("timestamp", timestamp);
		mv.addObject("nonceStr", nonceStr);
		mv.addObject("signature", signature);
		return mv;
	}
	
	/**
	 * 获取微信ticket
	 * @param accessToken
	 * @return
	 */
	private String getTicket(String accessToken){
		List<WxInfo> list = getWxInfoList();
		String ticket = null;
		if(list == null){
			return null;
		}else if(!StringHelper.isEmpty(list.get(0).getJsapiticket())){
			ticket = list.get(0).getJsapiticket();
		}else{
			Map<String, Object> ticketMap = WXJsSdkAPIUtils.getTicket(accessToken);
			ticket = (ticketMap != null && ticketMap.get("ticket") != null) ? ticketMap.get("ticket").toString() : null;
			WxInfo info = list.get(0);
			info.setJsapiticket(ticket);
			wxInfoService.updateByPrimaryKeySelective(info);
		}
		return ticket;
	}
	
	/**
	 * 获取accessToken
	 * @return
	 */
	private String getAccessToken(){
		List<WxInfo> list = getWxInfoList();
		String accessToken = null;
		if(list == null || (list != null &&  StringHelper.isEmpty(list.get(0).getAccesstoken()))){ //重新调接口获取
			Map<String, Object> tokenMap = WXJsSdkAPIUtils.getAccessToken();
			System.out.println("tokenMap:"+tokenMap);
			accessToken = (tokenMap != null && tokenMap.get("access_token") != null) ? tokenMap.get("access_token").toString() : null;
			//如果表中有数据，但AccessToken为空，则需要先清空表
			wxInfoService.deleteAll();
			//添加新数据
			WxInfo wxInfo = new WxInfo();
			wxInfo.setAccesstoken(accessToken);
			wxInfoService.insertSelective(wxInfo);
		}else{//直接重库里取
			accessToken = list.get(0).getAccesstoken();
		}
		return accessToken;
	}
	
	
	private List<WxInfo> getWxInfoList(){
		List<WxInfo> list = wxInfoService.selectAll();
		if(list == null || list.size() == 0){
			return null;
		}
		return list;
	}
	
	@RequestMapping(value="/shopSub")
	public ModelAndView shopSub(Integer empId,String code,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("shopSub");
		mv.addObject("empId", empId);
		mv.addObject("code", code);
		return mv;
	}
}
