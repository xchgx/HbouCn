package com.xchgx.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/** 
 * 基于HandlerExceptionResolver接口的异常处理类 
 * @author ZYWANG 2011-4-2 
 */  
public class CustomExceptionHandler implements HandlerExceptionResolver {  

	@Override  
	public ModelAndView resolveException(HttpServletRequest request,  
			HttpServletResponse response, Object object, Exception ex) {
		System.out.println(ex.getMessage());
		System.out.println(ex.getLocalizedMessage());
		if(ex instanceof IOException){  
			return new ModelAndView("errors/ioexp");  
		}else if(ex instanceof SQLException){  
			return new ModelAndView("errors/sqlexp");  
		}else if(ex instanceof NumberFormatException){
			return new ModelAndView("errors/numberexp");
		}else if(ex instanceof NullPointerException){
			return new ModelAndView("errors/nullexp");
		}else if(ex instanceof UserExistException){
			return new ModelAndView("errors/userExistExp");
		}
		return new ModelAndView("errors/exception");  
	}  

}  