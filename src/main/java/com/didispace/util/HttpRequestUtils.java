package com.didispace.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 * 处理HTTP 请求的工具类
 * @author Arison
 *
 */
@SuppressWarnings("unused")
public class HttpRequestUtils {
	
	
	/**
	 * @param body Object
	 * @param request HttpServletRequest
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> getHttpMessage(Object body, HttpServletRequest request) {
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
	 * @param body Object
	 * @param request HttpServletRequest
	 * @return Map<String, Object>
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public static Map<String, Object> getHttpMessage(HttpServletRequest request)  {
		Map<String, Object> goods = new HashMap<String, Object>();
		Map<String, Object> header = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		Enumeration paramNames = request.getParameterNames();
		//解析文件流
//		Collection<Part> parts=request.getParts();
//         for (Part part : parts) {
//		  InputStream input=part.getInputStream();
//		  String msg= IOUtils.convertStreamToString(input);
//		  goods.put("parts", msg);
//		  
//		}
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
		return goods;
	}
}
