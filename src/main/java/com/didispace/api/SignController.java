package com.didispace.api;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.didispace.util.ApiUasUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@Api(value = "签到接口")
@RequestMapping(value = "/sign")
public class SignController {
	
	/**
	 * Session对象的两种取法
	 * @param mPhone
	 * @param mPassword
	 * @param date
	 * @param request
	 * @param httpSession
	 * @return
	 */
	@Deprecated
	@ApiOperation(value = "Session", notes = "")
	@RequestMapping(method = RequestMethod.GET, value = "/api/{mPhone}/{mPassword}")
	public @ResponseBody String getApiServletInfo(
	        @ApiParam(value = "手机号", required = true, defaultValue="13268690268")
	        @PathVariable String mPhone,	     
	        @PathVariable String mPassword,
	        @ApiParam(value = "日期", required = true, defaultValue="2017-07-04")
	        @PathVariable String date, 
	        HttpServletRequest request,
	        @ApiParam(value = "Session", hidden=true)
	        HttpSession httpSession) {
		   System.out.println(request.getSession().equals(httpSession));
				return mPassword;
	    // ...
	 }
	
	@Deprecated
	@ApiOperation(value = "HttpSession", notes = "")
	@RequestMapping(method = RequestMethod.GET, value = "/api/{mPhone}/{mPassword}/{date}")
	public @ResponseBody String getApiInfo(
	        @ApiParam(value = "手机号", required = true, defaultValue="13268690268")
	        @PathVariable String mPhone,
	        @ApiParam(value = "密码", required = true,defaultValue="111111")
	        @PathVariable String mPassword,
	        @ApiParam(value = "日期", required = true, defaultValue="2017-07-04")
	        @PathVariable String date, 
	        @ApiParam(value = "Session", hidden=true)
	        HttpSession httpSession) {
				return date;
	    // ...
	 }

	@ApiOperation(value = "获取打卡信息", notes = "")
	@RequestMapping(method = RequestMethod.GET, value = "/{mPhone}/{mPassword}/{date}")
	@ApiImplicitParams({ 
		    @ApiImplicitParam(name = "mPhone", value = "手机号", required = true, dataType = "String",defaultValue="13266699268",paramType="Path"),
			@ApiImplicitParam(name = "mPassword", value = "密码", required = true, dataType = "String",defaultValue="111111",paramType="Path"),
			@ApiImplicitParam(name = "date", value = "日期", required = true, dataType = "String",defaultValue="2017-07-04",paramType="Path"),
		    @ApiImplicitParam(name = "httpSession", value = "Session", required = false)})
	public @ResponseBody String getSignInfo(@PathVariable String mPhone, @PathVariable String mPassword,
			@PathVariable String date, 
			HttpSession httpSession) {
		String sessionId = (String) httpSession.getAttribute("sessionId");// 拿到关键参数
		String emcode = (String) httpSession.getAttribute("emcode");// 拿到关键参数
		if (StringUtils.isEmpty(sessionId)) {
			String result = ApiUasUtils.login(mPhone, mPassword);
			sessionId = JSON.parseObject(result).getString("sessionId");// 拿到关键参数
			emcode = JSON.parseObject(result).getString("erpaccount");// 拿到关键参数
		}
		if (StringUtils.isEmpty(date)) {
			date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		String result = ApiUasUtils.getSignInfo(emcode, sessionId, date);
		return result;
	}

	@ApiOperation(value = "签到", notes = "")
	@RequestMapping(method = RequestMethod.POST, value = "/{mPhone}/{mPassword}/{date}")
	@ApiImplicitParams({ 
	    @ApiImplicitParam(name = "mPhone", value = "手机号", required = true, dataType = "String",defaultValue="13266699268",paramType="Path"),
		@ApiImplicitParam(name = "mPassword", value = "密码", required = true, dataType = "String",defaultValue="111111",paramType="Path"),
		@ApiImplicitParam(name = "date", value = "日期", required = true, dataType = "String",defaultValue="2017-07-04 13:45:13",paramType="Path"),
	    @ApiImplicitParam(name = "httpSession", value = "Session", required = false)})
	public @ResponseBody String saveSign(@PathVariable String mPhone, @PathVariable String mPassword,
			@PathVariable String date, HttpSession httpSession) {

		String sessionId = (String) httpSession.getAttribute("sessionId");// 拿到关键参数
		String emcode = (String) httpSession.getAttribute("emcode");// 拿到关键参数
		String emname = (String) httpSession.getAttribute("emname");// 拿到关键参数
		String phone = (String) httpSession.getAttribute("phone");// 拿到关键参数
		if (StringUtils.isEmpty(sessionId)) {
			String result = ApiUasUtils.login(mPhone, mPassword);
			sessionId = JSON.parseObject(result).getString("sessionId");// 拿到关键参数
			emcode = JSON.parseObject(result).getString("erpaccount");// 拿到关键参数
			emname = JSON.parseObject(result).getString("emname");// 拿到关键参数
			phone = mPhone;
		}

		float Max = 100, Min = 30.0f;
		BigDecimal db = new BigDecimal(Math.random() * (Max - Min) + Min);
		String dis = db.setScale(1, BigDecimal.ROUND_HALF_UP)// 保留30位小数并四舍五入
				.toString();
		if (StringUtils.isEmpty(date)) {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		}
		String result1 = ApiUasUtils.saveSign(sessionId, phone, emcode, emname, dis, date);
		String result2 = ApiUasUtils.getSignInfo(emcode, sessionId,
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return result1 + result2;
	}

	@ApiOperation(value = "login", notes = "")
	@RequestMapping(method = RequestMethod.GET, value = "/{phone}/{password}")
	public @ResponseBody String login(@PathVariable String phone, @PathVariable String password,
			HttpSession httpSession) {
		String result = ApiUasUtils.login(phone, password);
		String sessionId = JSON.parseObject(result).getString("sessionId");// 拿到关键参数
		String emcode = JSON.parseObject(result).getString("erpaccount");// 拿到关键参数
		String emname = JSON.parseObject(result).getString("emname");// 拿到关键参数
		boolean sucess = JSON.parseObject(result).getBooleanValue("success");
		if (sucess) {
			httpSession.setAttribute("sessionId", sessionId);
			httpSession.setAttribute("emcode", emcode);
			httpSession.setAttribute("phone", phone);
			httpSession.setAttribute("emname", emname);
		} else {
			return "登录失败！";
		}
		return result;
	}
}
