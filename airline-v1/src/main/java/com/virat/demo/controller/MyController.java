package com.virat.demo.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.virat.demo.model.User;
import com.virat.demo.service.UserService;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public String index() {
		return "index1";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	@Autowired
	public UserService us;
	
	@RequestMapping(path="RegisterData",method = RequestMethod.POST)
    public String checkLogin(ModelMap model,  HttpServletRequest request) {
		String un = request.getParameter("t1");
		String name = request.getParameter("t2");
		String email = request.getParameter("t3");
		String gender = request.getParameter("t4");
		int age = Integer.parseInt(request.getParameter("t5"));
		long mobile = Long.parseLong(request.getParameter("t6"));
		String address = request.getParameter("t7");
		String pass = request.getParameter("t8");
		String repass = request.getParameter("t9");
		int key = Integer.parseInt(request.getParameter("t10"));
		
		User u = new User();
		u.setUsername(un);
		u.setName(name);
		u.setAddress(address);
		u.setAge(age);
		u.setEmail(email);
		u.setMobile(mobile);
		u.setPassword(pass);
		u.setGender(gender);
		
		if(pass==repass) {
			String ack = us.addUser(u);
			model.put("error" , ack);
			return "register";
		}
		else {
			model.put("error" , "password mismatch");
			return "register";
		}
	}
	

}
