package com.xsx.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xsx.domain.Employee;
import com.xsx.domain.EmployeeExtension;
import com.xsx.domain.Ips;
import com.xsx.domain.WxInfo;
import com.xsx.service.WxInfoService;
import com.xsx.util.Decript;
import com.xsx.util.StringHelper;
import com.xsx.util.wxJsSdk.WXJsSdkAPI;

/**
 * 
 * @Title: ShopController.java 
 * @Package com.xsx.controller 
 * @Description: 商品详情页，也就是分享页面的Controller
 * @author xsx
 * @date 2017年11月24日 下午9:52:47 
 * @version V1.0
 */
@Controller
@RequestMapping("/shop")
public class ShopController extends BaseController {

	@Resource
	private WxInfoService wxInfoService;

	/**
	 * 商品详情页，分享页面
	 * @param empId
	 * @param code
	 * @param type: f:免接口  t:需要接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/info/{empId}/{code}/{time}/{type}")
	public ModelAndView info(@PathVariable("empId") Integer empId,@PathVariable("code") String code,
			@PathVariable("type") String type, HttpServletRequest request) {
		//判断这个员工是否被删除
		Employee employee = employeeService.selectByPrimaryKey(empId);
		if(employee == null || (employee != null && employee.getStatu() == 0)){
			return null;
		}
		
		String dmUrl = getEffectivDomainName();
		String domainUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		//String redirectUrl = "http://localhost:8090/wx_js_sdk/shop/"+(type.equals("t") ? "infoTrue" : "infoImpFalse")+"/"+empId+ "/"+code+"/"+new Date().getTime();
		String redirectUrl = "http://"+dmUrl+"/shop/"+(type.equals("t") ? "infoTrue" : "infoImpFalse")+"/"+empId+ "/"+code+"/"+new Date().getTime();
		return new ModelAndView("redirect:"+redirectUrl);
	}
	
	/**
	 * 免接口分享頁面
	 * @param empId
	 * @param code
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/infoImpFalse/{empId}/{code}/{time}")
	public ModelAndView infoImpFalse(@PathVariable("empId") Integer empId,@PathVariable("code") String code,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("imlfalse");
		//分享成功后，回调跳转页面路径
		String domainUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		String jurl = domainUrl + "/shop/shopSub/" + empId +"/" + code+"/"+new Date().getTime();
		mv.addObject("jurl", jurl);
		return mv;
	}
	
	
	/**
	 * 接口分享
	 * @param empId
	 * @param code
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/infoTrue/{empId}/{code}/{time}")
	public ModelAndView infoTrue(@PathVariable("empId") Integer empId,@PathVariable("empId") String code,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shopInfo");
		mv.addObject("empId", empId);
		mv.addObject("code", code);
		//分享成功后，回调跳转页面路径
		String domainUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		String jurl = domainUrl + "/shop/shopSub/" + empId +"/" + code+"/"+new Date().getTime();
		mv.addObject("jurl", jurl);
		// 时间戳
		String timestamp = String.valueOf(new Date().getTime())
				.substring(0, 10);
		// 生成签名的随机串
		String nonceStr = UUID.randomUUID().toString().replace("-", "")
				.substring(0, 15);
		String url = request.getRequestURL().toString();
		if (request.getQueryString() != null) {
			url = url + "?" + request.getQueryString();
		}
		
		// 签名
		String signature = null;
		String ticket = getTicket();
		if (ticket != null && nonceStr != null && timestamp != null
				&& url != null) {
			String appStr = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr
					+ "&timestamp=" + timestamp + "&url=" + url;
			System.out.println(appStr);
			signature = Decript.SHA1(appStr);
			System.out.println("signature:" + signature);
		}
		mv.addObject("appid", WXJsSdkAPI.APPID);
		mv.addObject("timestamp", timestamp);
		mv.addObject("nonceStr", nonceStr);
		mv.addObject("signature", signature);
		return mv;
	}

	/**
	 * 获取有效域名
	 * @return
	 */
	private String getEffectivDomainName() {
		//得到全部域名
		Ips ip = ipsService.seleceIpOne();
		return ip != null ? ip.getName() : null;
	}

	/**
	 * 三十分钟后情况微信tokensession，用于更新token
	 */
	private synchronized void threadTime() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 1;
				while (i <= 3600) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i++;
				}
				wxInfoService.deleteAll();
			}
		}).start();
	}

	/**
	 * 获取微信ticket
	 * 
	 * @param accessToken
	 * @return
	 */
	private String getTicket() {
		String ticket = null;
		try {
			List<WxInfo> list = getWxInfoList();
			System.out.println("in db value is : " + list);
			if (list == null
					|| (list != null && StringHelper.isEmpty(list.get(0)
							.getAccesstoken()))
					|| (list != null && StringHelper.isEmpty(list.get(0)
							.getJsapiticket()))) {
				wxInfoService.deleteAll();
				WXJsSdkAPI api = new WXJsSdkAPI();
				// 发送http请求获取
				Map<String, Object> tokenMap = api.getAccessToken();
				System.out.println(">>>>>>通过http请求获取Access：<<<<<<<" + tokenMap);
				if (tokenMap != null && tokenMap.get("access_token") != null) {
					String token = tokenMap.get("access_token").toString();
					Map<String, Object> ticketMap = api.getTicketValue(token);
					System.out.println(">>>>>>通过http请求获取ticketMap：<<<<<<<"
							+ ticketMap);
					if (ticketMap != null && ticketMap.get("ticket") != null) {
						ticket = (String) ticketMap.get("ticket");
						WxInfo wxInfo = new WxInfo();
						wxInfo.setAccesstoken(token);
						wxInfo.setJsapiticket(ticket);
						wxInfoService.insertSelective(wxInfo);
						threadTime();
					} else {
						System.out
								.println(">>>>>>通过http请求获取ticketMap失败<<<<<<<");
					}
				} else {
					System.out.println(">>>>>>>>>通过http请求获取ticketMap失败<<<<<");
				}
			} else if (list != null
					&& !StringHelper.isEmpty(list.get(0).getJsapiticket())) {
				WxInfo wxInfo = (WxInfo) list.get(0);
				ticket = wxInfo.getJsapiticket();
			}
			return ticket;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}

	private List<WxInfo> getWxInfoList() {
		List<WxInfo> list = wxInfoService.selectAll();
		if (list == null || list.size() == 0) {
			return null;
		}
		return list;
	}
	
	/**
	 * 分享成功后，用户提交表单页面
	 * @param empId
	 * @param code
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/shopSub/{empId}/{code}/{time}")
	public ModelAndView shopSub(@PathVariable("empId") Integer empId,@PathVariable("code") String code,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shopSub");
		mv.addObject("empId", empId);
		mv.addObject("code", code);
		return mv;
	}
	
	@RequestMapping(value = "/report")
	public ModelAndView report() {
		ModelAndView mv = new ModelAndView("report");
		return mv;
	}
	@RequestMapping(value = "/result")
	public ModelAndView result() {
		ModelAndView mv = new ModelAndView("result");
		return mv;
	}
	
	/**
	 * 跳转到  action --> extension
	 * @param empId
	 * @return
	 */
	@RequestMapping(value="/extensionJump/{empId}/{time}")
	public ModelAndView extensionJump(@PathVariable("empId") Integer empId,
			HttpServletRequest request){
		String dmUrl = getEffectivDomainName();
		String redirectUrl = "http://"+dmUrl+"/shop/extension/" + empId + "/"+new Date().getTime();
		return new ModelAndView("redirect:"+redirectUrl);
	}
	
	/**
	 * 员工微信内容
	 * @return
	 */
	@RequestMapping(value = "/extension/{empId}/{time}")
	public ModelAndView extension(@PathVariable("empId") Integer empId) {
		ModelAndView mv = new ModelAndView("extension");
		EmployeeExtension employeeExtension = employeeExtensionService.selectByEmployeeId(empId);
		mv.addObject("info", employeeExtension);
		return mv;
	}
 
}
