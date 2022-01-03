package com.th.controller;  


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;

import com.th.constants.PropertyConstants;
import com.th.model.Admin;
import com.th.model.Groceries;
import com.th.model.Users;

import com.th.service.AdminService;
import com.th.service.UserService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  


@Controller  
public class MainController 
{
	@Autowired
	UserService userservice;
	
	@Autowired
	AdminService adminservice;
	
@RequestMapping("/")  
public String Loginpage()
{  

	String loginpage = userservice.Loginpage();
	return loginpage; 
}  



@RequestMapping(PropertyConstants.SIGN_UP)  
public String signUp()
{  
	String signup = userservice.signUp();
	return signup;
} 

@PostMapping ("/saveuser")
public String saveUser(@ModelAttribute("user") Users user) {


	String loginpage = userservice.Loginpage();
	return loginpage; 
} 

@PostMapping ("/check")
public String auth (@ModelAttribute("user") Users u){
	
	String homepage = userservice.auth(u);
	return homepage;
	
}


@RequestMapping("/admin")  
public String adminPage()
{  
	String admin = adminservice.adminPage();
	return admin;  
}


@PostMapping ("/adminlogin")
public String adminlogincheck (@ModelAttribute("admin") Admin a, Model model){
	String adminlogin = adminservice.adminlogincheck(a, model);
	return adminlogin;
	

}
@RequestMapping("/adminlogin1")
public String redisplay(Model model) 
{

	String redisplay = adminservice.redisplay(model);
	return redisplay;

}



@RequestMapping("/new")
public String showNewProductPage(Model model) {
	
	
	String showproductpage = adminservice.showNewProductPage(model);
	return showproductpage;

}

@RequestMapping(value = "/save", method = RequestMethod.POST)
public String saveProduct(@ModelAttribute("product") Groceries product) {

	String saveproduct = adminservice.saveProduct(product);
	return saveproduct;
	
}



@RequestMapping("/delete/{id}")
public String deleteProduct(@PathVariable(name = "id") int id) {

	String deleteproduct = adminservice.deleteProduct(id);
	return deleteproduct;
}

@RequestMapping("/edit/{id}")
public ModelAndView showEditProductPage(@PathVariable(name = "id") int id)
{
	ModelAndView showeditproductpage = adminservice.showEditProductPage(id);
	return showeditproductpage;
	

}

@RequestMapping("/addtocarttable/{name}")
public String findCategoriestable(Model model, @PathVariable(name="name") String name) {

    String categorytable = adminservice.findCategoriestable(model, name);
    return categorytable;
}





 
}  