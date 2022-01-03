package com.th.service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.th.model.Admin;
import com.th.model.Groceries;

public interface AdminService {
	
	public String adminPage();
	public String adminlogincheck (@ModelAttribute("admin") Admin a, Model model);
	public String redisplay(Model model);
	public String showNewProductPage(Model model);
	public String saveProduct(@ModelAttribute("product") Groceries product);
	public String deleteProduct(@PathVariable(name = "id") int id);
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id);
	public String findCategoriestable(Model model, @PathVariable(name="name") String name);

}
