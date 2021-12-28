package com.th.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.th.model.Groceries;
import com.th.repository.GroceryRepository;


/**
 * GroceryController(CRUD operations for admin login)
 * @author venkatesh_devale
 *
 */
@RestController
@RequestMapping("/groceries")
public class GroceryController {
	
	@Autowired
	GroceryRepository groceryRepository;
	//put 'POST' in PostMan
	
	/**
	 * 
	 * @param b
	 * @return
	 */
	@PostMapping("/grocery")
	public ResponseEntity<Groceries> save (@RequestBody Groceries g){
		Groceries gk = groceryRepository.save(g);
		return new ResponseEntity<Groceries>(gk,HttpStatus.OK);
	}
	
	
	@GetMapping("/abc")
	public String viewHomePage() {
		return "home";		
	}
	
	
	/**
	 * getAllGrocery(write)
	 * @return List<Groceries> it contains grocery item list
	 */
	@GetMapping("/getAllGrocery")
	public  ResponseEntity<List<Groceries>> getAllGrocery(){
		
		List<Groceries> glist = groceryRepository.findAll();
		return new ResponseEntity<List<Groceries>>(glist,HttpStatus.OK);
	}
	//put 'GET' in PostMan
	@GetMapping("/getGroceryById/{id}")
	public ResponseEntity<Groceries> getGroceryById(@PathVariable int id){
		
		Optional<Groceries> gk = groceryRepository.findById(id);
		if(gk.isPresent()) {
			Groceries b= gk.get();
			return new ResponseEntity<Groceries>(b,HttpStatus.OK);
		}
		else
			return new  ResponseEntity<Groceries>(HttpStatus.NOT_FOUND);
		
	}
	//put 'DELETE' in PostMan
	@DeleteMapping("/deletegrocery/{id}")
	public ResponseEntity<Groceries> deleteGroceryById(@PathVariable int id){
		if(groceryRepository.existsById(id)) {
			groceryRepository.deleteById(id);
			return new ResponseEntity<Groceries>(HttpStatus.NO_CONTENT);
		}
			return new ResponseEntity<Groceries>(HttpStatus.NOT_FOUND);
	}
}
