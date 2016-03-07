package com.michaeldowden.jwf.service;

import static com.michaeldowden.jwf.utils.OrderUtils.buildOrder;
import static com.michaeldowden.jwf.utils.OrderUtils.findOrderInHistory;

import javax.servlet.http.HttpSession;

import com.michaeldowden.jwf.model.Address;
import com.michaeldowden.jwf.model.Order;
import com.michaeldowden.jwf.model.ShoppingCart;

public class OrderService {

	private static final String ORDER = "ORDER";

	private void clearOrder(HttpSession session) {
		session.invalidate();
	}

	public Order fetchOrder(HttpSession session) {
		Object order = session.getAttribute(ORDER);
		if (order == null) {
			order = new Order();
			session.setAttribute(ORDER, order);
		}
		return (Order) order;
	}

	public void updateShippingAddress(HttpSession session, Address address) {
		fetchOrder(session).setAddress(address);
	}

	public Address fetchShippingAddress(HttpSession session) {
		return fetchOrder(session).getAddress();
	}

	public Integer checkout(HttpSession session, ShoppingCart cart) {
		Integer orderNumber = buildOrder(fetchOrder(session), cart);
		// Clear current order
		clearOrder(session);
		// Return Order # for lookup
		return orderNumber;
	}

	public Order lookupOrder(Integer orderNumber) {
		return findOrderInHistory(orderNumber);
	}
}
