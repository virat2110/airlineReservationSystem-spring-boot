package com.virat.demo.service;

import com.virat.demo.model.User;

public interface UserService {
	public String addUser(User u);
	public String verifyLogin(String username, String password, int key);

}
