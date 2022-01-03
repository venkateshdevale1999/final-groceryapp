package com.th.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.model.Users;
import com.th.repository.UsersRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	UsersRepository ur;

	@Override
	public String auth(Users u) {
		Optional<Users> searchUser = ur.findById(u.getUseremail());
		if(searchUser.isPresent()) 
		{
			Users u1= searchUser.get();
			if(u.getPassword().equals(u1.getPassword()))
				return "home";
			 
			return "invalid";

		}
		else
		  return "invalid";

	}

	@Override
	public String signUp() {
		return"signup";  
	}

	@Override
	public String Loginpage() {
		
		return "login";
	}

	@Override
	public String saveUser(Users user) {
		ur.save(user);
		return "login";
	}
	
	
	
	
	
	

}
