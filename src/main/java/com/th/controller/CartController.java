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
	
	

	

}
