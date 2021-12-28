package com.th.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.model.Users;
import com.th.repository.UsersRepository;

@Service
public class UserService {
	
	@Autowired
	UsersRepository ur;
	
	
	public void saveUser(Users user) {
		this.ur.save(user);
		}
	
	
	

}
