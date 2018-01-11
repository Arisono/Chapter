package com.didispace.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.didispace.util.HttpRequestUtils;

import io.swagger.annotations.Api;

@Api(value = "请求方法", description = " ")
@RestController
public class HttpMethodsController {
	
	@RequestMapping(value = "/requestDelete", method = RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> deleteParam(HttpServletRequest request) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		return goods;
	}
	
	@RequestMapping(value = "/requestPut", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> putParam(HttpServletRequest request) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		return goods;
	}
	
	@RequestMapping(value = "/requestPost", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> postParam(HttpServletRequest request) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		return goods;
	}
	
	@RequestMapping(value = "/requestGet", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getParam(HttpServletRequest request) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		return goods;
	}
	
	@RequestMapping(value = "/requestHead", method = RequestMethod.HEAD)
	public @ResponseBody Map<String, Object> headParam(HttpServletRequest request) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		return goods;
	}
}
