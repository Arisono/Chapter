package com.didispace.exception;

import com.alibaba.fastjson.JSON;
import com.didispace.model.common.ErrorInfo;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("unused")
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("exceptionStr", JSON.toJSONString(e));
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
    
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ErrorInfo<String> default2ErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//    	  ErrorInfo<String> r = new ErrorInfo<>();
//    	  System.out.println("全局异常："+JSON.toJSON(e));
//          r.setMessage(e.toString());
//          r.setCode(ErrorInfo.ERROR);
//          r.setData("Some Data");
//          r.setUrl(req.getRequestURL().toString());
//          return r;
//    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}

