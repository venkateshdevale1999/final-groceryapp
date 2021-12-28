package com.th.controller;  
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;

import com.th.model.Users;
import com.th.repository.UsersRepository;
import com.th.userservice.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  


@Controller  
public class PageController 
{
	
	@Autowired
	UsersRepository ur;
	
	
@RequestMapping("/")  
public String index()
{  

return"login";  
}  



@RequestMapping("/signup")  
public String signUp()
{  
	return"signup";  
} 

@PostMapping ("/saveuser")
public String saveEmployee(@ModelAttribute("user") Users user) {
// save employee to database
ur.save(user);
return "login";
} 

@PostMapping ("/check")
public String auth (@ModelAttribute("user") Users u){
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






 
}  