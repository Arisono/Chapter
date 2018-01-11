package com.didispace.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.didispace.util.HttpRequestUtils;

@RestController
public class IndexController {
	
	
	//forward
	
	@RequestMapping("/index/forward")
    public String indexForward(){
        //转发方式1
       // return "home.jsp";
        //转发方式2
        return "forward:error.html";
        //重定向方式
       // return "redirect:index.jsp";
    }
	
	@RequestMapping("/index/html")
    public String indexHtml(){
        //转发方式1
       // return "home.jsp";
        //转发方式2
        return "error.html";
        //重定向方式
       // return "redirect:index.jsp";
    }

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelMap map) {
		map.addAttribute("host", "http://blog.didispace.com");
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/layout", method = RequestMethod.GET)
	public ModelAndView layout(ModelMap map) {
		map.addAttribute("host", "http://blog.didispace.com");
		return new ModelAndView("/layout/layout");
	}

	@RequestMapping("/index.action")
	public String indexAction() {
		return "index";
	}
	


	@RequestMapping(value = "/context", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getContext(
			HttpServletRequest request) throws IOException, ServletException {
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
