package com.virat.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virat.demo.model.Booking;
import com.virat.demo.model.Flight;
import com.virat.demo.repository.BookingRepository;
import com.virat.demo.validation.UserAdmin;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	public BookingRepository br;
	
	@Autowired
	public FlightService  fs;
	

	@Override
	public String bookTicket(Booking b) {
		br.save(b);
		return "Ticket Booked";
		
		
	}
	
	public int pnrGenerate() {
		boolean repeat = true;
		int pnr = generate();
		while(repeat) {
			if(br.existsById(pnr)) {
				pnr = generate();
			}
			else {
				repeat = false;
				break;
			}
		}
		
		return pnr;
		
	}
	public static int generate() {
		int min = 1000;
		int max = 9999;
		return (int) ((Math.random() * (max - min)) + min);
	}

	@Override
	public String cancelTicket(String username, int pnr) {
		String ack="";
		if(br.existsById(pnr)) {
			Booking b = br.getById(pnr);
			if(b.getUsername().equals(username)) {
				b.setStatus("cancelled");
				ack+="Ticket Cancelled";
				br.save(b);
			}
			else {
				ack+="Error in cancelling";
			}
		}
		else {
			ack+="Error in cancelling";
		}
		return ack;
	}

	@Override
	public List<List<String>> showBookingById(String username) {
		List<Booking> l = br.findAll();
		List<List<String>> ll = new ArrayList<>();
		for(Booking b : l) {
			if(b.getUsername().equals(username)) {
				List<String> list = new ArrayList<>();
				List<String> f = fs.sourceDestById(b.getFlightid());
				list.add(String.valueOf(b.getPnr()));
				list.add(f.get(0));
				list.add(f.get(1));
				list.add(String.valueOf(b.getTimestamp()));
				list.add(b.getStatus());
				ll.add(list);
				
			}
		}
		return ll;
	}

	@Override
	public List<String> viewTicket(String username, int pnr) {
		List<String> l = new ArrayList<>();
		Booking b = br.getById(pnr);
		Flight f = fs.flightById(b.getFlightid());
		l.add("Flight id:  "+String.valueOf(f.getFlightId()));
		l.add("Flight name:  "+f.getName());
		l.add("Booking price:  "+String.valueOf(b.getPrice()));
		l.add("Dept time:  "+f.getDeparture()+" IST");
		l.add("Arrival:  "+f.getArrival()+" IST");
		l.add("Flight status:  "+f.getStatus());
		l.add("Delay tiime:  "+f.getDelay()+" hrs:min");
		l.add("Booking status:  "+b.getStatus());
		
		
		
		return l;
	}

	
	

}
