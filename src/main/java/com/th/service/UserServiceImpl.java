package com.th.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.th.model.Groceries;
import com.th.model.MyOrder;
import com.th.model.Users;
import com.th.model.Userscartitems1;
import com.th.repository.GroceryRepository;
import com.th.repository.MyOrderRepository;
import com.th.repository.UsersCartRepository;
import com.th.repository.UsersRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UsersRepository ur;
	
	@Autowired
	GroceryRepository gr;
	
	@Autowired
	UsersCartRepository uc;
	
	@Autowired
	MyOrderRepository mo;
	
	String emailid;
	
	public static String username;

	@Override
	public String auth(Users u, RedirectAttributes redirAttrs,Model model) {
		Optional<Users> searchUser = ur.findById(u.getUseremail());
		String name = searchUser.get().getUsername();
		username = name;
		model.addAttribute("username",name);
		System.out.println(name);
		if(searchUser.isPresent()) 
		{
			emailid=u.getUseremail();
			Users u1= searchUser.get();
			if(u.getPassword().equals(u1.getPassword()))
				return "home";
			 
			redirAttrs.addFlashAttribute("error", "The");
			return "redirect:/login";

		}
		else {
			redirAttrs.addFlashAttribute("error1", "The");
			return "redirect:/login";
		}

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
	public String saveUser(Users user, RedirectAttributes redirAttrs) {
		Optional<Users> u=ur.findById(user.getUseremail());
		if(u.isEmpty()) {
			ur.save(user);
			redirAttrs.addFlashAttribute("success", "The");
			return "redirect:/signup";
			
		}
		else {
			redirAttrs.addFlashAttribute("error", "The");
			return "redirect:/signup";
		}
		
	}

	@Override
	public String addTocart(Model model, int proid, String procat, int qun, Groceries product, RedirectAttributes redirAttrs, String email) {
		List<Userscartitems1> opt=uc.findByProid(proid, email);
		if(opt.isEmpty()) {
			Userscartitems1 u=new Userscartitems1();
			u.setQuantity(qun);
			int price=gr.getproRec(proid).getPrice();

			u.setTotalprice(price*qun);
			u.setGroceries(gr.getproRec(proid));
			u.setUser(ur.getUesrRec(emailid));

			uc.save(u);
			redirAttrs.addFlashAttribute("success", "s");
			return "redirect:/addtocarttable/"+procat;
		}
		else {
			redirAttrs.addFlashAttribute("error", "e");
			return "redirect:/addtocarttable/"+procat;
		}
	}

	@Override
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

	@Override
	public String editCartItem(int itemid, Model model) {
		Userscartitems1 item = uc.findById(itemid).get();
		model.addAttribute("item", item);



		return "edit_cartitem";
	}

	@Override
	public String saveCart(Userscartitems1 item) {
		Userscartitems1 itemlist = uc.findById(item.getItemid()).get();
		
		int q=item.getQuantity();
		int p=itemlist.grocerie.getPrice();
		itemlist.setQuantity(item.getQuantity());
		itemlist.setTotalprice(q*p);

		uc.save(itemlist);
		return "redirect:/cartShow";
	}

	@Override
	public String deleteCartItem(int id) {
		uc.deleteById(id);
		return "redirect:/cartShow";
		
	}

	@Override
	public String goToPayment(int price, Model model) {
		model.addAttribute("price", price);
		return "payment";
	}

	@Override
	public String finalPay(int price, Model model, String address) {
		
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

	@Override
	public String saveProductnoimg(Groceries grocery) {
		gr.save(grocery);
		return "redirect:/adminlogin1";
	}

	@Override
	public String showMyorders(Model model) {
		List<MyOrder> li=mo.getMyOrderByEmail(emailid);
		model.addAttribute("li",li);
		return "myorders";
	}

	@Override
	public String Indexpage() {
		return "splash";
	}
	
	
	
	

	

	
	
	
	
	
	
	

}
