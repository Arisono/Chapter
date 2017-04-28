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

@Api(value = "请求信息", description = " ")
@RestController
public class ContextController {

	// ,produces="application/xml"
	@RequestMapping(value = "/getContextInfo", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getContext(HttpServletRequest request) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		String path = request.getContextPath();
		String path1 = request.getServletContext().getContextPath();
		String path2 = request.getServletContext().getRealPath("/");
		String path3 = request.getServletContext().getResource("/").toString();
		String path4 = request.getServletPath();
		String path5 = request.getRequestURI();

		goods.put("request.getContextPath", path);
		goods.put("request.getServletContext.getContextPath", path1);
		goods.put("request.getServletContext.getRealPath", path2);
		goods.put("resoure", path3);
		goods.put("request.getServletPath", path4);
		goods.put("request.getRequestURI", path5);
		return goods;
	}
}
