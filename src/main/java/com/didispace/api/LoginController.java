package com.didispace.api;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;

/**
 * 检验登录的控制器Cookie+Session
 * 
 * @author Arison
 *
 */
@RestController
public class LoginController {

	/**
	 * 登录
	 * @param request
	 * @param mPhone
	 * @param mPassword
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/{mPhone}/{mPassword}")
	public @ResponseBody Map<String,Object> login(
			HttpServletRequest request,
			@ApiParam(value = "手机号", required = true, defaultValue="13266699268")
            @PathVariable String mPhone,
            @ApiParam(value = "密码", required = true, defaultValue="111111")
            @PathVariable String mPassword){
		Map<String,Object> result=new LinkedHashMap<>();
		if(!StringUtils.isEmpty(mPhone)&&!StringUtils.isEmpty(mPassword)){
			if(mPhone.equals("13266699268")&&mPassword.equals("111111")){
				request.getSession().setAttribute("isLogin", true);
				result.put("success", true);
			}else{
				request.getSession().setAttribute("isLogin", false);
				result.put("success", "false");
				result.put("message", "账户或者密码错误！");
			}
		}else{
			result.put("success", false);
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/info")
	public @ResponseBody Map<String,Object> getUserInfo(HttpServletRequest request) {
		Map<String,Object> result=new LinkedHashMap<>();
		HttpSession httpSession=request.getSession();
		if(httpSession!=null){
			if(httpSession.getAttribute("isLogin")!=null){
				boolean isLogin= (boolean) httpSession.getAttribute("isLogin");
				if(isLogin){
					result.put("user", "arison");
				}else{
					result.put("error", "请进行登录操作！");
				}
			}else{
				result.put("error", "请进行登录操作！");
			}
		}else{
			result.put("error", "请进行登录操作！");
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/exit")
	public @ResponseBody Map<String,Object> exit(HttpServletRequest request){
		
		Map<String,Object> result=new LinkedHashMap<>();
		HttpSession httpSession=request.getSession();
		if(httpSession!=null){
			httpSession.removeAttribute("isLogin");
			result.put("message", "退出登录，操作成功！");
		}else{
			result.put("message", "您处于未登录状态！");
		}
		return result;
		
	}
}
