package com.xchgx.controller;

import hbou.image.domain.IdentifyingCode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class Hello {

	@RequestMapping("/xy")
	//http://localhost:8080/Hboucn/xy.do
	public ModelAndView xy(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("errors/exception");
		return mav ;
	}
	
	@RequestMapping("/login")
	//http://localhost:8080/Hboucn/login.do
	//contrller 控制器一般不用来作 计算处理的工作。
	//service 服务层处理工作
	// dao  层处理数据库
	// bean 只有定义不做任何处理代码，只有get/set
	public ModelAndView login(String user , String pass){
		//计算，处理。最终结果
		String result = "登陆成功";
//		IdentifyingCode identifyingCode = new IdentifyingCode(20000);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("errors/exception");
		mav.addObject("result",result);
		return mav ;
	}
}
