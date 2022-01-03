package com.th.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.th.model.Admin;
import com.th.model.Groceries;
import com.th.repository.AdminRepository;
import com.th.repository.GroceryRepository;





@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	
	
	@Autowired
	AdminRepository ar;
	
	@Autowired
	GroceryRepository gr;

	@Override
	public String adminPage() {
		
		return "admin";
	}

	@Override
	public String adminlogincheck(Admin a, Model model) {
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

	@Override
	public String redisplay(Model model) {
		List<Groceries> lg = gr.findAll();
		model.addAttribute("lg",lg);
		return "admincrud";
	}

	@Override
	public String showNewProductPage(Model model) {
		Groceries item = new Groceries();
		model.addAttribute("product", item);

		return "new_product";
	}

	@Override
	public String saveProduct(Groceries product) {
		gr.save(product);

		return "redirect:/adminlogin1";
	}

	@Override
	public String deleteProduct(int id) {
		gr.deleteById(id);
		return "redirect:/adminlogin1";
	}

	@Override
	public ModelAndView showEditProductPage(int id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Groceries item = gr.findById(id).get();
		mav.addObject("item", item);

		return mav;
	}

	@Override
	public String findCategoriestable(Model model, String name) {
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
