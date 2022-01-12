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

/**
 * 
 * @author Venkatesh_devale  syed_Asif
 *
 */
@Controller
public class MainController {
	
	
	/**
	 * to use all the methods of userservice and adminservice classes
	 */
	@Autowired
	UserService userservice;

	@Autowired
	AdminService adminservice;
	

	
	/**
	 * this is landing page for the application
	 * @return returns the login.html page
	 */
	@RequestMapping("/")
	public String Loginpage() {

		String loginpage = userservice.Loginpage();
		return loginpage;
	}
	
	

	/**
	 * user will guided to signup page for registration
	 * @return returns the signup.html page
	 */
	@RequestMapping(PropertyConstants.SIGN_UP)
	public String signUp() {
		String signup = userservice.signUp();
		return signup;
	}
	
	

	/**
	 * this methods save the details of new user
	 * @param user takes all the user credentials from page
	 * @return redirect backs to loginpage for login again
	 */
	@PostMapping(PropertyConstants.SAVE_USER)
	public String saveUser(@ModelAttribute("user") Users user) {

		String loginpage = userservice.saveUser(user);
		return loginpage;
	}

	/**
	 * checks the user credentials with email and password
	 * @param u  it contains email and password
	 * @return after validation return home page orlese invalid page
	 */
	@PostMapping(PropertyConstants.CHECK)
	public String auth(@ModelAttribute("user") Users u) {

		String homepage = userservice.auth(u);
		return homepage;

	}

	/**
	 * this methods is guide the admin to admin login page
	 * @return login page for admin
	 */
	@RequestMapping(PropertyConstants.ADMIN)
	public String adminPage() {
		String admin = adminservice.adminPage();
		return admin;
	}

	/**
	 * this method will validates the admin credentials
	 * @param admin takes admin id and password from html page
	 * @param model 
	 * @return page which has the option for CRUD for admin
	 */
	@PostMapping(PropertyConstants.ADMIN_LOGIN)
	public String adminlogincheck(@ModelAttribute("admin") Admin admin, Model model) {
		String adminlogin = adminservice.adminlogincheck(admin, model);
		return adminlogin;

	}

	/**
	 * this method will validates the admin credentials
	 * @param admin takes admin id and password from html page
	 * @param model 
	 * @return page which has the option for CRUD for admin
	 */
	@RequestMapping(PropertyConstants.ADMIN_LOGIN1)
	public String redisplay(Model model) {

		String redisplay = adminservice.redisplay(model);
		return redisplay;

	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(PropertyConstants.NEW)
	public String showNewProductPage(Model model) {

		String showproductpage = adminservice.showNewProductPage(model);
		return showproductpage;

	}

	
	/**
	 * this methods add a new item to grocery table
	 * @param grocery object of grocery will taken from html page
	 * @return returns saveproduct.html page
	 */
	@RequestMapping(value =PropertyConstants.SAVE , method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("grocery") Groceries grocery) {

		String saveproduct = adminservice.saveProduct(grocery);
		return saveproduct;

	}
	
	/**
	 * this method delete a item from grocery table 
	 * @param id
	 * @return
	 */
	@RequestMapping(PropertyConstants.DELETE)
	public String deleteProduct(@PathVariable(name = "id") int id) {

		String deleteproduct = adminservice.deleteProduct(id);
		return deleteproduct;
	}

	/**
	 * this method will edit the existing item from grocery table
	 * @param id this will take id from html page to edit 
	 * @return returns edit_product.html
	 */
	@RequestMapping(PropertyConstants.EDIT)
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView showeditproductpage = adminservice.showEditProductPage(id);
		return showeditproductpage;

	}

	/**
	 * this method display the specific table to user
	 * @param model
	 * @param name takes which category to display
	 * @return addtocart.html will be displayed
	 */
	@RequestMapping(PropertyConstants.ADDTOCART_NAME)
	public String findCategoriestable(Model model, @PathVariable(name = "name") String name) {

		String categorytable = adminservice.findCategoriestable(model, name);
		return categorytable;
	}

}