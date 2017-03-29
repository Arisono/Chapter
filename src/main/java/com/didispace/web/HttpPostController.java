package com.didispace.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.didispace.dto.ErrorInfo;
import com.didispace.util.HttpRequestUtils;

/**
 * 测试post参数请求 
 * @author Arison
 *
 */
@SuppressWarnings("unused")
@RestController
public class HttpPostController {
	
	/**
	 * @RequestBody 
	 * @param request
	 * @return
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/postParam", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> test0(
			HttpServletRequest request) throws IOException, ServletException {
		System.out.println("/postParam()  执行");
		Map<String, Object> goods =HttpRequestUtils.getHttpMessage(request);
		return goods;
	}
	
	/**
	 * @RequestBody 
	 * @param body
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/postBodyByString", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> test1(
			@RequestBody String body, 
			HttpServletRequest request) {
		Map<String, Object> goods =HttpRequestUtils.getHttpMessage(body, request);
		return goods;
	}

	/**
	 * @RequestBody 
	 * @param body
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/postBodyByObject", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> test2(
			@RequestBody Object body, 
			HttpServletRequest request) {
		Map<String, Object> goods =HttpRequestUtils.getHttpMessage(body, request);
		return goods;
	}
	
	/**
	 * @RequestBody 
	 * @param body
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/postBodyByModel", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> test2(
			@RequestBody ErrorInfo<String> body, 
			HttpServletRequest request) {
		Map<String, Object> goods =HttpRequestUtils.getHttpMessage(body, request);
		return goods;
	}
	
	/**
	 * 多个请求体---出现错误
	 * @RequestBody 
	 * @param body
	 * @param request
	 * @return
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/postBodyByMuli", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> test2(
//			@RequestBody String body, 
//			@RequestBody String body2, 
			HttpServletRequest request) throws IOException, ServletException {
		Map<String, Object> goods =HttpRequestUtils.getHttpMessage( request);
//		goods.put("body2", body2);
		return goods;
	}
	

}
