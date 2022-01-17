package com.th.service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.th.model.Groceries;
import com.th.model.Users;
import com.th.model.Userscartitems1;

public interface UserService {
	
	public String auth (@ModelAttribute("user") Users u);
	public String signUp();
	public String Loginpage();
	public String saveUser(@ModelAttribute("user") Users user);
	public String addTocart(Model model, @PathVariable(name="proid") int proid,@PathVariable(name="procat") String procat,@PathVariable(name="qun") int qun,@ModelAttribute("li") Groceries product);
	public String showCart(Model model);
	public String editCartItem(@PathVariable(name="itemid") int itemid,Model model);
	public String saveCart(@ModelAttribute("Userscartitems1") Userscartitems1 item);
	public String deleteCartItem(@PathVariable(name = "id") int id);
	public String goToPayment(@PathVariable(name = "price") int price ,Model model);
	public String finalPay(@PathVariable(name = "price") int price ,Model model,@PathVariable("address") String address);
	public String saveProductnoimg(@ModelAttribute("grocery") Groceries grocery);
	public String showMyorders(Model model);

}
