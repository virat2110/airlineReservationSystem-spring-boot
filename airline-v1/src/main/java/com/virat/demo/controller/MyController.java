package com.virat.demo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.virat.demo.model.Admin;
import com.virat.demo.model.Flight;
import com.virat.demo.model.User;
import com.virat.demo.service.FlightService;
import com.virat.demo.service.UserService;
import com.virat.demo.validation.UserAdmin;
import com.virat.demo.validation.UserRegValidation;

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
	
	@RequestMapping("/admin")
	public String admin() {
		return "adminDash";
	}
	
	@RequestMapping("/addFlight")
	public String addFlight() {
		if(UserAdmin.isAdmin == 1 && UserAdmin.admin ==1) {
		return "addFlight";
		}
		else {
			return "errorAdmin";
		}
	}
	
	
	@RequestMapping("/logout")
	public String logout() {
		UserAdmin.admin =-1;
		UserAdmin.isAdmin = -1;
		UserAdmin.user =-1;
		return "logout";
	}
	
	@Autowired
	public UserService us;
	
	@RequestMapping(path="RegisterData",method = RequestMethod.POST)
    public String userReg(ModelMap model,  HttpServletRequest request) {
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
		
		if(pass.equals(repass)) {
			User u = new User();
			u.setUsername(un);
			u.setName(name);
			u.setAddress(address);
			u.setAge(age);
			u.setEmail(email);
			u.setMobile(mobile);
			UserRegValidation urv = new UserRegValidation();
			u.setPassword(urv.encrypt(pass, key));
			u.setGender(gender);
			String ack = us.addUser(u);
			model.put("error" , ack);
			return "register";
		}
		else {
			model.put("error" , "password mismatch");
			return "register";
		}
	}//register
	
	@RequestMapping(path="LoginData",method = RequestMethod.POST)
    public String loginUser(ModelMap model,  HttpServletRequest request) {
		String username = request.getParameter("t1");
		String pass = request.getParameter("t2");
		int key = Integer.parseInt(request.getParameter("t3"));
		UserRegValidation urv = new UserRegValidation();
		String password = urv.encrypt(pass, key);
		
		String ack = us.verifyLogin(username, password, key);
		model.put("error", ack);
		
		if(UserAdmin.admin==1) {
			return "redirect:/admin";
		}
		else if(UserAdmin.user==1) {
			return "redirect:/";
		}
		else {
			return "LoginData";
		}
	}//login
	
	@Autowired
	public FlightService fs;
	
	@RequestMapping(path="AddFlight",method = RequestMethod.POST)
    public String addFlight(ModelMap model,  HttpServletRequest request)  {
		if(UserAdmin.isAdmin == 1 && UserAdmin.admin ==1) {
			int id = Integer.parseInt(request.getParameter("t1"));
			String source = request.getParameter("t2");
			String dest = request.getParameter("t3");
			String departure = request.getParameter("t4");
			String arrival = request.getParameter("t5");
			int price = Integer.parseInt(request.getParameter("t6"));
			
			Flight f = new Flight();
			f.setArrival(arrival);
			f.setDeparture(departure);
			f.setFlightId(id);
			f.setDest(dest);
			f.setSource(source);
			f.setPrice(price);
			f.setDelay("0");
			f.setStatus("running");
			
			String ack = fs.addFlight(f);
			model.put("msg", ack);
			return "addFlight";
			
		}
		else {
			return"redirect:/login";
		}
	}//addFlight
	

}
