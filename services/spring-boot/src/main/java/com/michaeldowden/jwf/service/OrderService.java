package com.michaeldowden.jwf.service;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.michaeldowden.jwf.model.Address;
import com.michaeldowden.jwf.model.Order;
import com.michaeldowden.jwf.model.ShoppingCart;
import com.michaeldowden.jwf.web.ResourceNotFoundException;

@Service
@Scope(proxyMode = TARGET_CLASS, value = "session")
public class OrderService {
	private int lastOrderNumber = 1000000;
	private Map<Integer, Order> orderHistory = new HashMap<Integer, Order>();
	private Order order = new Order();

	public void updateShippingAddress(Address address) {
		order.setAddress(address);
	}

	public Address fetchShippingAddress() {
		return order.getAddress();
	}

	public Integer checkout(ShoppingCart cart) {
		Integer orderNumber = lastOrderNumber++;
		order.setOrderNumber(orderNumber);
		order.setItems(cart.getItems());
		order.setTotal(cart.getTotal());
		orderHistory.put(orderNumber, order);
		// Clear current order
		order = new Order();
		// Return Order # for lookup
		return orderNumber;
	}

	public Order lookupOrder(Integer orderNumber) {
		if (orderHistory.containsKey(orderNumber)) {
			return orderHistory.get(orderNumber);
		} else {
			throw new ResourceNotFoundException("Cannot find Order #" + orderNumber);
		}
	}
}
