package com.michaeldowden.jwf.web;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michaeldowden.jwf.model.Address;
import com.michaeldowden.jwf.model.Order;
import com.michaeldowden.jwf.service.CartService;
import com.michaeldowden.jwf.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	CartService cartSvc;

	@Autowired
	OrderService orderSvc;

	@RequestMapping("/svc/order/shipping")
	public Address fetchShippingAddress() {
		return orderSvc.fetchShippingAddress();
	}

	@RequestMapping(value = "/svc/order/shipping", method = PUT)
	public void updateShippingAddress(@RequestBody Address address) {
		orderSvc.updateShippingAddress(address);
	}

	@RequestMapping(value = "/svc/order/checkout", method = POST)
	public Integer checkout() {
		return orderSvc.checkout(cartSvc.fetchCart());
	}

	@RequestMapping("/svc/order/{orderNumber}")
	public Order lookupOrder(@PathVariable("orderNumber") Integer orderNumber) {
		return orderSvc.lookupOrder(orderNumber);
	}

}
