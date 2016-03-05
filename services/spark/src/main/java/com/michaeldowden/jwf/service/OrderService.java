package com.michaeldowden.jwf.service;

import static spark.Spark.halt;

import java.util.HashMap;
import java.util.Map;

import spark.Request;

import com.michaeldowden.jwf.model.Address;
import com.michaeldowden.jwf.model.Order;
import com.michaeldowden.jwf.model.ShoppingCart;

public class OrderService {
	private static final OrderService SINGLETON = new OrderService();
	private static final String ORDER = "ORDER";

	private Map<Integer, Order> orderHistory = new HashMap<Integer, Order>();
	private int lastOrderNumber = 1000000;

	private OrderService() {
	}

	public static OrderService getInstance() {
		return SINGLETON;
	}

	public Order fetchOrder(Request req) {
		Object order = req.session().attribute(ORDER);
		if (order == null) {
			order = new Order();
			req.session().attribute(ORDER, order);
		}
		return (Order) order;
	}

	public void updateShippingAddress(Request req, Address address) {
		fetchOrder(req).setAddress(address);
	}

	public Address fetchShippingAddress(Request req) {
		return fetchOrder(req).getAddress();
	}

	public Integer checkout(Request req, ShoppingCart cart) {
		Integer orderNumber = lastOrderNumber++;
		Order order = fetchOrder(req);
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
			halt(404, "Cannot find Order #" + orderNumber);
			return null;
		}
	}
}
