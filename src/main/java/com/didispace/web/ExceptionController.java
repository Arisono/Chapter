package com.didispace.web;

import java.util.ArrayList;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.didispace.exception.MyException;

import io.swagger.annotations.Api;

@Api(value = "异常请求", description = " ")
@RestController
public class ExceptionController {

	@SuppressWarnings("null")
	@RequestMapping(value = "/exception01", method = RequestMethod.GET)
	public String testException1() {
		ArrayList<String> lists = null;
		System.out.println(lists.size());
		return "testException";
	}

	@RequestMapping(value = "/exception02", method = RequestMethod.GET)
	public String testException2() {
		testTaskRun();
		return "testException";
	}

	@RequestMapping(value = "/exception03", method = RequestMethod.GET)
	public String testException3() {
		return "testException";
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() throws Exception {
		// new Exception("发生错误")
		throw new Exception("发生错误");
	}

	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public String json() throws MyException {
		throw new MyException("发生错误2");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelMap map) {
		map.addAttribute("host", "http://blog.didispace.com");
		return new ModelAndView("index");
//		map.addAttribute("host", "http://blog.didispace.com");
//		return new ModelAndView("html/index.html");
	}

	public void testTaskRun() {
		// ArrayList<String> lists=null;
		// System.out.println(lists.size());
		ArrayList<String> lists = new ArrayList<>();
		System.out.println(lists.get(0).toString());
	}
}
