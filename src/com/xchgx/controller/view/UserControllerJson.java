package com.xchgx.controller.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xchgx.controller.BaseController;
import com.xchgx.domain.User;
import com.xchgx.service.UserService;
import com.xchgx.zsbwork.bean.Teacher;

@Controller
@RequestMapping("/manager/json")
public class UserControllerJson extends BaseController{

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="userList", method={RequestMethod.GET})
	public @ResponseBody List<User> getUserList(){
		List<User> users = userService.getAllUsers();
		List<User> userList = new ArrayList<User>();
		for(User u : users){
			User user = new User(u.getName(), u.getXm(), u.getPassword(), u.getLocked(), u.getCredit());
			user.setId(u.getId());
			if(u.getTeacher() != null){
				Teacher teacher = new Teacher(u.getTeacher().getJgh(), u.getTeacher().getName(), u.getTeacher().getSex(), u.getTeacher().getAge(), u.getTeacher().getPosition(), u.getTeacher().getPhone(), u.getTeacher().getDescription());
				teacher.setId(u.getTeacher().getId());
				user.setTeacher(teacher);
			}
			userList.add(user);
		}
		return userList;
	}
}
