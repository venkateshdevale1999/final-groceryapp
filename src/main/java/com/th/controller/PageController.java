package com.th.controller;  
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;

import com.th.model.Admin;
import com.th.model.Groceries;
import com.th.model.Users;
import com.th.repository.AdminRepository;
import com.th.repository.GroceryRepository;
import com.th.repository.UsersRepository;
import com.th.userservice.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  


@Controller  
public class PageController 
{
	
	@Autowired
	UsersRepository ur;
	
	@Autowired
	AdminRepository ar;
	
	
	@Autowired
	GroceryRepository gr;
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


@RequestMapping("/admin")  
public String adminPage()
{  
	return"admin";  
}


@PostMapping ("/adminlogin")
public String adminlogincheck (@ModelAttribute("admin") Admin a, Model model){
	Optional<Admin> searchUser = ar.findById(a.getAdminid());
	
	if(searchUser.isPresent()) 
	{
		Admin a1= searchUser.get();
		if(a.getPassword().equals(a1.getPassword())) 
		{
			
			List<Groceries> lg = gr.findAll();
			model.addAttribute("lg",lg);
			return "admincrud";
			
		}
		 
		return "invalid";

	}
	else
	  return "invalid1";

}
@RequestMapping("/adminlogin1")
public String redisplay(Model model) 
{
List<Groceries> lg = gr.findAll();
model.addAttribute("lg",lg);
return "admincrud";}



@RequestMapping("/new")
public String showNewProductPage(Model model) {
Groceries item = new Groceries();
model.addAttribute("product", item);

return "new_product";
}

@RequestMapping(value = "/save", method = RequestMethod.POST)
public String saveProduct(@ModelAttribute("product") Groceries product) {
gr.save(product);

return "redirect:/adminlogin1";
}



@RequestMapping("/delete/{id}")
public String deleteProduct(@PathVariable(name = "id") int id) {
gr.deleteById(id);
return "redirect:/adminlogin1";
}

@RequestMapping("/edit/{id}")
public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
ModelAndView mav = new ModelAndView("edit_product");
Groceries item = gr.findById(id).get();
mav.addObject("item", item);

return mav;
}

@RequestMapping("/addtocarttable/{name}")
public String findCitiesNameEndsWith(Model model, @PathVariable(name="name") String name) {

    if(name.equals("fruit")) {
    	List<Groceries> li = ((GroceryRepository) gr).fruittable(name);
    	model.addAttribute("li", li);
    }
    if(name.equals("vegetable")) {
    	List<Groceries> li = ((GroceryRepository) gr).vegetabletable(name);
    	model.addAttribute("li", li);
    }
    if(name.equals("meat")) {
    	List<Groceries> li = ((GroceryRepository) gr).meattable(name);
    	model.addAttribute("li", li);
    }
    if(name.equals("dailyneeds")) {
    	List<Groceries> li = ((GroceryRepository) gr).dailytable(name);
    	model.addAttribute("li", li);
    }
    

    return "showtable";
}





 
}  