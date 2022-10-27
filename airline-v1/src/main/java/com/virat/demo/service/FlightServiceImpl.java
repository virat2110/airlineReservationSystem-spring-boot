package com.virat.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virat.demo.model.Flight;
import com.virat.demo.model.SourceDest;
import com.virat.demo.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService{
	
	@Autowired
	public FlightRepository fr;
	
	@Autowired
	public SourceDestService sds;

	@Override
	public String addFlight(Flight f) {
		String ack="";
		if(fr.existsById(f.getFlightId())) {
			ack+="Flight id exists";
		}
		else {
			fr.save(f);
			SourceDest sd = new SourceDest();
			sd.setSource(f.getSource());
			sd.setDest(f.getDest());
			String a = sds.addSourceDest(sd);
			ack+="Flight added   ";
			ack+=a;
		}
		
		return ack;
	}

}
