package com.virat.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virat.demo.model.User;
import com.virat.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	public UserRepository ur;

	@Override
	public String addUser(User u) {
		if(ur.existsById(u.getUsername())) {
			return "user name already exists";
		}
		
		else {
			ur.save(u);
			return "Inserted Successfully";
		}
		
		
	}

}
