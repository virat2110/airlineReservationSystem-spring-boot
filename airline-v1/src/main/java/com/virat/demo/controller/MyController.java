package com.virat.demo.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.virat.demo.model.Flight;
import com.virat.demo.model.User;
import com.virat.demo.service.FlightService;
import com.virat.demo.service.SourceDestService;
import com.virat.demo.service.UserService;
import com.virat.demo.validation.UserAdmin;
import com.virat.demo.validation.UserRegValidation;

@Controller
public class MyController {
	
	@Autowired
	public SourceDestService sds;
	@Autowired
	public UserService us;
	@Autowired
	public FlightService fs;
	
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
		if(UserAdmin.isAdmin == 1 && UserAdmin.admin ==1) {
			return "adminDash";
	}
		else {
			return "login";
		}
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
	
	
	@RequestMapping("/searchflight")
	public String searchFlight(HttpServletRequest request) {
		if(UserAdmin.user==1) {
			List<String> ls = sds.getSource();
			List<String> ld = sds.getDest();
			request.setAttribute("ls", ls);
			request.setAttribute("ld", ld);
			return "searchFlightLogged";
		}
		else {
			return "login";
		}
	}
	
	
	@RequestMapping("/logout")
	public String logout() {
		UserAdmin.admin =-1;
		UserAdmin.isAdmin = -1;
		UserAdmin.user =-1;
		return "logout";
	}
	
	@RequestMapping("/flightListUser")
	public String flightList(HttpServletRequest request) {
		if(UserAdmin.user==1) {
			return "flightListUser";
		}
		else {
			return "login";
		}
	}
	
	
	
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
		HttpSession session = request.getSession();
		if(UserAdmin.admin==1) {
			
			session.setAttribute("aUser", username);
			return "redirect:/admin";
		}
		else if(UserAdmin.user==1) {
			session.setAttribute("uUser", username);
			return "redirect:/searchflight";
		}
		else {
			return "login";
		}
	}//login
	
	
	
	@RequestMapping(path="AddFlight",method = RequestMethod.POST)
    public String addFlight(ModelMap model,  HttpServletRequest request)  {
		if(UserAdmin.isAdmin == 1 && UserAdmin.admin ==1) {
			int id = Integer.parseInt(request.getParameter("t1"));
			String name = request.getParameter("t2");
			String source = request.getParameter("t3");
			String dest = request.getParameter("t4");
			String departure = request.getParameter("t5");
			String arrival = request.getParameter("t6");
			int price = Integer.parseInt(request.getParameter("t7"));
			
			Flight f = new Flight();
			f.setArrival(arrival);
			f.setDeparture(departure);
			f.setFlightId(id);
			f.setDest(dest);
			f.setSource(source);
			f.setPrice(price);
			f.setName(name);
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
	
	@RequestMapping(path="searchFlight",method = RequestMethod.POST)
    public String searchFlight(ModelMap model,  HttpServletRequest request)  {
		if(UserAdmin.user ==1) {
			String source = request.getParameter("t1");
			String dest = request.getParameter("t2");
			List<Flight> list = fs.flightList(source, dest);
			HttpSession session = request.getSession();
			if(list.size() ==0) {
				
				session.setAttribute("msga", "No flight Found");
				return "redirect:/searchflight";
			}
			else {
				session.setAttribute("source", source);
				session.setAttribute("dest", dest);
				request.setAttribute("flightList", list);
				return "flightListUser";
			}
			
		}
		else {
			return "login";
		}
	}//searchFlight
	
	@RequestMapping(path="flight/{id}",method = RequestMethod.GET)
	public String pathparameterdemo1(@PathVariable int id) {
		
		System.out.println(id+"   ***************    ");
		return "redirect:/searchflight";
	}
	
	
	

}
