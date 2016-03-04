package com.michaeldowden.jwf.web;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.michaeldowden.jwf.model.OrderItem;
import com.michaeldowden.jwf.model.ShoppingCart;
import com.michaeldowden.jwf.service.CartService;
import com.michaeldowden.jwf.service.ItemDao;

@RestController
public class OrderController {

	@Autowired
	CartService cartSvc;


}
