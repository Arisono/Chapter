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

@Api(value = "Get请求", description = "测试get请求参数")
@RestController
public class HttpGetController {

	@RequestMapping(value = "/getParam", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> test0(
			HttpServletRequest request) throws IOException, ServletException {
		Map<String, Object> goods =HttpRequestUtils.getHttpMessage(request);
		return goods;
	}
}
