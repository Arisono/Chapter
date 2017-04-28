package com.didispace.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.didispace.util.HttpRequestUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
  常见的媒体格式类型如下：

    text/html ： HTML格式
    text/plain ：纯文本格式      
    text/xml ：  XML格式
    image/gif ：gif图片格式    
    image/jpeg ：jpg图片格式 
    image/png：png图片格式
    
   以application开头的媒体格式类型：

   application/xhtml+xml ：XHTML格式
   application/xml     ： XML数据格式
   application/atom+xml  ：Atom XML聚合格式    
   application/json    ： JSON数据格式
   application/pdf       ：pdf格式  
   application/msword  ： Word文档格式
   application/octet-stream ： 二进制流数据（如常见的文件下载）
   application/x-www-form-urlencoded ： <form encType=””>中默认的encType，form表单数据被编码为key/value格式发送到服务器（表单默认的提交数据的格式）
 
   另外一种常见的媒体格式是上传文件之时使用的：

    multipart/form-data ： 需要在表单中进行文件上传时，就需要使用该格式
    
    以上就是我们在日常的开发中，经常会用到的若干content-type的内容格式。

 * @author Arison
 *
 */
@Api(value = "get请求", description = " ")
@RestController
public class HttpGetController {

	@ApiOperation(value = "默认", notes = "")
	@RequestMapping(value = "/getParam", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getParam(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		return goods;
	}
	
	@ApiOperation(value = "text/html", notes = "text/html")
	@RequestMapping(value = "/getParam/html", method = RequestMethod.GET
			, produces = "text/html; charset=utf-8")
	public @ResponseBody Object getParamHtml(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		//注意返回类型需要是Object或者String
		return JSON.toJSONString(goods);
	}
	
	@ApiOperation(value = "text/plain", notes = "text/plain")
	@RequestMapping(value = "/getParam/text", method = RequestMethod.GET
			, produces = "text/plain; charset=utf-8")
	public @ResponseBody String getParamText(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		return JSON.toJSONString(goods);
	}
	
	@ApiOperation(value = "text/xml", notes = "text/xml")
	@RequestMapping(value = "/getParam/xml", method = RequestMethod.GET
			, produces = "text/xml; charset=utf-8")
	public @ResponseBody Map<String, Object> getParamXml(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		return goods;
	}
	
	@ApiOperation(value = "text/json", notes = "text/json")
	@RequestMapping(value = "/getParam/json", method = RequestMethod.GET
			, produces = "text/json; charset=utf-8")
	public @ResponseBody String getParamJson(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		return JSON.toJSONString(goods);
	}
	
	@ApiOperation(value = "application/json", notes = "application/json")
	@RequestMapping(value = "/getParam/app/json", method = RequestMethod.GET
			, produces = "application/json; charset=utf-8")
	public @ResponseBody Map<String, Object> getParamAppJson(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		return goods;
	}
	
	@ApiOperation(value = "application/xml", notes = "application/xml")
	@RequestMapping(value = "/getParam/app/xml", method = RequestMethod.GET
			, produces = "application/xml; charset=utf-8")
	public @ResponseBody Map<String, Object> getParamAppXml(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		return goods;
	}
	
	@ApiOperation(value = "application/xhtml+xml", notes = "application/xhtml+xml")
	@RequestMapping(value = "/getParam/app/html", method = RequestMethod.GET
			, produces = "application/xhtml+xml ; charset=utf-8")
	public @ResponseBody Map<String, Object> getParamAppHtml(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		return goods;
	}
	
	@ApiOperation(value = "application/text", notes = "application/text")
	@RequestMapping(value = "/getParam/app/text", method = RequestMethod.GET
			, produces = "application/text ; charset=utf-8")
	public @ResponseBody String getParamAppText(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		Map<String, Object> goods = HttpRequestUtils.getHttpMessage(request);
		return JSON.toJSONString(goods);
	}
}
