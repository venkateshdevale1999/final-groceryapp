package com.th.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.th.constants.PropertyConstants;
import com.th.model.Admin;
import com.th.model.Groceries;
import com.th.model.MyOrder;
import com.th.model.Users;
import com.th.model.Userscartitems1;
import com.th.repository.GroceryRepository;
import com.th.repository.MyOrderRepository;
import com.th.repository.UsersCartRepository;
import com.th.repository.UsersRepository;
import com.th.service.AdminService;
import com.th.service.UserService;

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
	
	
	
	
	@Autowired
	GroceryRepository gr;
	
	@Autowired
	UsersRepository ur;
	
	@Autowired
	UsersCartRepository uc;
	
	@Autowired
	MyOrderRepository mo;
	

	
	String emailid;
	

	
	/**
	 * this is landing page for the application
	 * @return returns the login.html page
	 */
	@RequestMapping("/")
	public String Indexpage() {

		String startpage = userservice.Indexpage();
		return startpage;
	}
	
	@RequestMapping("/login")
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
	public String saveUser(@ModelAttribute("user") Users user, RedirectAttributes redirAttrs) {

		String loginpage = userservice.saveUser(user, redirAttrs);
		return loginpage;
	}

	/**
	 * checks the user credentials with email and password
	 * @param u  it contains email and password
	 * @return after validation return home page orlese invalid page
	 */
	@PostMapping(PropertyConstants.CHECK)
	public String auth(@ModelAttribute("user") Users u, RedirectAttributes redirAttrs, Model model) {
		emailid=u.getUseremail();
		model.addAttribute("emailid",emailid);
		String homepage = userservice.auth(u, redirAttrs);
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
	public String adminlogincheck(@ModelAttribute("admin") Admin admin, Model model, RedirectAttributes redirAttrs) {
		String adminlogin = adminservice.adminlogincheck(admin, model, redirAttrs);
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
	 * this method will display the category wise products
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
	public String saveProduct(@ModelAttribute("grocery") Groceries grocery,@RequestParam("img") MultipartFile file, RedirectAttributes redirAttrs)throws IOException {

		grocery.setImage(file.getBytes());
		String saveproduct = adminservice.saveProduct(grocery, redirAttrs);
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
	
	
	/**
	 * this will display the add to cart option in showtable
	 * @param model contains variables to display on html page
	 * @param proid product_id will taken from the path
	 * @param procat category will taken from the path
	 * @param qun quantity will taken from path
	 * @param product product will be taken from path
	 * @return displays the addtocart html page
	 */
	@RequestMapping(PropertyConstants.ADDTOCART_ID_CAT_QUN)
	public String addTocart(Model model, @PathVariable(name="proid") int proid,@PathVariable(name="procat") String procat,@PathVariable(name="qun") int qun,@ModelAttribute("li") Groceries product, RedirectAttributes redirAttrs) {

		String addtocart = userservice.addTocart(model, proid, procat, qun, product, redirAttrs, emailid);
		return addtocart;
	}



	/**
	 * this method will user cart product which choose before
	 * @param model contains the information about products selected
	 * @return
	 */
	@RequestMapping(PropertyConstants.CART_SHOW)
	public String showCart(Model model) {
		String showcart = userservice.showCart(model);
		return showcart;
	}



	/**
	 * this method is for editing of existing product in user cart table 
	 * @param itemid product id to edit in back-end
	 * @param model contains information to display on html page
	 * @return display edit product page
	 */
	@RequestMapping(PropertyConstants.EDIT_CART_ID)
	public String editCartItem(@PathVariable(name="itemid") int itemid,Model model) {

		String edit_cartitem = userservice.editCartItem(itemid, model);
		return edit_cartitem;
	}
	
	/**
	 * this methis is to save the edit product to the back-end table
	 * @param item object of the usercarttable
	 * @return save cart page will be display
	 */
	@RequestMapping(PropertyConstants.SAVE_CART)
	public String saveCart(@ModelAttribute("Userscartitems1") Userscartitems1 item) {
	
		String savecart = userservice.saveCart(item);
		return savecart;

	}
	
	/**
	 * this method will display the cart after deleting the product from table
	 * @param id takes the id from front-end html page 
	 * @return displays the cart
	 */
	@RequestMapping(PropertyConstants.DELETE_CART_ID)
	public String deleteCartItem(@PathVariable(name = "id") int id) {
	
		String deletecartitem = userservice.deleteCartItem(id);
		return deletecartitem;
	}
	
	/**
	 * this method will take the control to payment page after clicking buynow button
	 * @param price price will taken to next page
	 * @param model
	 * @return displays the payment page
	 */
	@RequestMapping(PropertyConstants.PAYMENT_PRICE)
	public String goToPayment(@PathVariable(name = "price") int price ,Model model) {
	
		
		String paymentpage = userservice.goToPayment(price, model);
		return paymentpage;
	}
	
	
	/**
	 * this method will go to confirmation page after payment
	 * @param price this will take the price from path
	 * @param model
	 * @param address this will taken from path
	 * @return
	 */
	@RequestMapping(PropertyConstants.SAVEORDER_PRICE_ADDRESS)
	public String finalPay(@PathVariable(name = "price") int price ,Model model,@PathVariable("address") String address) {

	
		String finalpay = userservice.finalPay(price, model, address);
		return finalpay;
	}
	
	/**
	 * this method directs to home page
	 * @return 
	 */
	@RequestMapping(PropertyConstants.HOME_REDIRECT)
	public String replayhome(Model model) {
		model.addAttribute("emailid", emailid);
		return "home";
		
		
	}
	
	@RequestMapping(value =PropertyConstants.SAVE_NOIMG , method = RequestMethod.POST)
	public String saveProductnoimg(@ModelAttribute("grocery") Groceries grocery) {

		
		String savenoimg = userservice.saveProductnoimg(grocery);
		return savenoimg;

	}
	
	@RequestMapping(PropertyConstants.MY_ORDER)
	public String showMyorders(Model model) {
	
		
		String myorder = userservice.showMyorders(model);
		return myorder;
	}
	
	@RequestMapping("/userorders")
	public String userOrders(Model model) {
		List<MyOrder> list=mo.findAll();
		model.addAttribute("list", list);
		return "userorders";
	}
	

}