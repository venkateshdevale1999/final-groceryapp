package com.th.controller;

import java.util.List;

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

import com.th.constants.PropertyConstants;
import com.th.model.Groceries;
import com.th.model.Userscart;
import com.th.repository.GroceryRepository;
import com.th.repository.UsersCartRepository;

@RestController
@RequestMapping("/cartcontroller")
public class CartController {

	@Autowired
	UsersCartRepository jpc;
	@Autowired
	GroceryRepository jpb;
	
	

	// display all grocery for user

	@GetMapping(PropertyConstants.GET_ALL_GROCERY)
	public ResponseEntity<List<Groceries>> getAllGrocery() {

		List<Groceries> glist = jpb.findAll();
		return new ResponseEntity<List<Groceries>>(glist, HttpStatus.OK);
	}

	// add a grocery to his cart

	@PostMapping("/grocery")
	public ResponseEntity<Userscart> save(@RequestBody Userscart c) {
		Userscart userCart = jpc.save(c);
		return new ResponseEntity<Userscart>(userCart, HttpStatus.OK);
	}

	// remove a grocery from his cart

	@DeleteMapping("/delete/{pid}")
	public ResponseEntity<Userscart> deleteGroceryById(@PathVariable int pid) {
		if (jpc.existsById(pid)) {
			jpc.deleteById(pid);
			return new ResponseEntity<Userscart>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Userscart>(HttpStatus.NOT_FOUND);
	}

}
