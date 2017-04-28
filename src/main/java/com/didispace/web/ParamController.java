package com.didispace.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.didispace.dto.ErrorInfo;

import io.swagger.annotations.Api;

/**
 * @author Arison
 *
 */
@Api(value = "参数传递", description = " ")
@RestController
public class ParamController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public @ResponseBody String index() {
		return "{}";
	}

	@RequestMapping(value = "/getMap", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> index1() {
		Map<String, Object> goods = new HashMap<String, Object>();
		goods.put("1", "11");
		goods.put("2", "22");
		goods.put("3", "33");
		return goods;
	}

	@RequestMapping(value = "/param/{id}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> index2(@PathVariable String id) {
		Map<String, Object> goods = new HashMap<String, Object>();
		goods.put("参数id", id);
		return goods;
	}

	/**
	 * 错误的写法
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/param?id={id}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> index3(@PathVariable String id) {
		Map<String, Object> goods = new HashMap<String, Object>();
		goods.put("参数id", id);
		return goods;
	}

	/*
	 * @RequestMapping("/param") public @ResponseBody Map<String, Object>
	 * index3(HttpServletRequest request) { Map<String, Object> goods=new
	 * HashMap<String, Object>(); goods.put("参数id", request.getParameter("id"));
	 * return goods; }
	 */

	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> index4(@RequestBody String body, HttpServletRequest request) {
		Map<String, Object> goods = new HashMap<String, Object>();
		Map<String, Object> header = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String key = (String) paramNames.nextElement();
			Object value = request.getParameter(key);
			goods.put(key, value);
		}

		@SuppressWarnings("rawtypes")
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			header.put(key, value);
		}
		goods.put("headers", header);
		goods.put("body", body);
		return goods;
	}

	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> index5(@RequestBody String body, HttpServletRequest request) {
		Map<String, Object> goods = new HashMap<String, Object>();
		Map<String, Object> header = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String key = (String) paramNames.nextElement();
			Object value = request.getParameter(key);
			goods.put(key, value);
		}

		@SuppressWarnings("rawtypes")
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			header.put(key, value);
		}
		goods.put("headers", header);
		goods.put("body", body);
		return goods;
	}

	@RequestMapping(value = "/paramBody", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> index6(@RequestBody ErrorInfo<String> body, HttpServletRequest request) {
		Map<String, Object> goods = new HashMap<String, Object>();
		Map<String, Object> header = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String key = (String) paramNames.nextElement();
			Object value = request.getParameter(key);
			goods.put(key, value);
		}

		@SuppressWarnings("rawtypes")
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			header.put(key, value);
		}
		goods.put("headers", header);
		goods.put("body", body);
		return goods;
	}

	/**
	 * @RequestBody 多个的时候,是不允许出现
	 * @param body
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/paramModel", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> index8(@RequestBody String body, @RequestBody String body2, HttpServletRequest request) {
		Map<String, Object> goods = new HashMap<String, Object>();
		Map<String, Object> header = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String key = (String) paramNames.nextElement();
			Object value = request.getParameter(key);
			goods.put(key, value);
		}

		@SuppressWarnings("rawtypes")
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			header.put(key, value);
		}
		goods.put("headers", header);
		goods.put("body", body);
		return goods;
	}

}