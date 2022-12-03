package com.virat.demo.service;

import java.util.List;

import com.virat.demo.model.Booking;

public interface BookingService {
	
	public String bookTicket(Booking b);
	public int pnrGenerate();
	public String cancelTicket(String username, int pnr);
	public List<List<String>> showBookingById(String username);

}
