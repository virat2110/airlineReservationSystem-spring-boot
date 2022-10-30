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

import com.virat.demo.model.Booking;
import com.virat.demo.model.Coupon;
import com.virat.demo.model.Flight;
import com.virat.demo.model.User;
import com.virat.demo.service.BookingService;
import com.virat.demo.service.CouponService;
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
	
	@Autowired
	public BookingService bs;
	
	@Autowired
	public CouponService cs;
	
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
		if(UserAdmin.admin == 1) {
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
	public String logout(HttpServletRequest request) {
		UserAdmin.admin =-1;
		UserAdmin.isAdmin = -1;
		UserAdmin.user =-1;
		UserAdmin.pnr =-1;
		HttpSession session = request.getSession();
		session.invalidate();
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
		model.put("errorLogin", ack);
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
	public String flightTxn(HttpServletRequest request, @PathVariable int id) {
		if(UserAdmin.user ==1) {
			Flight f = fs.flightById(id);
			HttpSession session= request.getSession();
			if(f == null) {
				return "redirect:/searchflight";
			}
			else {
				String user = (String)session.getAttribute("uUser");
				User u = us.userById(user);
				if(user == null) {
					return "redirect:/searchflight";
				}
				else {
					session.setAttribute("fliightId", id);
					session.setAttribute("userObj", u);
					session.setAttribute("flightObj", f);
					return "transaction";
				}
			}
		}
		else {
			return "login";
		}
	}//flight & txn page
	
	
	@RequestMapping("/bookTicket")
	public String booking(HttpServletRequest request)  {
		if(UserAdmin.user==1) {
			HttpSession session = request.getSession();
			
			String user = (String)session.getAttribute("uUser");
			Flight f = (Flight) session.getAttribute("flightObj");
			int price = (int) session.getAttribute("disPrice");
			Booking b = new Booking();
			b.setFlightid(f.getFlightId());
			b.setPrice(price);
			b.setStatus("Booked");
			b.setUsername(user);
			bs.bookTicket(b);
			String ack = "Ticket booked with pnr: " + String.valueOf(UserAdmin.pnr);
			session.setAttribute("msga", ack);
			return "redirect:/searchflight";
		}
		else {
			return "/login";
		}
	}// Ticket booking confirmation
	
	@RequestMapping("/coupon")
	public String coupon() {
		if(UserAdmin.admin==1 && UserAdmin.isAdmin ==1) {
			return "addCoupon";
		}
		else {
			return "errorAdmin";
		}
	}
	
	@RequestMapping(path="AddCoupon", method=RequestMethod.POST)
	public String addCoupon(HttpServletRequest request) {
		String name = request.getParameter("t1");
		int per = Integer.parseInt(request.getParameter("t2"));
		int active = Integer.parseInt(request.getParameter("t3"));
		Coupon c = new Coupon();
		c.setName(name);
		c.setPercentage(per);
		c.setActive(active);
		
		String ack = cs.addCoupon(c);
		HttpSession session = request.getSession();
		session.setAttribute("couponMsg", ack);
		return "addCoupon";
	}//addingCoupon
	
	@RequestMapping(path="ApplyCoupon", method = RequestMethod.GET)
	public String applyCoupon(HttpServletRequest request) {
		if(UserAdmin.user ==1) {
			String name = request.getParameter("t1");
			System.out.println(name);
			HttpSession session = request.getSession();
			int per = cs.discount(name);
			System.out.println(per);
			String ack = "";
			if(per ==0) {
				ack+= "Not a valid coupon";
			}
			else if(per == -1) {
				ack+="Coupon expired";
			}
			else {
				Flight f = (Flight) session.getAttribute("flightObj");
				int price = cs.disPrice(f.getPrice(), per, name.charAt(0));
				System.out.println(price);
				session.setAttribute("disPrice", price);
				ack+= "Discounted Price:  ₹ " + String.valueOf(price);
				
			}
			session.setAttribute("couponMsg", ack);
			return "transaction";
		}
		else {
			return "login";
		}
	}//couponApplied
	

}
