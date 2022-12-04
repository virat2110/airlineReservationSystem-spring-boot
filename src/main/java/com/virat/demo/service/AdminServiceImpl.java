package com.virat.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virat.demo.model.Booking;
import com.virat.demo.model.Flight;
import com.virat.demo.repository.BookingRepository;
import com.virat.demo.repository.FlightRepository;
import com.virat.demo.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	public FlightRepository fr;
	
	@Autowired
	public BookingRepository br;
	
	@Autowired
	public UserRepository ur;
	
	@Override
	public List<String> adminFlightDetails() {
		List<String> l = new ArrayList<>();
		List<Flight> fli = fr.findAll();
		HashSet<String> s = new HashSet<>();
		HashSet<String> d = new HashSet<>();
		int delay =0;
		int can =0;
		l.add(String.valueOf(fli.size()));
		for(Flight f: fli) {
			s.add(f.getSource());
			d.add(f.getDest());
			if(f.getDelay().length() > 1) {
				delay++;
			}
			if(!f.getStatus().equalsIgnoreCase("running")) {
				can++;
			}
		}
		List<Booking> bb = br.findAll();
		int ticketcan =0;
		for(Booking b : bb) {
			if(!b.getStatus().equalsIgnoreCase("Booked")) {
				ticketcan++;
			}
		}
		long users = ur.count();
		
		l.add(String.valueOf(bb.size()));
		l.add(String.valueOf(users));
		l.add(String.valueOf(s.size()));
		l.add(String.valueOf(can));
		l.add(String.valueOf(ticketcan));
		l.add(String.valueOf(delay));
		l.add(String.valueOf(d.size()));
		
		
		return l;
	}

}
