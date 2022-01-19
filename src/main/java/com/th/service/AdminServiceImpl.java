package com.th.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.th.constants.SystemConstants;
import com.th.model.Admin;
import com.th.model.Groceries;
import com.th.repository.AdminRepository;
import com.th.repository.GroceryRepository;
import com.th.util.ImageUtil;





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
	public String adminlogincheck(Admin a, Model model, RedirectAttributes redirAttrs) {
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
			 
			redirAttrs.addFlashAttribute("error", "The");
			return "redirect:/admin";

		}
		else {
			redirAttrs.addFlashAttribute("error1", "The");
			return "redirect:/admin";
		}
	}

	@Override
	public String redisplay(Model model) {
		model.addAttribute("imgUtil", new ImageUtil());
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
	public String saveProduct(Groceries product, RedirectAttributes redirAttrs) {
		Optional<Groceries> list=gr.findById(product.getProid());
		if(list.isEmpty()) {
			gr.save(product);
			return "redirect:/adminlogin1";
		}
		else {
			redirAttrs.addFlashAttribute("error", "e");
			return "redirect:/new";
		}		
		
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
		
		model.addAttribute("imgUtil", new ImageUtil());
		if(name.equals(SystemConstants.FRUIT)) {
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
