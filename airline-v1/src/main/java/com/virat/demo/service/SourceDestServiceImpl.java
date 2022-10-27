package com.virat.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virat.demo.model.SourceDest;
import com.virat.demo.repository.SourceDestRepository;

@Service
public class SourceDestServiceImpl implements SourceDestService{
	
	@Autowired
	public SourceDestRepository sdr;

	@Override
	public String addSourceDest(SourceDest sd) {
		List<SourceDest> l = sdr.findAll();
		int flag =0;
		for(SourceDest s : l) {
			if(s.getSource().equalsIgnoreCase(sd.getSource()) && s.getDest().equalsIgnoreCase(sd.getDest())) {
				flag =1;
				break;
			}
		}
		if(flag==1) {
			return "S D already added";
		}
		else {
			sdr.save(sd);
			return "S D Added";
		}
		
	}

}
