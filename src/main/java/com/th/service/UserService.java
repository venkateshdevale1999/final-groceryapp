package com.th.service;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.th.model.Users;

public interface UserService {
	
	public String auth (@ModelAttribute("user") Users u);
	public String signUp();
	public String Loginpage();
	public String saveUser(@ModelAttribute("user") Users user);
	
	

}
