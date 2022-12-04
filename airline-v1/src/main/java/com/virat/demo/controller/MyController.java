package com.virat.demo.controller;
import java.sql.Timestamp;


import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
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
import com.virat.demo.repository.UserRepository;
import com.virat.demo.service.AdminService;
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
	
	@Autowired
	public UserRepository ur;
	
	@Autowired
	public AdminService as;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "index1";
	}
	@RequestMapping(path = "/error", method = RequestMethod.GET)
	public String error() {
		return "error";
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
	public String admin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("aUser") !=null) {
			List<String> adminDash = as.adminFlightDetails();
			request.setAttribute("adminDash", adminDash);
			
			
			
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
			u.setLastSouce("Source");
			u.setLastDest("Destination");
			
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
		HttpSession session = request.getSession();
		String username = request.getParameter("t1");
		String pass = request.getParameter("t2");
		int key = Integer.parseInt(request.getParameter("t3"));
		UserRegValidation urv = new UserRegValidation();
		String password = urv.encrypt(pass, key);
		
		String ack = us.verifyLogin(username, password, key);
		model.put("errorLogin", ack);
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
	
	@RequestMapping("/searchflight")
	public String searchFlight(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(UserAdmin.user==1) {
			List<String> ls = sds.getSource();
			List<String> ld = sds.getDest();
			String username = (String) session.getAttribute("uUser");
			List<String> latest = us.latestSearch(username);
			request.setAttribute("latest", latest);
			request.setAttribute("ls", ls);
			request.setAttribute("ld", ld);
			return "searchFlightLogged";
		}
		else {
			return "login";
		}
	}
	
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
				String un = (String) session.getAttribute("uUser");
				User u = ur.getById(un);
				u.setLastSouce(source);
				u.setLastDest(dest);
				 us.updateSD(u);
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
	
	@RequestMapping(path = "/searchLast/{source}/{dest}", method = RequestMethod.GET)
	public String viewFlight(HttpServletRequest request, @PathVariable String source, @PathVariable String dest) {
		
		HttpSession session =request.getSession();
		List<Flight> list = fs.flightList(source, dest);
		if(list.size() ==0) {
			
			
			return "redirect:/searchflight";
		}
		else {
			request.setAttribute("flightList", list);
			return "flightListUser";
		}
		
	}
	
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
			if((Flight) session.getAttribute("flightObj") == null) {
				return "redirect:/searchflight";
			}
			Flight f = (Flight) session.getAttribute("flightObj");
			int price =0;
			if( session.getAttribute("disPrice")==null) {
				price = f.getPrice();
				session.setAttribute("disPrice", price);
			}
			else {
				price = (int) session.getAttribute("disPrice");
			}
			Booking b = new Booking();
			b.setFlightid(f.getFlightId());
			b.setPrice(price);
			b.setStatus("Booked");
			b.setUsername(user);
			Date d = new Date();
			Timestamp timestamp2 = new Timestamp(d.getTime());
			b.setTimestamp(timestamp2);
			int pnr = bs.pnrGenerate();
			b.setPnr(pnr);
			session.setAttribute("pnr", pnr);
			bs.bookTicket(b);
			
			return "confirmation";
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
		HttpSession session = request.getSession();
		if(UserAdmin.user ==1) {
			String name = request.getParameter("t1");
			int per = cs.discount(name);
			
			String ack = "";
			if(per ==0) {
				ack+= "Not a valid coupon";
			}
			else if(per == -1) {
				ack+="Coupon expired";
			}
			else {
				if(session.getAttribute("disPrice") ==null) {
				Flight f = (Flight) session.getAttribute("flightObj");
				int price = cs.disPrice(f.getPrice(), per, name.charAt(0));
				
				session.setAttribute("disPrice", price);
				ack+= "Discounted Price:  ₹ " + String.valueOf(price);
				session.setAttribute("couponMsg", ack);
				}
				
			}
			if(session.getAttribute("couponMsg")=="") {
				session.setAttribute("couponMsg", ack);
			}
			return "transaction";
		}
		else {
			return "login";
		}
	}//couponApplied
	
	@RequestMapping(path="flightList", method=RequestMethod.GET)
	public String allFlight(HttpServletRequest request) {
	if(UserAdmin.isAdmin ==1) {
			List<Flight> l = fs.allFlight();
			HttpSession session = request.getSession();
			session.setAttribute("allFlight", l);
			return "flightData";
		}
		else {
			return "errorAdmin";
		}
		
	}//flghtList for admin
	
	@RequestMapping(path="flightList/{id}", method = RequestMethod.GET)
	public String editFlightById(HttpServletRequest request, @PathVariable int id) {
		Flight f = fs.flightById(id);
		HttpSession session = request.getSession();
		session.setAttribute("flightEdit", f);
		return "editFlight";
	}
	
	@RequestMapping(path="editFlightData",method = RequestMethod.POST)
	public String editFlight(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Flight f = (Flight) session.getAttribute("flightEdit");
		if(request.getParameter("t3") != "") {
			f.setDeparture(request.getParameter("t3"));
		}
		
		if(request.getParameter("t4") != "") {
			f.setArrival(request.getParameter("t4"));
		}
		
		if(request.getParameter("t5") != "") {
			f.setPrice(Integer.parseInt(request.getParameter("t5")));
		}
		if(request.getParameter("t6") != "") {
			f.setStatus(request.getParameter("t6"));
		}
		if(request.getParameter("t7") != "") {
			f.setDelay(request.getParameter("t7"));
		}
		String ack = fs.Update(f);
		session.setAttribute("msgEFD", ack);
		
		
		return "editFlight";
	}//Edited flight data
	
	@RequestMapping(path="/AllFlight",method = RequestMethod.GET)
	public String delayedFlight(HttpServletRequest request) {
		List<Flight> l = fs.delayedFlight();
		request.setAttribute("FlightList", l);
		return "FlightList";
	}

	
	@RequestMapping(path = "FlightsearchHome", method = RequestMethod.POST)
	public String searchFlightHome(HttpServletRequest request) {
		String source = request.getParameter("source");
		String dest = request.getParameter("dest");
		List<Flight> l = fs.flightList(source, dest);
		request.setAttribute("FlightList", l);
		return "FlightList";
	}
	
	@RequestMapping("/viewBooking")
	public String viewBooking(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("uUser") !=null) {
			String user = (String) session.getAttribute("uUser");
			User u = us.userById(user);
			List<List<String>> l = bs.showBookingById(user);
			session.setAttribute("user", u);
			session.setAttribute("viewBooking", l);
			if(l.size()==0) {
				return "redirect:/searchflight";
			}
			else {
				return "viewBooking";
			}
		}
		else {
			return "redirect:/login";
		}
	}
	
	
	@RequestMapping("/cancelTicket/{pnr}/{username}")
	public String cancelTicket(HttpServletRequest request, @PathVariable String pnr, @PathVariable String username) {
		HttpSession session = request.getSession();
		if(session.getAttribute("uUser") !=null) {
		int p = Integer.parseInt(pnr);
		String ack = bs.cancelTicket(username, p);
		request.setAttribute("ackCancel", ack);
		return "redirect:/viewBooking";
		}
		else {
			return "redirect:/login";
		}
		
	}
	
	@RequestMapping("/viewTicket/{pnr}/{username}")
	public String viewTicket(HttpServletRequest request, @PathVariable String pnr, @PathVariable String username) {
		HttpSession session = request.getSession();
		if(session.getAttribute("uUser") !=null) {
		int p = Integer.parseInt(pnr);
		List<String> ack = bs.viewTicket(username, p);
		request.setAttribute("ackView", ack);
		return "viewTicket";
		}
		else {
			return "redirect:/login";
		}
		
	}//view ticket
	@RequestMapping("/updateUser/{username}")
	public String viewUpdatePage(HttpServletRequest request, @PathVariable String username) {
		HttpSession session = request.getSession();
		if(session.getAttribute("uUser") != null) {
			session.setAttribute("username", username);
			 User u = us.userById(username);
			 session.setAttribute("userUpdate", u);
			return "updateUser";
		}
		else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(path = "UpdateUser", method = RequestMethod.POST)
	public String updateUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("uUser") != null) {
		String username =(String) session.getAttribute("username");
		User u = ur.getById(username);
		if(request.getParameter("t3") != "") {
			String email = request.getParameter("t3");
			if(us.verifyemail(email)) {
				session.setAttribute("msgUU", "email already exsits");
				return "updateUser";
			}
			else {
				u.setEmail(email);
			}
		}
		if(request.getParameter("t4") != "") {
			u.setName(request.getParameter("t4"));
		}
		if(request.getParameter("t5") != "") {
			u.setAddress(request.getParameter("t5"));
		}
		if(request.getParameter("t6") != "") {
			u.setMobile(Long.parseLong(request.getParameter("t6")));
		}
		String ack = us.updateUser(u);
		session.setAttribute("msgUU", ack);
		return "updateUser";
		}
		else {
			return "redirect:/login";
		}
		
		
		
	}
	
	

}
