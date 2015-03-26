package com.xchgx.zsbwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xchgx.controller.BaseController;

@Controller
@RequestMapping(value="/zsbwork")
public class StudentController extends BaseController{

	@RequestMapping(value="/manager/", method=RequestMethod.GET)
	public String managerIndex(){
		return "manager/index";
	}
}
