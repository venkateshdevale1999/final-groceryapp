package com.th.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
		emailid=u.getUseremail();

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
	public String saveProduct(@ModelAttribute("grocery") Groceries grocery,@RequestParam("img") MultipartFile file)throws IOException {

		grocery.setImage(file.getBytes());
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
	
	
	
	@RequestMapping("/addTocart/{proid}/{procat}/{qun}")
	public String addTocart(Model model, @PathVariable(name="proid") int proid,@PathVariable(name="procat") String procat,@PathVariable(name="qun") int qun,@ModelAttribute("li") Groceries product) {

	Userscartitems1 u=new Userscartitems1();
	u.setQuantity(qun);
	int price=gr.getproRec(proid).getPrice();

	u.setTotalprice(price*qun);
	u.setGroceries(gr.getproRec(proid));
	u.setUser(ur.getUesrRec(emailid));

	uc.save(u);

	return "redirect:/addtocarttable/"+procat;
	}



	@RequestMapping("/cartShow")
	public String showCart(Model model) {
	List<Userscartitems1> li=uc.findByUseremail(emailid);
	System.out.println(li);
	int totalp=0;
	for(Userscartitems1 u:li) {
	totalp += u.getTotalprice();
	}
	System.out.println(totalp);
	model.addAttribute("totalp",totalp);
	model.addAttribute("li",li);
	return "showcart";
	}



	@RequestMapping("/editCartItem/{itemid}")
	public String editCartItem(@PathVariable(name="itemid") int itemid,Model model) {

	Userscartitems1 item = uc.findById(itemid).get();
	model.addAttribute("item", item);



	return "edit_cartitem";
	}
	@RequestMapping("/saveCart")
	public String saveCart(@ModelAttribute("Userscartitems1") Userscartitems1 item) {
	Userscartitems1 itemlist = uc.findById(item.getItemid()).get();
	System.out.println(itemlist);
	int q=item.getQuantity();
	int p=itemlist.grocerie.getPrice();
	itemlist.setQuantity(item.getQuantity());
	itemlist.setTotalprice(q*p);

	uc.save(itemlist);
	return "redirect:/cartShow";

	}
	@RequestMapping("/deleteCartItem/{id}")
	public String deleteCartItem(@PathVariable(name = "id") int id) {
	uc.deleteById(id);
	return "redirect:/cartShow";
	}
	@RequestMapping("/payment/{price}")
	public String goToPayment(@PathVariable(name = "price") int price ,Model model) {
	model.addAttribute("price", price);
	return "payment";
	}
	@RequestMapping("/saveorder/{price}/{address}")
	public String finalPay(@PathVariable(name = "price") int price ,Model model,@PathVariable("address") String address) {

	MyOrder myOrder=new MyOrder();
	Random random = new Random();
	myOrder.setPiadamount(price);
	myOrder.setAddress(address);
	myOrder.setOrderdate(new Date());
	myOrder.setOrderid("OID"+random.nextInt(1000));
	myOrder.setUseremail(emailid);
	System.out.println(myOrder.toString());
	mo.save(myOrder);

	List<Userscartitems1> li=uc.findByUseremail(emailid);
	for(Userscartitems1 u1:li) {
	System.out.println(u1.getQuantity()+" "+u1.getGroceries().getQuantity());
	u1.getGroceries().setQuantity(u1.getGroceries().getQuantity()-u1.getQuantity());
	System.out.println("after"+u1.getQuantity()+" "+u1.getGroceries().getQuantity());
	uc.save(u1);
	}
	for(Userscartitems1 u1:li) {
		uc.deleteById(u1.getItemid());
	}
	System.out.println(emailid);
	
	
	model.addAttribute("oid", myOrder.getOrderid());
	return "success";
	}
	
	@RequestMapping("/homereplay")
	public String replayhome() {
		return "home";
		
		
	}
	
	@RequestMapping(value ="/savenoimg" , method = RequestMethod.POST)
	public String saveProductnoimg(@ModelAttribute("grocery") Groceries grocery) {

		
		gr.save(grocery);
		return "redirect:/adminlogin1";

	}
	
	@RequestMapping("/myorders")
	public String showMyorders(Model model) {
	List<MyOrder> li=mo.getMyOrderByEmail(emailid);
	model.addAttribute("li",li);
	return "myorders";
	}

}