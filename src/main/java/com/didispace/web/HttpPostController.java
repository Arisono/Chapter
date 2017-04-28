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

import io.swagger.annotations.Api;

/**
 * 测试post参数请求
 * 
 * @author Arison
 *
 */
@SuppressWarnings("unused")
@Api(value = "POST接口", description = " ")
@RestController
public class HttpPostController {

	/**
	 * 方式一：表单传递
	 * 
	 * @RequestBody
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/postParam", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> postParam(HttpServletRequest request) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		return goods;
	}

	/**
	 * @RequestBody
	 * @param body
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/postBodyByString", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> postBodyByString(@RequestBody String body, HttpServletRequest request) {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(body, request);
		return goods;
	}

	/**
	 * @RequestBody
	 * @param body
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/postBodyByObject", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> postBodyByObject(@RequestBody Object body, HttpServletRequest request) {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(body, request);
		return goods;
	}

	/**
	 * @RequestBody
	 * @param body
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/postBodyByModel", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> postBodyByModel(@RequestBody ErrorInfo<String> body, HttpServletRequest request) {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(body, request);
		return goods;
	}

	/**
	 * 多个请求体---出现错误
	 * 
	 * @RequestBody
	 * @param body
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/postBodyByMuli", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> postBodyByMuli(
			// @RequestBody String body,
			// @RequestBody String body2,
			HttpServletRequest request) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		// goods.put("body2", body2);
		return goods;
	}

}
