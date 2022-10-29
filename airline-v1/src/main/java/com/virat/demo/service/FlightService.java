package com.virat.demo.service;

import java.util.List;

import com.virat.demo.model.Flight;

public interface FlightService {
	
	public String addFlight(Flight f);
	public List<Flight> flightList(String source, String dest);
	public Flight flightById(int id);

}
